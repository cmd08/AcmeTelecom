package com.acmetelecom;

import org.joda.time.DateTime;

class DaytimePeakPeriod {

    public boolean offPeak(DateTime time) {
        int hour = time.getHourOfDay();
        return hour < 7 || hour >= 19;
    }
}
