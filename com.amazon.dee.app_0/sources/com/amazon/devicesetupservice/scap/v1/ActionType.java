package com.amazon.devicesetupservice.scap.v1;
/* loaded from: classes12.dex */
public class ActionType {
    public static final String END_SESSION = "END_SESSION";
    public static final String WAIT = "WAIT";
    public static final String START_SCANNING = "START_SCANNING";
    public static final String STOP_SCANNING = "STOP_SCANNING";
    public static final String CONNECT_PERIPHERAL = "CONNECT_PERIPHERAL";
    public static final String DISCONNECT_PERIPHERAL = "DISCONNECT_PERIPHERAL";
    public static final String START_SERVICE_DISCOVERY = "START_SERVICE_DISCOVERY";
    public static final String STOP_SERVICE_DISCOVERY = "STOP_SERVICE_DISCOVERY";
    public static final String START_CHARACTERISTIC_DISCOVERY = "START_CHARACTERISTIC_DISCOVERY";
    public static final String STOP_CHARACTERISTIC_DISCOVERY = "STOP_CHARACTERISTIC_DISCOVERY";
    public static final String SUBSCRIBE_CHARACTERISTIC = "SUBSCRIBE_CHARACTERISTIC";
    public static final String UNSUBSCRIBE_CHARACTERISTIC = "UNSUBSCRIBE_CHARACTERISTIC";
    public static final String READ_CHARACTERISTIC = "READ_CHARACTERISTIC";
    public static final String WRITE_CHARACTERISTIC = "WRITE_CHARACTERISTIC";
    public static final String BLACKLIST_PERIPHERALS = "BLACKLIST_PERIPHERALS";
    public static final String GENERATE_CBL_TOKEN = "GENERATE_CBL_TOKEN";
    public static final String SET_SMARTHOME_CREDENTIALS = "SET_SMARTHOME_CREDENTIALS";
    private static final String[] values = {WAIT, "END_SESSION", START_SCANNING, STOP_SCANNING, CONNECT_PERIPHERAL, DISCONNECT_PERIPHERAL, START_SERVICE_DISCOVERY, STOP_SERVICE_DISCOVERY, START_CHARACTERISTIC_DISCOVERY, STOP_CHARACTERISTIC_DISCOVERY, SUBSCRIBE_CHARACTERISTIC, UNSUBSCRIBE_CHARACTERISTIC, READ_CHARACTERISTIC, WRITE_CHARACTERISTIC, BLACKLIST_PERIPHERALS, GENERATE_CBL_TOKEN, SET_SMARTHOME_CREDENTIALS};

    private ActionType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
