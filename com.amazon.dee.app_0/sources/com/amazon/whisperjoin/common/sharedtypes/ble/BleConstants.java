package com.amazon.whisperjoin.common.sharedtypes.ble;

import android.os.ParcelUuid;
import java.util.UUID;
/* loaded from: classes13.dex */
public class BleConstants {
    public static final UUID PROVISIONING_STATUS_CHARACTERISTIC_UUID = UUID.fromString("cf011df7-07ee-40b7-a71d-3f2eb6e0d040");
    public static final UUID START_PROVISIONING_REQUEST_CHARACTERISTIC_UUID = UUID.fromString("cf011df8-07ee-40b7-a71d-3f2eb6e0d040");
    public static final UUID START_PROVISIONING_RESPONSE_CHARACTERISTIC_UUID = UUID.fromString("cf011df9-07ee-40b7-a71d-3f2eb6e0d040");
    public static final UUID PROVISIONING_COMMAND_CHARACTERISTIC_UUID = UUID.fromString("cf011dfa-07ee-40b7-a71d-3f2eb6e0d040");
    public static final UUID PROVISIONING_COMMAND_RESPONSE_CHARACTERISTIC_UUID = UUID.fromString("cf011dfb-07ee-40b7-a71d-3f2eb6e0d040");
    public static final UUID PROVISIONING_NOTIFICATION_CHARACTERISTIC_UUID = UUID.fromString("cf011dfc-07ee-40b7-a71d-3f2eb6e0d040");
    private static final String BLUETOOTH_BASE_UUID_FORMATTED_STRING = "0000%s-0000-1000-8000-00805F9B34FB";
    private static final String WHISPER_JOIN_16BIT_UUID = "FE00";
    public static final UUID WHISPER_JOIN_UUID = UUID.fromString(String.format(BLUETOOTH_BASE_UUID_FORMATTED_STRING, WHISPER_JOIN_16BIT_UUID));
    public static final ParcelUuid WHISPER_JOIN_PARCEL_UUID = new ParcelUuid(WHISPER_JOIN_UUID);
}
