package com.amazon.devicesetup.common.v1;
/* loaded from: classes12.dex */
public class RegistrationState {
    public static final String FAILED = "FAILED";
    public static final String IN_PROGRESS = "IN_PROGRESS";
    public static final String NOT_REGISTERED = "NOT_REGISTERED";
    public static final String COMPLETE = "COMPLETE";
    private static final String[] values = {"NOT_REGISTERED", "IN_PROGRESS", COMPLETE, "FAILED"};

    private RegistrationState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
