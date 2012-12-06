package com.acmetelecom;

public class HtmlPrinter implements Printer {

    private static Printer instance = new HtmlPrinter();
    
    private StringBuilder buffer = new StringBuilder();

    private HtmlPrinter() {
    }

    public static Printer getInstance() {
        return instance;
    }

    public void printHeading(String name, String phoneNumber, String pricePlan) {
        beginHtml();
        printLineToBuffer(h2(name + "/" + phoneNumber + " - " + "Price Plan: " + pricePlan));
        beginTable();
    }

    private void beginTable() {
    	printLineToBuffer("<table border=\"1\">");
    	printLineToBuffer(tr(th("Time") + th("Number") + th("Duration") + th("Cost")));
    }

    private void endTable() {
    	printLineToBuffer("</table>");
    }

    private String h2(String text) {
        return "<h2>" + text + "</h2>";
    }

    public void printItem(String time, String callee, String duration, String cost) {
    	printLineToBuffer(tr(td(time) + td(callee) + td(duration) + td(cost)));
    }

    private String tr(String text) {
        return "<tr>" + text + "</tr>";
    }

    private String th(String text) {
        return "<th width=\"160\">" + text + "</th>";
    }

    private String td(String text) {
        return "<td>" + text + "</td>";
    }

    public void printTotal(String total) {
        endTable();
    	printLineToBuffer(h2("Total: " + total));
        endHtml();
    }

	private void beginHtml() {
    	printLineToBuffer("<html>");
    	printLineToBuffer("<head></head>");
    	printLineToBuffer("<body>");
    	printLineToBuffer("<h1>");
    	printLineToBuffer("Acme Telecom");
    	printLineToBuffer("</h1>");
    }

    private void endHtml() {
    	printLineToBuffer("</body>");
    	printLineToBuffer("</html>");
    }
    
    private void printLineToBuffer(String s) {
    	buffer.append(s + "\n");
//    	System.out.println(s);
    }

	@Override
	public String getAndClearOutput() {
		String output = buffer.toString();
		buffer = new StringBuilder();
		return output;
	}
}
