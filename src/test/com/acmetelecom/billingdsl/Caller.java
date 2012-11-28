package test.com.acmetelecom.billingdsl;

public interface Caller {

	Callee newCallFrom(String callee);
	
	public void createCustomerBills();

}
