package com.amazon.alexa.presence.detection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/* loaded from: classes9.dex */
public class BeaconFormatLogic {
    private static final String DEFAULT_TIMEZONE = "UTC";
    public static final String ISO_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final SimpleDateFormat simpleDateFormat;

    public BeaconFormatLogic(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public String bytesToHexString(byte[] bArr) throws IllegalArgumentException {
        if (bArr != null) {
            StringBuilder sb = new StringBuilder();
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Byte.valueOf(bArr[i])));
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Null beacon payload while trying to convert to hex string");
    }

    public String epochToIso8601(long j) throws IllegalArgumentException {
        if (j >= 0) {
            return this.simpleDateFormat.format(new Date(j));
        }
        throw new IllegalArgumentException("Invalid time input provided.");
    }
}
