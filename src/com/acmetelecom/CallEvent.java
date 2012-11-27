package com.acmetelecom;

public abstract class CallEvent {
    private CallParticipant caller;
    private CallParticipant callee;
    private long time;

    public CallEvent(CallParticipant caller, CallParticipant callee, long timeStamp) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
    }

    public CallParticipant getCaller() {
        return caller;
    }

    public CallParticipant getCallee() {
        return callee;
    }

    public long time() {
        return time;
    }
}
