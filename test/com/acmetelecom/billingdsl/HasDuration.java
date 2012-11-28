package com.acmetelecom.billingdsl;

public interface HasDuration {

	Caller withDuration(int hours, int minutes, int seconds);

	Caller endAtTime(long endTime);
}
