package com.acmetelecom;

import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.billingdsl.BillingSystemTestContext;
import com.acmetelecom.billingdsl.Caller;

public class BillingSystemTest {
	
    Caller billingContext = null;
	
	@Before
	public void setUp() throws Exception {
        billingContext = new BillingSystemTestContext();
	}
	
	@Test
	public void  runnerTest2() throws Exception {
		CallParticipant a = CallParticipant.newCaller("447722113434");
		CallParticipant b = CallParticipant.newCallee("447766511332");
		CallParticipant c = CallParticipant.newCallee("447711111111");
		CallParticipant d = CallParticipant.newCaller("447777765432");
		
		billingContext.newCallFrom(a).callTo(b).startAtTimeNow().withDuration(0,0,20)
		.newCallFrom(a).callTo(c).startAtTimeNow().withDuration(0,0,30)
		.newCallFrom(d).callTo(c).startAtTimeNow().withDuration(0,0,60);
        billingContext.createCustomerBills();
	}
}
