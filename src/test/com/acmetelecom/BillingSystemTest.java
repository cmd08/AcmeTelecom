package test.com.acmetelecom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.com.acmetelecom.billingdsl.BillingSystemTestContext;
import test.com.acmetelecom.billingdsl.Caller;

public class BillingSystemTest {
	
    Caller billingContext = null;
	
	@Before
	public void setUp() throws Exception {
        billingContext = new BillingSystemTestContext();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void runnerTest() throws Exception {
		billingContext.newCallFrom("447722113434").callTo("447766511332").startAtTimeNow().withDuration(0,0,20)
		.newCallFrom("447722113434").callTo("447711111111").startAtTimeNow().withDuration(0,0,30)
		.newCallFrom("447777765432").callTo("447711111111").startAtTimeNow().withDuration(0,0,60);
        
        billingContext.createCustomerBills();
    }

}
