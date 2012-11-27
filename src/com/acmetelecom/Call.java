package com.acmetelecom;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;

public class Call {
    private CallEvent start;
    private CallEvent end;

    public Call(CallEvent start, CallEvent end) {
        this.start = start;
        this.end = end;
    }

    public String callee() {
        return start.getCallee();
    }

    public int durationSeconds() {
        return (int) (((end.time() - start.time()) / 1000));
    }

    public String date() {
        return new DateTime(start.time()).toString();
    }

    public DateTime startTime() {
        return new DateTime(start.time());
    }

    public DateTime endTime() {
        return new DateTime(end.time());
    }
}
