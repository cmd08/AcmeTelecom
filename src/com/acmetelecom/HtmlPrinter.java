package com.acmetelecom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class HtmlPrinter implements Printer {

    private static Printer instance = new HtmlPrinter();

    private HtmlPrinter() {
    }

    public static Printer getInstance() {
        return instance;
    }

    public void printHeading(String name, String phoneNumber, String pricePlan) {
        beginHtml();
        printToFile(h2(name + "/" + phoneNumber + " - " + "Price Plan: " + pricePlan));
        System.out.println(h2(name + "/" + phoneNumber + " - " + "Price Plan: " + pricePlan));
        beginTable();
    }

    private void beginTable() {
    	printToFile("<table border=\"1\">");
    	printToFile(tr(th("Time") + th("Number") + th("Duration") + th("Cost")));
        System.out.println("<table border=\"1\">");
        System.out.println(tr(th("Time") + th("Number") + th("Duration") + th("Cost")));
    }

    private void endTable() {
    	printToFile("</table>");
        System.out.println("</table>");
    }

    private String h2(String text) {
        return "<h2>" + text + "</h2>";
    }

    public void printItem(String time, String callee, String duration, String cost) {
    	printToFile(tr(td(time) + td(callee) + td(duration) + td(cost)));
        System.out.println(tr(td(time) + td(callee) + td(duration) + td(cost)));
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
    	printToFile(h2("Total: " + total));
        System.out.println(h2("Total: " + total));
        endHtml();
        finishPrintToFile();
    }

	private void beginHtml() {
    	printToFile("<html>");
    	printToFile("<head></head>");
    	printToFile("<body>");
    	printToFile("<h1>");
    	printToFile("Acme Telecom");
    	printToFile("</h1>");
        System.out.println("<html>");
        System.out.println("<head></head>");
        System.out.println("<body>");
        System.out.println("<h1>");
        System.out.println("Acme Telecom");
        System.out.println("</h1>");
    }

    private void endHtml() {
    	printToFile("</body>");
    	printToFile("</html>");
        System.out.println("</body>");
        System.out.println("</html>");
    }
    
    BufferedWriter fileOut = null;
    boolean append = false;
    
    private void printToFile(String s) {
    	if (fileOut == null) {
    		try {
				fileOut = new BufferedWriter(new FileWriter("output.html",append));
				append = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	try {
			fileOut.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void finishPrintToFile() {
    	if (fileOut!=null) {
    		try {
				fileOut.flush();
	    		fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		fileOut = null;
    	}
	}
}
