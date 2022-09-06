package com.amazon.alexa.presence.bleconn.helpers;

import java.util.Calendar;
import java.util.Date;
/* loaded from: classes9.dex */
public final class DateHelper {
    private DateHelper() {
    }

    public static Date addHoursTo(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(10, i);
        return calendar.getTime();
    }
}
