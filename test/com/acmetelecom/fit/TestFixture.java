package com.acmetelecom.fit;

import org.joda.time.DateTime;

public class TestFixture {
	private String number;
	private String tarif;
	private DateTime start;
	private DateTime end;
	private int duration;
	private String rate;
	private int cost;
	
	public void setNumber(String num){
		number = num;
	}
	
	public void setTarif(String tar){
		tarif = tar;
	}
	
	public void setStart(DateTime st){
		start = st;
	}
	
	public void setEnd(DateTime en){
		end = en;
	}
	
	public int duration(){
		return SystemUnderTest.getCallDuration(start, end); // need to get the real function here
	}
	
	public String rate(){
		return SystemUnderTest.getCallRate(start, end); // get the real function to workout tarif here
	}
	
	public int cost(){
		return SystemUnderTest.getCallCost(start, end, rate); //get real function here
	}
	
}
