package com.acmetelecom.billingdsl;

import com.acmetelecom.CallParticipant;

public interface Caller {

	Callee newCallFrom(CallParticipant callee);
	
	public String createCustomerBills();

}
