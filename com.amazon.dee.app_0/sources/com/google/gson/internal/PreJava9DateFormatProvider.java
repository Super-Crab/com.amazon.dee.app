package com.google.gson.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes3.dex */
public class PreJava9DateFormatProvider {
    private static String getDateFormatPattern(int i) {
        if (i != 0) {
            if (i == 1) {
                return "MMMM d, y";
            }
            if (i == 2) {
                return "MMM d, y";
            }
            if (i != 3) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown DateFormat style: ", i));
            }
            return "M/d/yy";
        }
        return "EEEE, MMMM d, y";
    }

    private static String getDatePartOfDateTimePattern(int i) {
        if (i != 0) {
            if (i == 1) {
                return "MMMM d, yyyy";
            }
            if (i == 2) {
                return "MMM d, yyyy";
            }
            if (i != 3) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown DateFormat style: ", i));
            }
            return "M/d/yy";
        }
        return "EEEE, MMMM d, yyyy";
    }

    private static String getTimePartOfDateTimePattern(int i) {
        if (i == 0 || i == 1) {
            return "h:mm:ss a z";
        }
        if (i == 2) {
            return "h:mm:ss a";
        }
        if (i != 3) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown DateFormat style: ", i));
        }
        return "h:mm a";
    }

    public static DateFormat getUSDateFormat(int i) {
        return new SimpleDateFormat(getDateFormatPattern(i), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int i, int i2) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(i) + " " + getTimePartOfDateTimePattern(i2), Locale.US);
    }
}
