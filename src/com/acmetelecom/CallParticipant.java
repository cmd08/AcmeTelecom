package com.acmetelecom;

public class CallParticipant {
	private final String number;
	
	private CallParticipant(String number){
		this.number = number;
	}
	
	public static CallParticipant newCaller(String number){
		return new CallParticipant(number);
	}
	
	public static CallParticipant newCallee(String number){
		return new CallParticipant(number);
	}
	
	public String getNumber(){
		return this.number;
	}
	
	@Override
	public String toString(){
		return this.number;
	}

}
