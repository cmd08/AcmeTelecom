package com.acmetelecom;

import org.joda.time.DateTimeUtils;

public class CallEvent implements CallEventInterface {
    final private CallParticipant caller;
    final private CallParticipant callee;
    final private long time;
    final private CallType callType;

    private CallEvent(CallParticipant caller, CallParticipant callee, long timeStamp, CallType callType) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
        this.callType = callType;
    }
    
    public static CallEvent newCallStart(CallParticipant caller, CallParticipant callee) {
    	return new CallEvent(caller, callee, DateTimeUtils.currentTimeMillis(), CallType.CALL_START);
    }
    
    public static CallEvent newCallEnd(CallParticipant caller, CallParticipant callee) {
    	return new CallEvent(caller, callee, DateTimeUtils.currentTimeMillis(), CallType.CALL_END);
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
}
