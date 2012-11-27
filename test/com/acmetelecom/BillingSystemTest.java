package com.acmetelecom;

import org.junit.Test;

public class BillingSystemTest {

	@Test
	public void runnerTest() throws Exception {
		CallParticipant a = CallParticipant.newCaller("447722113434");
		CallParticipant b = CallParticipant.newCallee("447766511332");
		CallParticipant c = CallParticipant.newCallee("447711111111");
		CallParticipant d = CallParticipant.newCaller("447777765432");
				
		
        System.out.println("Running...");
        BillingSystem billingSystem = new BillingSystem();
        billingSystem.callInitiated(a, b);
        sleepSeconds(20);
        billingSystem.callCompleted(a, b);
        billingSystem.callInitiated(a, c);
        sleepSeconds(30);
        billingSystem.callCompleted(a, c);
        billingSystem.callInitiated(d, c);
        sleepSeconds(60);
        billingSystem.callCompleted(d, c);
        billingSystem.createCustomerBills();
    }
    private static void sleepSeconds(int n) throws InterruptedException {
        Thread.sleep(n * 1000);
    }

}
