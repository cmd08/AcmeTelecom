package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class BillingSystem {

    private List<CallEvent> callLog = new ArrayList<CallEvent>();

    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }

    public void createCustomerBills() {
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    private void createBillFor(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : callLog) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }

        List<RealCall> realCalls = new ArrayList<RealCall>();

        CallEvent start = null;
        for (CallEvent event : customerEvents) {
            if (event instanceof CallStart) {
                start = event;
            }
            if (event instanceof CallEnd && start != null) {
                realCalls.add(new RealCall(start, event));
                start = null;
            }
        }

        BigDecimal totalBill = new BigDecimal(0);
        List<LineItem> items = new ArrayList<LineItem>();

        for (RealCall realCall : realCalls) {

            Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);

            BigDecimal cost;

            DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
            if (peakPeriod.offPeak(realCall.startTime()) && peakPeriod.offPeak(realCall.endTime()) && realCall.durationSeconds() < 12 * 60 * 60) {
                cost = new BigDecimal(realCall.durationSeconds()).multiply(tariff.offPeakRate());
            } else {
                cost = new BigDecimal(realCall.durationSeconds()).multiply(tariff.peakRate());
            }

            cost = cost.setScale(0, RoundingMode.HALF_UP);
            BigDecimal callCost = cost;
            totalBill = totalBill.add(callCost);
            items.add(new LineItem(realCall, callCost));
        }

        new BillGenerator().send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

    static class LineItem {
        private RealCall realCall;
        private BigDecimal callCost;

        public LineItem(RealCall realCall, BigDecimal callCost) {
            this.realCall = realCall;
            this.callCost = callCost;
        }

        public String date() {
            return realCall.date();
        }

        public String callee() {
            return realCall.callee();
        }

        public String durationMinutes() {
            return "" + realCall.durationSeconds() / 60 + ":" + String.format("%02d", realCall.durationSeconds() % 60);
        }

        public BigDecimal cost() {
            return callCost;
        }
    }
}
