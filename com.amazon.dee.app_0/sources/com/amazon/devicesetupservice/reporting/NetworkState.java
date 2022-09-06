package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class NetworkState {
    public static final String ASSOCIATED = "ASSOCIATED";
    public static final String CONNECTED = "CONNECTED";
    public static final String DISCONNECTED = "DISCONNECTED";
    public static final String INITIATING_CONNECTION_ATTEMPT = "INITIATING_CONNECTION_ATTEMPT";
    public static final String CONNECTING = "CONNECTING";
    public static final String CONNECTION_FAILED_INTERNAL_ERROR = "CONNECTION_FAILED_INTERNAL_ERROR";
    public static final String CONNECTION_FAILED_PSK_AUTHENTICATION = "CONNECTION_FAILED_PSK_AUTHENTICATION";
    public static final String CONNECTION_FAILED_AP_NOT_FOUND = "CONNECTION_FAILED_AP_NOT_FOUND";
    public static final String CONNECTED_BEHIND_CAPTIVE_PORTAL = "CONNECTED_BEHIND_CAPTIVE_PORTAL";
    public static final String CONNECTED_LIMITED_CONNECTIVITY = "CONNECTED_LIMITED_CONNECTIVITY";
    public static final String STATE_INITIATING_CONNECTION_ATTEMPT = "STATE_INITIATING_CONNECTION_ATTEMPT";
    public static final String STATE_DISCONNECTED = "STATE_DISCONNECTED";
    public static final String STATE_CONNECTING = "STATE_CONNECTING";
    public static final String STATE_ASSOCIATED = "STATE_ASSOCIATED";
    private static final String[] values = {INITIATING_CONNECTION_ATTEMPT, "DISCONNECTED", CONNECTING, "CONNECTED", "ASSOCIATED", CONNECTION_FAILED_INTERNAL_ERROR, CONNECTION_FAILED_PSK_AUTHENTICATION, CONNECTION_FAILED_AP_NOT_FOUND, CONNECTED_BEHIND_CAPTIVE_PORTAL, CONNECTED_LIMITED_CONNECTIVITY, STATE_INITIATING_CONNECTION_ATTEMPT, STATE_DISCONNECTED, STATE_CONNECTING, STATE_ASSOCIATED};

    private NetworkState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
