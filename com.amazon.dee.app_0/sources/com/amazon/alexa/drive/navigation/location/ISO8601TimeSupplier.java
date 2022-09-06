package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.sun.mail.imap.IMAPStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes7.dex */
public final class ISO8601TimeSupplier {
    static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
    private final SimpleDateFormat iso8601DateFormat;
    private final Object lock;

    public ISO8601TimeSupplier() {
        this(DEFAULT_FORMAT, TimeZone.getTimeZone(Constants.UTC));
    }

    public String getTime(Date date) {
        String format;
        Preconditions.notNull(date, IMAPStore.ID_DATE);
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
        Preconditions.notEmpty(str, "format");
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
