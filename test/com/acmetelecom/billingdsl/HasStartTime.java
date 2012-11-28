package com.acmetelecom.billingdsl;

public interface HasStartTime {

	HasDuration startAtTimeNow();
	
	HasDuration startAtTime(long time);

}
