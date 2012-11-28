package test.com.acmetelecom.billingdsl;

import com.acmetelecom.BillingSystem;

public class BillingSystemTestContext implements Caller, Callee, HasStartTime, HasDuration {
	private BillingSystem billingSystem;
	
	public BillingSystemTestContext() {
		billingSystem = new BillingSystem();
	}

	public void callInitiated(String caller, String callee) {
		billingSystem.callInitiated(caller, callee);
	}

	public void callCompleted(String caller, String callee) {
		billingSystem.callCompleted(caller, callee);
	}

	public void createCustomerBills() {
		billingSystem.createCustomerBills();
	}
	
	String savedCaller;
	String savedCallee;
	String savedTime;

	@Override
	public Callee newCallFrom(String caller) {
		savedCaller = caller;
		return this;
	}
	
	@Override
	public HasStartTime callTo(String callee) {
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
