package com.acmetelecom;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class Call {
    private CallEventInterface start;
    private CallEventInterface end;

    public Call(CallEventInterface start, CallEventInterface end) throws Exception {
    	if(end.getTime() < start.getTime()) {
    		throw new Exception();
    	} else {
    		this.start = start;
        	this.end = end;
    	}
    }

    public CallParticipant callee() {
        return start.getCallee();
    }

    public int durationSeconds() {
        return (int) (((end.getTime() - start.getTime()) / 1000));
    }

    public String date() {
        DateTimeFormatter dtf = DateTimeFormat.mediumDateTime();
        DateTime date = new DateTime(start.getTime());
        return dtf.print(date);
    }

    public DateTime startTime() {
        return new DateTime(start.getTime());
    }

    public DateTime endTime() {
        return new DateTime(end.getTime());
    }
}
