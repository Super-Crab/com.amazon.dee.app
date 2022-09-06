package com.amazon.devicesetupservice.scap.v1;
/* loaded from: classes12.dex */
public class BleConnectionPriority {
    public static final String HIGH = "HIGH";
    public static final String BALANCED = "BALANCED";
    public static final String LOW = "LOW";
    private static final String[] values = {HIGH, BALANCED, LOW};

    private BleConnectionPriority() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
