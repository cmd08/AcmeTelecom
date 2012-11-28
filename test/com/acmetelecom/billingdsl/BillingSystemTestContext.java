package com.acmetelecom.billingdsl;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.CallParticipant;
import com.acmetelecom.HtmlPrinter;

public class BillingSystemTestContext implements Caller, Callee, HasStartTime, HasDuration {
	private BillingSystem billingSystem;
	
	public BillingSystemTestContext() {
		billingSystem = new BillingSystem();
	}

	public void callInitiated(CallParticipant caller, CallParticipant callee) {
		billingSystem.callInitiated(caller, callee);
	}

	public void callCompleted(CallParticipant caller, CallParticipant callee) {
		billingSystem.callCompleted(caller, callee);
	}

	public String createCustomerBills() {
		billingSystem.createCustomerBills();
		return HtmlPrinter.getInstance().getAndClearOutput();
	}
	
	CallParticipant savedCaller;
	CallParticipant savedCallee;
	String savedTime;

	@Override
	public Callee newCallFrom(CallParticipant caller) {
		savedCaller = caller;
		return this;
	}
	
	@Override
	public HasStartTime callTo(CallParticipant callee) {
		savedCallee = callee;
		return this;
	}
	
	@Override
	public Caller withDuration(int hours, int minutes, int seconds) throws InterruptedException {
		billingSystem.callInitiated(savedCaller, savedCallee);
		sleepSeconds(60*60*hours + 60*minutes + seconds);
		billingSystem.callCompleted(savedCaller, savedCallee);
		return this;
	}

	@Override
	public HasDuration startAtTimeNow() {
		//savedTime = now
//		savedTime = 
		return this;
	}
	
    private static void sleepSeconds(int n) throws InterruptedException {
        Thread.sleep(n * 1000);
    }




	
	
}
