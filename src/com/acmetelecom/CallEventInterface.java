package com.acmetelecom;

public interface CallEventInterface {

    public CallParticipant getCaller();

    public CallParticipant getCallee();

    public long getTime();
    
    public CallType getType();
    
    public static enum CallType {
    	CALL_START,CALL_END;
    }
}
