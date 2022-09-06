package com.amazon.alexa.presence.bleconn.identity.model;

import com.amazon.alexa.presence.bleconn.helpers.LoggingHelper;
import java.util.Date;
/* loaded from: classes9.dex */
public class ValidTimePeriod {
    private Date end;
    private Date start;

    public ValidTimePeriod() {
    }

    private static int compareToNow(ValidTimePeriod validTimePeriod) {
        Date date = new Date();
        if (validTimePeriod.start.compareTo(date) > 0) {
            return 1;
        }
        return validTimePeriod.end.compareTo(date) < 0 ? -1 : 0;
    }

    public static boolean isCurrentlyValid(ValidTimePeriod validTimePeriod) {
        return compareToNow(validTimePeriod) == 0;
    }

    public static boolean isExpired(ValidTimePeriod validTimePeriod) {
        return compareToNow(validTimePeriod) < 0;
    }

    public static boolean isUsableInFuture(ValidTimePeriod validTimePeriod) {
        return compareToNow(validTimePeriod) > 0;
    }

    public Date getEnd() {
        return this.end;
    }

    public Date getStart() {
        return this.start;
    }

    public void setEnd(Date date) {
        this.end = date;
    }

    public void setStart(Date date) {
        this.start = date;
    }

    public String toString() {
        return LoggingHelper.dump(this);
    }

    public ValidTimePeriod(Date date, Date date2) {
        this.start = date;
        this.end = date2;
    }
}
