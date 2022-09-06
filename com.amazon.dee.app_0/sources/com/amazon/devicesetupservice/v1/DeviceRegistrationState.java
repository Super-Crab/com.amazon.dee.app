package com.amazon.devicesetupservice.v1;
/* loaded from: classes12.dex */
public class DeviceRegistrationState {
    public static final String NOT_REGISTERED = "NOT_REGISTERED";
    public static final String RECENTLY_REGISTERED = "RECENTLY_REGISTERED";
    public static final String PAST_REGISTERED = "PAST_REGISTERED";
    private static final String[] values = {"NOT_REGISTERED", RECENTLY_REGISTERED, PAST_REGISTERED};

    private DeviceRegistrationState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
