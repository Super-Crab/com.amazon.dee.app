package com.amazon.devicesetup.common.v1;
/* loaded from: classes12.dex */
public class Event {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    private static final String[] values = {"SUCCESS", FAILURE};

    private Event() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
