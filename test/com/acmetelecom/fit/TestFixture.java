package com.acmetelecom.fit;

import org.joda.time.DateTime;

public class TestFixture {
	private String number;
	private DateTime start;
	private int duration;
	private int cost;
	
	public void setNumber(String num){
		number = num;
	}
	
	public void setStart(DateTime st){
		start = st;
	}
	
	public void setDuration(int dur){
		duration = dur;
	}
	
	public int cost(){
		return SystemUnderTest.getCost(number, start, duration); //get real function here
	}
	
}
