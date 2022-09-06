package com.amazon.devicesetup.common.v1;
/* loaded from: classes12.dex */
public class WifiConnectionState {
    public static final String ASSOCIATED = "ASSOCIATED";
    public static final String AUTHENTICATED = "AUTHENTICATED";
    public static final String DISCONNECTED = "DISCONNECTED";
    public static final String FAILED = "FAILED";
    public static final String UNAUTHENTICATED = "UNAUTHENTICATED";
    public static final String IDLE = "IDLE";
    private static final String[] values = {IDLE, "DISCONNECTED", "UNAUTHENTICATED", "AUTHENTICATED", "ASSOCIATED", "FAILED"};

    private WifiConnectionState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
