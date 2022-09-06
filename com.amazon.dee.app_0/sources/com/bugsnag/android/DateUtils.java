package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes.dex */
class DateUtils {
    private static final ThreadLocal<DateFormat> iso8601Holder = new ThreadLocal<DateFormat>() { // from class: com.bugsnag.android.DateUtils.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        @NonNull
        public DateFormat initialValue() {
            TimeZone timeZone = TimeZone.getTimeZone(Constants.UTC);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            simpleDateFormat.setTimeZone(timeZone);
            return simpleDateFormat;
        }
    };

    DateUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Date fromIso8601(@NonNull String str) throws ParseException {
        return iso8601Holder.get().parse(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toIso8601(@NonNull Date date) {
        return iso8601Holder.get().format(date);
    }
}
