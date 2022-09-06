package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class KeyManagement {
    public static final String NONE = "NONE";
    public static final String OPEN = "OPEN";
    public static final String WEP = "WEP";
    public static final String WPAPSK = "WPAPSK";
    private static final String[] values = {"WPAPSK", "WEP", "OPEN", "NONE"};

    private KeyManagement() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
