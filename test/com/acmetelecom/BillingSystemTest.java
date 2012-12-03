package com.acmetelecom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

import com.acmetelecom.billingdsl.BillingSystemTestContext;
import com.acmetelecom.billingdsl.Caller;

public class BillingSystemTest {
	
	final private static String testOutputDir = "testOutput/";
	final private static String testCompareDir = "resources/BillingSystemTestCompare/";
	
    Caller billingContext = null;
	
	@Before
	public void setUp() throws Exception {
        billingContext = new BillingSystemTestContext();
	}
	
	@Test
	public void testDSL() throws Exception {
		String testOutputName = "DSLOutput.html";
		
		DateTime startTimeCall1 = new DateTime(2012,12,03,12,01,0);
		DateTime startTimeCall2 = new DateTime(2012,12,03,12,02,0);
		DateTime startTimeCall3 = new DateTime(2012,12,03,12,03,0);
		
		DateTime endTimeCall1 = startTimeCall3.plusMinutes(3);
		DateTime endTimeCall2 = startTimeCall3.plusMinutes(2);
		DateTime endTimeCall3 = startTimeCall3.plusMinutes(1);
		
		CallParticipant a = CallParticipant.newCaller("447722113434");
		CallParticipant b = CallParticipant.newCallee("447766511332");
		CallParticipant c = CallParticipant.newCallee("447711111111");
		CallParticipant d = CallParticipant.newCaller("447711232343");
		
		billingContext.newCallFrom(a).callTo(b).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(a).callTo(c).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(d).callTo(c).startAtTime(startTimeCall3).endAtTime(endTimeCall3);
       
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	//Test Off Peak Calls
	
	@Test
	public void testOffPeakStandardRateCalls() throws Exception {
		String testOutputName = "OffPeakStandardRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447711232343");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,00,00,00);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,06,59,59);
		
		DateTime startTimeCall2 = 	new DateTime(2012,01,01,19,00,00);
		DateTime endTimeCall2 = 	new DateTime(2012,01,01,23,59,59);
		
		DateTime startTimeCall3 = 	new DateTime(2012,01,02,19,00,00);
		DateTime endTimeCall3 = 	new DateTime(2012,01,03,06,59,59);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall3).endAtTime(endTimeCall3);
       
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	@Test
	public void testOffPeakBusinessRateCalls() throws Exception {
		String testOutputName = "OffPeakBusinessRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447722113434");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,00,00,00);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,06,59,59);
		
		DateTime startTimeCall2 = 	new DateTime(2012,01,01,19,00,00);
		DateTime endTimeCall2 = 	new DateTime(2012,01,01,23,59,59);
		
		DateTime startTimeCall3 = 	new DateTime(2012,01,02,19,00,00);
		DateTime endTimeCall3 = 	new DateTime(2012,01,03,06,59,59);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall3).endAtTime(endTimeCall3);
       
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	@Test
	public void testOffPeakLeisureRateCalls() throws Exception {
		String testOutputName = "OffPeakLeisureRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447799555444");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,00,00,00);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,06,59,59);
		
		DateTime startTimeCall2 = 	new DateTime(2012,01,01,19,00,00);
		DateTime endTimeCall2 = 	new DateTime(2012,01,01,23,59,59);
		
		DateTime startTimeCall3 = 	new DateTime(2012,01,02,19,00,00);
		DateTime endTimeCall3 = 	new DateTime(2012,01,03,06,59,59);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall3).endAtTime(endTimeCall3);
       
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	//Test Mixed Peak Calls
	
	@Test
	public void testMixedPeakStandardRateCalls() throws Exception {
		String testOutputName = "MixedPeakStandardRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447711232343");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		//Start in off peak, end in peak
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,00,00,01);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,07,00,00);
		
		//Start in peak, end in off peak
		DateTime startTimeCall2 = 	new DateTime(2012,01,01,18,59,59);
		DateTime endTimeCall2 = 	new DateTime(2012,01,01,23,59,58);
		
		//Start in peak, end in peak across midnight and across off peak times
		DateTime startTimeCall3 = 	new DateTime(2012,01,03,18,00,00);
		DateTime endTimeCall3 = 	new DateTime(2012,01,04,07,00,00);
		
		//Start in off peak, end in peak across midnight and across off peak times
		DateTime startTimeCall4 = 	new DateTime(2012,01,04,19,00,00);
		DateTime endTimeCall4 = 	new DateTime(2012,01,05,8,00,00);
		
		//Start in off peak, end in off peak across peak times
		DateTime startTimeCall5 = 	new DateTime(2012,01,06,06,00,00);
		DateTime endTimeCall5 = 	new DateTime(2012,01,06,19,00,00);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall3).endAtTime(endTimeCall3)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall4).endAtTime(endTimeCall4)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall5).endAtTime(endTimeCall5);

		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	@Test
	public void testMixedPeakBusinessRateCalls() throws Exception {
		String testOutputName = "MixedPeakBusinessRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447722113434");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		//Start in off peak, end in peak
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,00,00,01);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,07,00,00);
		
		//Start in peak, end in off peak
		DateTime startTimeCall2 = 	new DateTime(2012,01,01,18,59,59);
		DateTime endTimeCall2 = 	new DateTime(2012,01,01,23,59,58);
		
		//Start in peak, end in peak across midnight and across off peak times
		DateTime startTimeCall3 = 	new DateTime(2012,01,03,18,00,00);
		DateTime endTimeCall3 = 	new DateTime(2012,01,04,07,00,00);
		
		//Start in off peak, end in peak across midnight and across off peak times
		DateTime startTimeCall4 = 	new DateTime(2012,01,04,19,00,00);
		DateTime endTimeCall4 = 	new DateTime(2012,01,05,8,00,00);
		
		//Start in off peak, end in off peak across peak times
		DateTime startTimeCall5 = 	new DateTime(2012,01,06,06,00,00);
		DateTime endTimeCall5 = 	new DateTime(2012,01,06,19,00,00);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall3).endAtTime(endTimeCall3)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall4).endAtTime(endTimeCall4)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall5).endAtTime(endTimeCall5);

		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	@Test
	public void testMixedPeakLeisureRateCalls() throws Exception {
		String testOutputName = "MixedPeakLeisureRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447799555444");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		//Start in off peak, end in peak
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,00,00,01);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,07,00,00);
		
		//Start in peak, end in off peak
		DateTime startTimeCall2 = 	new DateTime(2012,01,01,18,59,59);
		DateTime endTimeCall2 = 	new DateTime(2012,01,01,23,59,58);
		
		//Start in peak, end in peak across midnight and across off peak times
		DateTime startTimeCall3 = 	new DateTime(2012,01,03,18,00,00);
		DateTime endTimeCall3 = 	new DateTime(2012,01,04,07,00,00);
		
		//Start in off peak, end in peak across midnight and across off peak times
		DateTime startTimeCall4 = 	new DateTime(2012,01,04,19,00,00);
		DateTime endTimeCall4 = 	new DateTime(2012,01,05,8,00,00);
		
		//Start in off peak, end in off peak across peak times
		DateTime startTimeCall5 = 	new DateTime(2012,01,06,06,00,00);
		DateTime endTimeCall5 = 	new DateTime(2012,01,06,19,00,00);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall3).endAtTime(endTimeCall3)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall4).endAtTime(endTimeCall4)
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall5).endAtTime(endTimeCall5);

		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	//Test Peak Calls
	
	@Test
	public void testPeakStandardRateCalls() throws Exception {
		String testOutputName = "PeakStandardRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447711232343");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		//Start in peak, end in peak
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,07,00,00);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,18,59,59);
		
		//Start in peak, end in peak
		DateTime startTimeCall2 = 	new DateTime(2012,01,02,12,00,00);
		DateTime endTimeCall2 = 	new DateTime(2012,01,02,13,00,00);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2);
		
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	@Test
	public void testPeakBusinessRateCalls() throws Exception {
		String testOutputName = "PeakBusinessRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447722113434");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		//Start in peak, end in peak
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,07,00,00);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,18,59,59);
		
		//Start in peak, end in peak
		DateTime startTimeCall2 = 	new DateTime(2012,01,02,12,00,00);
		DateTime endTimeCall2 = 	new DateTime(2012,01,02,13,00,00);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2);
		
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	@Test
	public void testPeakLeisureRateCalls() throws Exception {
		String testOutputName = "PeakLeisureRateCallOutput.html";
		
		CallParticipant caller = CallParticipant.newCaller("447799555444");
		
		CallParticipant callee1 = CallParticipant.newCallee("447766511332");
		CallParticipant callee2 = CallParticipant.newCallee("447711111111");
		
		//Start in peak, end in peak
		DateTime startTimeCall1 = 	new DateTime(2012,01,01,07,00,00);
		DateTime endTimeCall1 = 	new DateTime(2012,01,01,18,59,59);
		
		//Start in peak, end in peak
		DateTime startTimeCall2 = 	new DateTime(2012,01,02,12,00,00);
		DateTime endTimeCall2 = 	new DateTime(2012,01,02,13,00,00);
		
		billingContext
		.newCallFrom(caller).callTo(callee1).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(caller).callTo(callee2).startAtTime(startTimeCall2).endAtTime(endTimeCall2);
		
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,testOutputName);
		
		assertTestAndResourceFilesIdentical(testOutputName);
	}
	
	private static void assertTestAndResourceFilesIdentical(String outputFileName) throws IOException {
		File testFile = new File(new File(testOutputDir).getPath() + "/" + outputFileName);
		File referenceFile = new File(new File(testCompareDir).getPath() + "/" + outputFileName);
		
		BufferedReader testFileReader = new BufferedReader(new FileReader(testFile));
		BufferedReader referenceFileReader = new BufferedReader(new FileReader(referenceFile));
		
		int lineNumber = 0;
		
		while (true) {
			lineNumber++;
			String testLine = testFileReader.readLine();
			String refLine = referenceFileReader.readLine();
			
			assertEquals("Failure on line " + lineNumber, refLine, testLine);
			
			if (refLine == null) {
				break;
			}
		}
	}

	private static void printStringToFile(String output, String fileName) throws IOException {
		
		File dir = new File(testOutputDir);
		if (dir.exists() == false) {
			dir.mkdir();
		}
		
		File outFile = new File(dir.getPath() + "/" + fileName);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
		
		bw.write(output);
		bw.flush();
		bw.close();
	}
}
