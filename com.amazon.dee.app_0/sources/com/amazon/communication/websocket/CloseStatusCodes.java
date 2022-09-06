package com.amazon.communication.websocket;
/* loaded from: classes12.dex */
public class CloseStatusCodes {
    public static final int ACCOUNT_CHANGE = 4501;
    public static final int AUTHENTICATION_FAILED = 4001;
    public static final int CLIENT_ERROR = 4002;
    public static final int CONNECTION_TIMEOUT = 4503;
    public static final int CONNECTIVITY_CHANGE = 4500;
    public static final int ENTERING_SUSPENDED_STATE = 4511;
    public static final int EOF_ERROR = 4004;
    public static final int EXTRA_DATA_RECEIVED = 4003;
    public static final int HEARTBEAT_FAILURE = 4502;
    public static final int HEARTBEAT_PROBING_DONE = 4508;
    public static final int INCORRECT_FRAME_HEADER = 4005;
    public static final int IO_ERROR = 4007;
    public static final int MAX_VALUE = 4999;
    public static final int MIN_VALUE = 1000;
    public static final int NORMAL = 1000;
    public static final int NOT_SET = 4510;
    public static final int NOT_YET_CONNECTED_ERROR = 4015;
    public static final int NO_RECENT_HEARTBEATS = 4504;
    public static final int PINGPONG_FAILURE = 4006;
    public static final int POLICY_CHANGE = 4506;
    public static final int RESOURCE_CRUNCH_ON_SERVER = 4013;
    public static final int SCREEN_OFF = 4507;
    public static final int SELECTION_KEY_INVALID = 4018;
    public static final int SELECTOR_LOOP_ERROR = 4017;
    public static final int SERVER_COMMUNICATION_ERROR = 4008;
    public static final int SERVER_EXISTING_CONNECTION_NOT_OLD = 4014;
    public static final int SERVER_INITIATED_CLOSE = 4010;
    public static final int SERVER_RECEIVES_NEW_CONNECTION = 4009;
    public static final int SOCKET_MANAGER_SHUTTING_DOWN = 4016;
    public static final int TOO_MANY_WRITE_RETRIES = 4019;
    public static final int TUNING_FAILED = 4011;
    public static final int TUNING_FAILED_PROTOCOL_MISMATCH = 4012;
    public static final int UNKNOWN_ERROR = 4000;
    public static final int WATCHDOG_STOP_WATCHING = 4509;

    public static boolean isError(int i) {
        if (i == 1000 || i == 4009 || i == 4014 || i == 4010 || i == 4013) {
            return false;
        }
        return i < 4500 || i >= 4600;
    }
}
