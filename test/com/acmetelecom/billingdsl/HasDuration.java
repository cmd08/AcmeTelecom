package com.acmetelecom.billingdsl;

import org.joda.time.DateTime;

public interface HasDuration {

	Caller endAtTime(long endTime);
	
	Caller endAtTime(DateTime endTime);
}
