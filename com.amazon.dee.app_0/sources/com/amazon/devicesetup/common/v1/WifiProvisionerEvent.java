package com.amazon.devicesetup.common.v1;
/* loaded from: classes12.dex */
public class WifiProvisionerEvent {
    public static final String ERROR = "ERROR";
    public static final String SESSION_ENDED = "SESSION_ENDED";
    public static final String PROBE_REQUEST_SCANNING_STARTED = "PROBE_REQUEST_SCANNING_STARTED";
    public static final String PROBE_REQUEST_SCANNING_STOPPED = "PROBE_REQUEST_SCANNING_STOPPED";
    public static final String PROBE_REQUEST_RECEIVED = "PROBE_REQUEST_RECEIVED";
    public static final String ACCESS_POINT_CREATED = "ACCESS_POINT_CREATED";
    public static final String ACCESS_POINT_DESTROYED = "ACCESS_POINT_DESTROYED";
    public static final String PROVISIONEE_CONNECTED = "PROVISIONEE_CONNECTED";
    public static final String PROVISIONEE_DISCONNECTED = "PROVISIONEE_DISCONNECTED";
    private static final String[] values = {PROBE_REQUEST_SCANNING_STARTED, PROBE_REQUEST_SCANNING_STOPPED, PROBE_REQUEST_RECEIVED, ACCESS_POINT_CREATED, ACCESS_POINT_DESTROYED, PROVISIONEE_CONNECTED, PROVISIONEE_DISCONNECTED, "SESSION_ENDED", "ERROR"};

    private WifiProvisionerEvent() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
