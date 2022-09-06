package com.amazon.credentiallocker;
/* loaded from: classes12.dex */
public class KeyManagement {
    public static final String NONE = "NONE";
    public static final String WPAPSK = "WPAPSK";
    private static final String[] values = {"NONE", "WPAPSK"};

    private KeyManagement() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
