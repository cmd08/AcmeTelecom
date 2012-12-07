

import fit.ColumnFixture;
import fit.Parse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimeParserBucket;

import com.acmetelecom.CallParticipant;


public class GivenTheFollowingCallEvents extends ColumnFixture{
	
	public String Caller;
	public String Callee;
	public String StartTime;
	public String EndTime;
	
	@Override
	public void doRows(Parse rows){
		super.doRows(rows);
	}
	
	@Override 
	public void execute() throws Exception {

		CallParticipant caller = CallParticipant.newCallee(Caller);
		CallParticipant callee = CallParticipant.newCallee(Callee);
		DateTime startTime = DateTime.parse(StartTime);
		DateTime endTime = DateTime.parse(EndTime);
		
		SystemUnderTest.billingContext.newCallFrom(caller).callTo(callee).startAtTime(startTime).endAtTime(endTime);
		
	}
}
