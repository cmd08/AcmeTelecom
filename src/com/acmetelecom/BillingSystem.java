package com.acmetelecom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;

import com.acmetelecom.CallEventInterface.CallType;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public class BillingSystem {

	private PeakPeriod peakPeriod = PeakPeriod.DEFAULT_PEAK_PERIOD;
	
    private List<CallEventInterface> callLog = new ArrayList<CallEventInterface>();
    
    public void callInitiated(CallEventInterface callStart) {
    	callLog.add(callStart);
    }
    
    public void callCompleted(CallEventInterface callEnd) {
    	callLog.add(callEnd);
    }

    public void createCustomerBills() {
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    public void createBillFor(Customer customer) {
        List<CallEventInterface> customerEvents = new ArrayList<CallEventInterface>();
        for (CallEventInterface callEvent : callLog) {
            if (callEvent.getCaller().getNumber().equals(customer.getPhoneNumber())) {
            	customerEvents.add(callEvent);
            }
        }

        List<Call> calls = new ArrayList<Call>();

        CallEventInterface start = null;
        for (CallEventInterface event : customerEvents) {
            if (event.getType() == CallType.CALL_START) {
                start = event;
            }
            if (event.getType() == CallType.CALL_END && start != null) {
                calls.add(new Call(start, event));
                start = null;
            }
        }

        BigDecimal totalBill = new BigDecimal(0);
        List<LineItem> items = new ArrayList<LineItem>();
        
        Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);

        for (Call call : calls) {

        	BigDecimal callCost = calculateCallCost(call, tariff);
        	
            totalBill = totalBill.add(callCost);
            items.add(new LineItem(call, callCost));
        }

        new BillGenerator().send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

    private BigDecimal calculateCallCost(Call call, Tariff tariff) {

        BigDecimal cost = new BigDecimal(0);
        
        LocalTime currentTime = call.startTime().toLocalTime();
        
        int timeLeft = call.durationSeconds();
        
        while (timeLeft > 0) {
        	BigDecimal portionCost = null;
        	//If off peak
            if (peakPeriod.isOffPeak(currentTime)) {
            	int maxDurationInOffPeak = peakPeriod.getSecondsBeforeNextPeakStartTime(currentTime);
            	if (timeLeft < maxDurationInOffPeak) {
            		portionCost = new BigDecimal(timeLeft).multiply(tariff.offPeakRate());
            		timeLeft = 0;
            	} else {
            		portionCost = new BigDecimal(maxDurationInOffPeak).multiply(tariff.offPeakRate());
            		timeLeft -= maxDurationInOffPeak;
            		currentTime = currentTime.plusSeconds(maxDurationInOffPeak);
            	}
            } else {
            	int maxDurationInPeak = peakPeriod.getSecondsUntilPeakEndTime(currentTime);
            	if (timeLeft < maxDurationInPeak) {
            		portionCost = new BigDecimal(timeLeft).multiply(tariff.peakRate());
            		timeLeft = 0;
            	}
            	else {
            		portionCost = new BigDecimal(maxDurationInPeak).multiply(tariff.peakRate());
            		timeLeft -= maxDurationInPeak;
            		currentTime = currentTime.plusSeconds(maxDurationInPeak);
            	}
            }
            cost = cost.add(portionCost);
        }
        
        return cost.setScale(0, RoundingMode.HALF_UP);
	}

	static class LineItem {
        private Call call;
        private BigDecimal callCost;

        public LineItem(Call call, BigDecimal callCost) {
            this.call = call;
            this.callCost = callCost;
        }

        public String date() {
            return call.date();
        }

        public CallParticipant callee() {
            return call.callee();
        }

        public String durationMinutes() {
            return "" + call.durationSeconds() / 60 + ":" + String.format("%02d", call.durationSeconds() % 60);
        }

        public BigDecimal cost() {
            return callCost;
        }
    }
}
