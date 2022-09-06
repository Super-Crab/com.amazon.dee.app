package com.amazon.alexa.location.networking.alps.models;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.sun.mail.imap.IMAPStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes9.dex */
public class ISO8601TimeSupplier {
    static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
    private static final String TAG = "ISO8601TimeSupplier";
    private final SimpleDateFormat iso8601DateFormat;
    private final Object lock;

    public ISO8601TimeSupplier() {
        this(DEFAULT_FORMAT, TimeZone.getTimeZone(Constants.UTC));
    }

    private void checkNotEmpty(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format(Locale.US, "%s must not be empty", str2));
        Log.e(TAG, "Throwing ", illegalArgumentException);
        throw illegalArgumentException;
    }

    private void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(String.format(Locale.US, "%s must not be null", str));
        Log.e(TAG, "Throwing ", nullPointerException);
        throw nullPointerException;
    }

    public String getTime(Date date) {
        String format;
        checkNotNull(date, IMAPStore.ID_DATE);
        synchronized (this.lock) {
            format = this.iso8601DateFormat.format(date);
        }
        return format;
    }

    public String getTimeOptional(Long l) {
        if (l == null) {
            return null;
        }
        return getTime(l.longValue());
    }

    public ISO8601TimeSupplier(String str, TimeZone timeZone) {
        checkNotEmpty(str, "format");
        this.lock = new Object();
        this.iso8601DateFormat = new SimpleDateFormat(str, Locale.US);
        this.iso8601DateFormat.setTimeZone(timeZone);
    }

    public String getTime(long j) {
        String format;
        synchronized (this.lock) {
            format = this.iso8601DateFormat.format(new Date(j));
        }
        return format;
    }
}
