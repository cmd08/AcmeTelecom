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
	public void basicTest() throws Exception {
		String testOutputName = "runnerOutput.html";
		
		DateTime startTimeCall1 = new DateTime(2012,12,03,12,01,0);
		DateTime startTimeCall2 = new DateTime(2012,12,03,12,02,0);
		DateTime startTimeCall3 = new DateTime(2012,12,03,12,03,0);
		
		DateTime endTimeCall1 = startTimeCall3.plusMinutes(3);
		DateTime endTimeCall2 = startTimeCall3.plusMinutes(2);
		DateTime endTimeCall3 = startTimeCall3.plusMinutes(1);
		
		CallParticipant a = CallParticipant.newCaller("447722113434");
		CallParticipant b = CallParticipant.newCallee("447766511332");
		CallParticipant c = CallParticipant.newCallee("447711111111");
		CallParticipant d = CallParticipant.newCaller("447777765432");
		
		billingContext.newCallFrom(a).callTo(b).startAtTime(startTimeCall1).endAtTime(endTimeCall1)
		.newCallFrom(a).callTo(c).startAtTime(startTimeCall2).endAtTime(endTimeCall2)
		.newCallFrom(d).callTo(c).startAtTime(startTimeCall3).endAtTime(endTimeCall3);
       
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
