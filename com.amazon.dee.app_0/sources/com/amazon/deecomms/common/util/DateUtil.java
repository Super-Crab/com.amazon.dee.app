package com.amazon.deecomms.common.util;

import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes12.dex */
public final class DateUtil {
    private static final SimpleDateFormat utcDateOnlyFormat;
    private static final SimpleDateFormat utcDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    static {
        utcDateTimeFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        utcDateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        utcDateOnlyFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
    }

    private DateUtil() {
    }

    public static String getUTCDateOnly(Date date) {
        return utcDateOnlyFormat.format(date);
    }

    public static String getUTCTime(Date date) {
        return utcDateTimeFormat.format(date);
    }
}
