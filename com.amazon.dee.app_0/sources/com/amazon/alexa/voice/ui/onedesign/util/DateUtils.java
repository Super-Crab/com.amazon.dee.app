package com.amazon.alexa.voice.ui.onedesign.util;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class DateUtils {
    private DateUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean areSameCalendarDay(Date date, Date date2) {
        Calendar dateToCalendar = dateToCalendar(date);
        Calendar dateToCalendar2 = dateToCalendar(date2);
        int[] iArr = {1, 2, 5};
        for (int i = 0; i < iArr.length; i++) {
            if (dateToCalendar.get(iArr[i]) != dateToCalendar2.get(iArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static String convert24HrToTargetFormat(String str, Locale locale, boolean z) throws ParseException {
        return new SimpleDateFormat(z ? "HH:mm" : "h:mm a", locale).format(new SimpleDateFormat("H:mm", locale).parse(str));
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static long extractTimeFromDateString(List<String> list, String str) throws ParseException {
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            try {
                return new SimpleDateFormat(it2.next(), Locale.getDefault()).parse(str).getTime();
            } catch (ParseException unused) {
            }
        }
        throw new ParseException(GeneratedOutlineSupport1.outline72("Could not parse date: ", str), 0);
    }

    public static CharSequence getDayOfMonth(@NonNull Date date) {
        return String.valueOf(dateToCalendar(date).get(5));
    }

    public static int getDayOfWeek(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        return new int[]{-1, 7, 1, 2, 3, 4, 5, 6}[calendar.get(7)];
    }

    public static CharSequence getMonth(@NonNull Date date, Locale locale) {
        return dateToCalendar(date).getDisplayName(2, 2, locale).toUpperCase(locale);
    }
}
