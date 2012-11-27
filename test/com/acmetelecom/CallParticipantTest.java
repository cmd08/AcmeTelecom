package com.acmetelecom;

import org.junit.Test;

public class CallParticipantTest {

	@Test
	public void testNewCaller() {
		String s = "447919973491";
		CallParticipant c = CallParticipant.newCaller(s);
		assert s.equals(c.toString());
	}

	@Test
	public void testNewCallee() {
		String s = "447919973491";
		CallParticipant c = CallParticipant.newCallee(s);
		assert s.equals(c.toString());
	}

	@Test
	public void testGetName() {
		String s = "447919973491";
		CallParticipant c = CallParticipant.newCallee(s);
		assert s.equals(c.getName());
	}

}
