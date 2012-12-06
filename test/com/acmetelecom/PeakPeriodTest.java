package com.acmetelecom;

import static org.junit.Assert.*;

import org.joda.time.LocalTime;
import org.junit.Test;

public class PeakPeriodTest {

	@Test
	public void testRegularPeakPeriod() {
		LocalTime startTime = new LocalTime(7,0,0);
		LocalTime endTime = new LocalTime(19,0,0);

		PeakPeriod pp = new PeakPeriod(startTime, endTime);
		
		assertTrue(pp.peakStartTime.isEqual(startTime));
		assertTrue(pp.peakEndTime.isEqual(endTime));
		
		LocalTime offPeakTime = new LocalTime(6,59,59);
		assertTrue(pp.isOffPeak(offPeakTime));
		
		assertEquals(pp.getSecondsBeforeNextPeakStartTime(offPeakTime),1);
		
		offPeakTime = new LocalTime(19,0,0);
		assertTrue(pp.isOffPeak(offPeakTime));
		
		assertEquals(pp.getSecondsBeforeNextPeakStartTime(offPeakTime),43200);
		
		LocalTime peakTime = new LocalTime(7,0,0);
		assertFalse(pp.isOffPeak(peakTime));
		
		assertEquals(pp.getSecondsUntilPeakEndTime(peakTime),43200);
		
		peakTime = new LocalTime(18,59,59);
		assertFalse(pp.isOffPeak(peakTime));
		
		assertEquals(pp.getSecondsUntilPeakEndTime(peakTime),1);
	}
	
	@Test
	public void testPeakPeriodOverMidnight() {
		LocalTime startTime = new LocalTime(19,0,0);
		LocalTime endTime = new LocalTime(7,0,0);

		PeakPeriod pp = new PeakPeriod(startTime, endTime);
		
		assertTrue(pp.peakStartTime.isEqual(startTime));
		assertTrue(pp.peakEndTime.isEqual(endTime));
		
		LocalTime offPeakTime = new LocalTime(7,0,0);
		assertTrue(pp.isOffPeak(offPeakTime));
		
		assertEquals(pp.getSecondsBeforeNextPeakStartTime(offPeakTime),43200);
		
		offPeakTime = new LocalTime(18,59,59);
		assertTrue(pp.isOffPeak(offPeakTime));
		
		assertEquals(pp.getSecondsBeforeNextPeakStartTime(offPeakTime),1);
		
		LocalTime peakTime = new LocalTime(6,59,59);
		assertFalse(pp.isOffPeak(peakTime));
		
		assertEquals(pp.getSecondsUntilPeakEndTime(peakTime),1);
		
		peakTime = new LocalTime(19,0,0);
		assertFalse(pp.isOffPeak(peakTime));
		
		assertEquals(pp.getSecondsUntilPeakEndTime(peakTime),43200);
	}
	
	

}
