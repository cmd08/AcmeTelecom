package com.acmetelecom;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

class PeakPeriod {
	
	final public static LocalTime PEAK_TIME_START_TIME = new LocalTime(7,0,0);
	final public static LocalTime PEAK_TIME_END_TIME = new LocalTime(19,00,00);

    public static boolean isOffPeak(DateTime time) {
    	LocalTime curTime = time.toLocalTime();
    	
    	boolean isAfterPeakStart = (PEAK_TIME_START_TIME.isBefore(curTime) || PEAK_TIME_START_TIME.isEqual(curTime));
    	boolean isBeforePeakEnd = PEAK_TIME_END_TIME.isAfter(curTime);
    	
    	if (isAfterPeakStart && isBeforePeakEnd) {
    		return false;
    	}
    	return true;
    }
}
