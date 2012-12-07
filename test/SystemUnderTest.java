

import com.acmetelecom.HtmlPrinter;
import com.acmetelecom.Printer;
import com.acmetelecom.billingdsl.BillingSystemTestContext;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CustomerDatabase;

/*
 * Unfortunately, FIT forces you to use static (e.g. global) variables to
 * share information between fixtures.  This class holds the objects
 * that we are testing and those that we are using to support the tests
 * in static variables and defines some useful methods.
 */
public class SystemUnderTest {
	
	public static final CustomerDatabase customerDatabase = CentralCustomerDatabase.getInstance();
	
	public static final BillingSystemTestContext billingContext = new BillingSystemTestContext();
	
	public static final Printer printer = HtmlPrinter.getInstance();

}
