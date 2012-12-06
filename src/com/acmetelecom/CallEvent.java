package com.acmetelecom;

public abstract class CallEvent {
    final private CallParticipant caller;
    final private CallParticipant callee;
    final private long time;
    final private CallType callType;

    public CallEvent(CallParticipant caller, CallParticipant callee, long timeStamp, CallType callType) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
        this.callType = callType;
    }

    public CallParticipant getCaller() {
        return caller;
    }

    public CallParticipant getCallee() {
        return callee;
    }

    public long getTime() {
        return time;
    }
    
    public CallType getType() {
        return callType;
    }
    
    public static enum CallType {
    	CALL_START,CALL_END;
    }
}
