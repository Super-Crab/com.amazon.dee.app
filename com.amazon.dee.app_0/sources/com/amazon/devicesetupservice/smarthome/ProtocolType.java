package com.amazon.devicesetupservice.smarthome;
/* loaded from: classes12.dex */
public class ProtocolType {
    public static final String CHIP = "CHIP";
    public static final String ZIGBEE = "ZIGBEE";
    public static final String BLE = "BLE";
    public static final String BLE_MESH = "BLE_MESH";
    public static final String WIFI = "WIFI";
    public static final String THREAD = "THREAD";
    private static final String[] values = {ZIGBEE, BLE, BLE_MESH, WIFI, "CHIP", THREAD};

    private ProtocolType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
