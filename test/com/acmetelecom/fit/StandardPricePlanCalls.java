package com.acmetelecom.fit;

import fit.ColumnFixture;
import fit.Parse;
import org.joda.time.DateTime;

import com.acmetelecom.CallParticipant;


public class StandardPricePlanCalls extends ColumnFixture{
	
	public String Number;
	public DateTime StartTime;
	public int Duration;
	public int Cost;
	
	@Override
	public void doRows(Parse rows){
		super.doRows(rows);
	}
	
	@Override 
	public void execute() throws Exception {
		CallParticipant caller = CallParticipant.newCaller(Number);
		CallParticipant callee = CallParticipant.newCallee(Number);
		DateTime EndTime = StartTime.plusMinutes(Duration);
		
		SystemUnderTest.bst.newCallFrom(caller).callTo(callee).startAtTime(StartTime).endAtTime(EndTime);
		
	}
}
