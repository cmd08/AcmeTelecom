package com.acmetelecom.billingdsl;

import org.joda.time.DateTime;

public interface HasStartTime {

	HasDuration startAtTime(long time);
	
	HasDuration startAtTime(DateTime time);

}
