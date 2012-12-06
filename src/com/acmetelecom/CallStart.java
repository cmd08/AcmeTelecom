package com.acmetelecom;

import org.joda.time.DateTimeUtils;

public class CallStart extends CallEvent {
    public CallStart(CallParticipant caller, CallParticipant callee) {
        super(caller, callee, DateTimeUtils.currentTimeMillis(), CallType.CALL_START);
    }
}
