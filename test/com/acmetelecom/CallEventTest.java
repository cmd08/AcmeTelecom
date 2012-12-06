package com.acmetelecom;

import static org.junit.Assert.*;

import org.joda.time.DateTimeUtils;
import org.junit.Test;

import com.acmetelecom.CallEventInterface.CallType;

public class CallEventTest {
	
	@Test
	public void testCallStart() {
		
		long currentTime = DateTimeUtils.currentTimeMillis();
		CallParticipant caller = CallParticipant.newCaller("447919973491");
		CallParticipant callee = CallParticipant.newCallee("447766511332");
		
		CallEvent callStart = CallEvent.newCallStart(caller, callee);
		
		assertEquals(callStart.getCallee(), callee);
		
		assertEquals(callStart.getCaller(), caller);
		
		assertTrue(callStart.getTime() >= currentTime);
		
		assertEquals(callStart.getType(), CallType.CALL_START);
	}
	
	@Test
	public void testCallEnd() {
		
		long currentTime = DateTimeUtils.currentTimeMillis();
		CallParticipant caller = CallParticipant.newCaller("447919973491");
		CallParticipant callee = CallParticipant.newCallee("447766511332");
		
		CallEvent callStart = CallEvent.newCallEnd(caller, callee);
		
		assertEquals(callStart.getCallee(), callee);
		
		assertEquals(callStart.getCaller(), caller);
		
		assertTrue(callStart.getTime() >= currentTime);
		
		assertEquals(callStart.getType(), CallType.CALL_END);
	}

}
