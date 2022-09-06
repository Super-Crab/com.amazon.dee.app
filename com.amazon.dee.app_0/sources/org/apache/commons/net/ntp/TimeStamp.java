package org.apache.commons.net.ntp;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes4.dex */
public class TimeStamp implements Serializable, Comparable {
    public static final String NTP_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm:ss.SSS";
    protected static final long msb0baseTime = 2085978496000L;
    protected static final long msb1baseTime = -2208988800000L;
    private static final long serialVersionUID = 8139806907588338737L;
    private static SoftReference simpleFormatter;
    private static SoftReference utcFormatter;
    private long ntpTime;

    public TimeStamp(long j) {
        this.ntpTime = j;
    }

    private static void appendHexString(StringBuffer stringBuffer, long j) {
        String hexString = Long.toHexString(j);
        for (int length = hexString.length(); length < 8; length++) {
            stringBuffer.append('0');
        }
        stringBuffer.append(hexString);
    }

    protected static long decodeNtpHexString(String str) throws NumberFormatException {
        if (str != null) {
            int indexOf = str.indexOf(46);
            if (indexOf == -1) {
                if (str.length() != 0) {
                    return Long.parseLong(str, 16) << 32;
                }
                return 0L;
            }
            return (Long.parseLong(str.substring(0, indexOf), 16) << 32) | Long.parseLong(str.substring(indexOf + 1), 16);
        }
        throw new NumberFormatException("null");
    }

    public static TimeStamp getCurrentTime() {
        return getNtpTime(System.currentTimeMillis());
    }

    public static TimeStamp getNtpTime(long j) {
        return new TimeStamp(toNtpTime(j));
    }

    public static TimeStamp parseNtpString(String str) throws NumberFormatException {
        return new TimeStamp(decodeNtpHexString(str));
    }

    protected static long toNtpTime(long j) {
        long j2 = msb0baseTime;
        boolean z = j < msb0baseTime;
        if (z) {
            j2 = msb1baseTime;
        }
        long j3 = j - j2;
        long j4 = j3 / 1000;
        long j5 = ((j3 % 1000) * 4294967296L) / 1000;
        if (z) {
            j4 |= 2147483648L;
        }
        return j5 | (j4 << 32);
    }

    public int compareTo(TimeStamp timeStamp) {
        int i = (this.ntpTime > timeStamp.ntpTime ? 1 : (this.ntpTime == timeStamp.ntpTime ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    public boolean equals(Object obj) {
        return (obj instanceof TimeStamp) && this.ntpTime == ((TimeStamp) obj).ntpValue();
    }

    public Date getDate() {
        return new Date(getTime(this.ntpTime));
    }

    public long getFraction() {
        return this.ntpTime & BodyPartID.bodyIdMax;
    }

    public long getSeconds() {
        return (this.ntpTime >>> 32) & BodyPartID.bodyIdMax;
    }

    public long getTime() {
        return getTime(this.ntpTime);
    }

    public int hashCode() {
        long j = this.ntpTime;
        return (int) (j ^ (j >>> 32));
    }

    public long ntpValue() {
        return this.ntpTime;
    }

    public String toDateString() {
        String format;
        SoftReference softReference = simpleFormatter;
        DateFormat dateFormat = softReference != null ? (DateFormat) softReference.get() : null;
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(NTP_DATE_FORMAT, Locale.US);
            dateFormat.setTimeZone(TimeZone.getDefault());
            simpleFormatter = new SoftReference(dateFormat);
        }
        Date date = getDate();
        synchronized (dateFormat) {
            format = dateFormat.format(date);
        }
        return format;
    }

    public String toString() {
        return toString(this.ntpTime);
    }

    public String toUTCString() {
        String format;
        SoftReference softReference = utcFormatter;
        DateFormat dateFormat = softReference != null ? (DateFormat) softReference.get() : null;
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
            utcFormatter = new SoftReference(dateFormat);
        }
        Date date = getDate();
        synchronized (dateFormat) {
            format = dateFormat.format(date);
        }
        return format;
    }

    public static long getTime(long j) {
        long j2 = (j >>> 32) & BodyPartID.bodyIdMax;
        return GeneratedOutlineSupport1.outline9(j2, 1000L, (2147483648L & j2) == 0 ? msb0baseTime : msb1baseTime, Math.round(((j & BodyPartID.bodyIdMax) * 1000.0d) / 4.294967296E9d));
    }

    public static String toString(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        appendHexString(stringBuffer, (j >>> 32) & BodyPartID.bodyIdMax);
        stringBuffer.append('.');
        appendHexString(stringBuffer, j & BodyPartID.bodyIdMax);
        return stringBuffer.toString();
    }

    public TimeStamp(String str) throws NumberFormatException {
        this.ntpTime = decodeNtpHexString(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return compareTo((TimeStamp) obj);
    }

    public TimeStamp(Date date) {
        this.ntpTime = date == null ? 0L : toNtpTime(date.getTime());
    }
}
