package com.acmetelecom.billingdsl;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.CallEnd;
import com.acmetelecom.CallParticipant;
import com.acmetelecom.CallStart;
import com.acmetelecom.HtmlPrinter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

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
	public Caller withDuration(final int hours,final int minutes,final int seconds) {
		
		long durationMillis = (60*60*hours + 60*minutes + seconds) * 1000;
		long endAtTime = savedStartTime + durationMillis;
		
		return endAtTime(endAtTime);
	}

	@Override
	public HasDuration startAtTimeNow() {
		savedStartTime = System.currentTimeMillis();
		return this;
	}
    
	public String createCustomerBills() {
		billingSystem.createCustomerBills();
		return HtmlPrinter.getInstance().getAndClearOutput();
	}

	@Override
	public HasDuration startAtTime(long time) {
		savedStartTime = time;
		return this;
	}

	@Override
	public Caller endAtTime(long endTime) {
		
		//Allows jmock to mock classes. Required to get instanceof working with mocks.
		Mockery context = new Mockery() {{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		
		final CallParticipant savedMockCaller = savedCaller;
		final CallParticipant savedMockCallee = savedCallee;
		
		final long savedMockStartTime = savedStartTime;
		
		final long savedMockEndTime = endTime;
				
		final CallStart mockCallStart = context.mock(CallStart.class);
		
		final CallEnd mockCallEnd = context.mock(CallEnd.class);
		
		context.checking(new Expectations() {
			{	
				allowing(mockCallStart).getCaller();
				will(returnValue(savedMockCaller));
				
				allowing(mockCallStart).getCallee();
				will(returnValue(savedMockCallee));
				
				allowing(mockCallStart).time();
				will(returnValue(savedMockStartTime));
				
				allowing(mockCallEnd).getCaller();
				will(returnValue(savedMockCaller));
				
				allowing(mockCallEnd).getCallee();
				will(returnValue(savedMockCallee));
				
				allowing(mockCallEnd).time();
				will(returnValue(savedMockEndTime));
			}
		});
		
		billingSystem.callInitiated(mockCallStart);
		billingSystem.callCompleted(mockCallEnd);
		
		return this;
	}
}
