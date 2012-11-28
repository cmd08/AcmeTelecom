package com.acmetelecom;

public class CallStart extends CallEvent {
    public CallStart(CallParticipant caller, CallParticipant callee) {
        super(caller, callee, System.currentTimeMillis());
    }
}
