package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.utils.Preconditions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes11.dex */
public final class InternationalDateTime {
    private static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String UTC = "UTC";

    private InternationalDateTime() {
        throw new IllegalStateException("No instances allowed of this utility class!");
    }

    public static long toEpochMillisUtc(String str) {
        Preconditions.notBlank("dateTimeString must not be empty.", str);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat.parse(str).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
