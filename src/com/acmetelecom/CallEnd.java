package com.acmetelecom;

import org.joda.time.DateTimeUtils;

public class CallEnd extends CallEvent {
    public CallEnd(CallParticipant caller, CallParticipant callee) {
        super(caller, callee, DateTimeUtils.currentTimeMillis());
    }
}
