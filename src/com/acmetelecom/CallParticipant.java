package com.acmetelecom;

public class CallParticipant {
	private final String name;
	
	private CallParticipant(String name){
		this.name = name;
	}
	
	public static CallParticipant newCaller(String name){
		return new CallParticipant(name);
	}
	
	public static CallParticipant newCallee(String name){
		return new CallParticipant(name);
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}

}
