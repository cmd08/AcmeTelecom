package com.acmetelecom.billingdsl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.joda.time.DateTime;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.CallEventInterface;
import com.acmetelecom.CallEventInterface.CallType;
import com.acmetelecom.CallParticipant;
import com.acmetelecom.HtmlPrinter;

public class BillingSystemTestContext implements Caller, Callee, HasStartTime, HasDuration {
	
	private BillingSystem billingSystem;
	
	CallParticipant savedCaller;
	CallParticipant savedCallee;
	long savedStartTime;
	
	public BillingSystemTestContext() {
		billingSystem = new BillingSystem();
	}
	
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
	public HasDuration startAtTime(long time) {
		savedStartTime = time;
		return this;
	}
	
	@Override
	public HasDuration startAtTime(DateTime time) {
		return startAtTime(time.getMillis());
	}

	@Override
	public Caller endAtTime(long endTime) {
		
		Mockery context = new Mockery();
		
		final CallParticipant savedMockCaller = savedCaller;
		final CallParticipant savedMockCallee = savedCallee;
		
		final long savedMockStartTime = savedStartTime;
		
		final long savedMockEndTime = endTime;
				
		final CallEventInterface mockCallStart = context.mock(CallEventInterface.class,"CallStart");
		
		final CallEventInterface mockCallEnd = context.mock(CallEventInterface.class, "CallEnd");
		
		context.checking(new Expectations() {
			{	
				allowing(mockCallStart).getCaller();
				will(returnValue(savedMockCaller));
				
				allowing(mockCallStart).getCallee();
				will(returnValue(savedMockCallee));
				
				allowing(mockCallStart).getTime();
				will(returnValue(savedMockStartTime));
				
				allowing(mockCallStart).getType();
				will(returnValue(CallType.CALL_START));
				
				allowing(mockCallEnd).getCaller();
				will(returnValue(savedMockCaller));
				
				allowing(mockCallEnd).getCallee();
				will(returnValue(savedMockCallee));
				
				allowing(mockCallEnd).getTime();
				will(returnValue(savedMockEndTime));
				
				allowing(mockCallEnd).getType();
				will(returnValue(CallType.CALL_END));
			}
		});
		
		billingSystem.callInitiated(mockCallStart);
		billingSystem.callCompleted(mockCallEnd);
		
		return this;
	}

	@Override
	public Caller endAtTime(DateTime endTime) {
		return endAtTime(endTime.getMillis());
	}
    
	public String createCustomerBills() {
		billingSystem.createCustomerBills();
		return HtmlPrinter.getInstance().getAndClearOutput();
	}
	
}
