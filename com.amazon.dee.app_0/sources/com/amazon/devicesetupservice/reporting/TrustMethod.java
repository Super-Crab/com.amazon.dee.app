package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class TrustMethod {
    public static final String AUTHENTICATED = "AUTHENTICATED";
    public static final String NONE = "NONE";
    public static final String UNAUTHENTICATED = "UNAUTHENTICATED";
    private static final String[] values = {"AUTHENTICATED", "UNAUTHENTICATED", "NONE"};

    private TrustMethod() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
