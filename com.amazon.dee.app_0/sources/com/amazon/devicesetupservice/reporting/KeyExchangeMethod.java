package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class KeyExchangeMethod {
    public static final String ECDHE = "ECDHE";
    public static final String JPAKE = "JPAKE";
    private static final String[] values = {ECDHE, JPAKE};

    private KeyExchangeMethod() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
