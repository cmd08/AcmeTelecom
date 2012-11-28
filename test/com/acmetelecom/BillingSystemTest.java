package com.acmetelecom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	public void  runnerTest() throws Exception {
		String outputFileName = "runnerOutput.html";
		
		CallParticipant a = CallParticipant.newCaller("447722113434");
		CallParticipant b = CallParticipant.newCallee("447766511332");
		CallParticipant c = CallParticipant.newCallee("447711111111");
		CallParticipant d = CallParticipant.newCaller("447777765432");
		
		billingContext.newCallFrom(a).callTo(b).startAtTimeNow().withDuration(0,0,1)
		.newCallFrom(a).callTo(c).startAtTimeNow().withDuration(0,0,2)
		.newCallFrom(d).callTo(c).startAtTimeNow().withDuration(0,0,3);
       
		String output = billingContext.createCustomerBills();
		
		printStringToFile(output,outputFileName);
		
		assertTestAndResourceFilesIdentical(outputFileName);
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

	private void printStringToFile(String output, String fileName) throws IOException {
		
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
