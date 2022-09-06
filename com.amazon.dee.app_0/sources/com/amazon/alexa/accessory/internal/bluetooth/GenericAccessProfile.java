package com.amazon.alexa.accessory.internal.bluetooth;

import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public interface GenericAccessProfile {
    public static final byte APPEARANCE = 25;
    public static final byte COMPLETE_LIST_UUIDS_128BIT = 7;
    public static final byte COMPLETE_LIST_UUIDS_16BIT = 3;
    public static final byte COMPLETE_LIST_UUIDS_32BIT = 5;
    public static final byte DEVICE_CLASS = 13;
    public static final byte DEVICE_ID = 16;
    public static final byte FLAGS = 1;
    public static final byte INCOMPLETE_LIST_UUIDS_128BIT = 6;
    public static final byte INCOMPLETE_LIST_UUIDS_16BIT = 2;
    public static final byte INCOMPLETE_LIST_UUIDS_32BIT = 4;
    public static final byte LE_BLUETOOTH_DEVICE_ADDRESS = 27;
    public static final byte LE_ROLE = 28;
    public static final byte LOCAL_NAME = 9;
    public static final byte MANUFACTURER_SPECIFIC_DATA = -1;
    public static final byte PUBLIC_TARGET_ADDRESS = 23;
    public static final byte RANDOM_TARGET_ADDRESS = 24;
    public static final byte SECURITY_MANAGER_OUT_OF_BAND = 17;
    public static final byte SERVICE_DATA = 22;
    public static final byte SERVICE_DATA_32BIT = 32;
    public static final byte SERVICE_SOLICITATION_UUIDS_128BIT = 21;
    public static final byte SERVICE_SOLICITATION_UUIDS_16BIT = 20;
    public static final byte SERVICE_SOLIICITATION_UUIDS_32BIT = 31;
    public static final byte SHORT_NAME = 8;
    public static final byte SIMPLE_PAIRING_HASH_C = 14;
    public static final byte SIMPLE_PAIRING_HASH_C256 = 29;
    public static final byte SIMPLE_PAIRING_RANDOMIZER_R = 15;
    public static final byte SIMPLE_PAIRING_RANDOMIZER_R256 = 30;
    public static final byte SLAVE_CONNECTION_INTERVAL_RANGE = 18;
    public static final byte TX_POWER_LEVEL = 10;
    public static final byte SERVICE_DATA_128BIT = 33;
    public static final byte LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE = 34;
    public static final byte LE_SECURE_CONNECTIONS_RANDOM_VALUE = 35;
    public static final byte URI = 36;
    public static final byte INDOOR_POSITIONING = 37;
    public static final byte TRANSPORT_DISCOVERY_DATA = 38;
    public static final byte LE_SUPPORTED_FEATURES = 39;
    public static final byte CHANNEL_MAP_UPDATE_INDICATION = 40;
    public static final byte PB_ADV = 41;
    public static final byte MESH_MESSAGE = 42;
    public static final byte MESH_BEACON = 43;
    public static final byte INFORMATION_DATA_3D = 61;
    public static final List<Byte> GAP_ASSIGNED_NUMBERS = Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, Byte.valueOf((byte) SERVICE_DATA_128BIT), Byte.valueOf((byte) LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE), Byte.valueOf((byte) LE_SECURE_CONNECTIONS_RANDOM_VALUE), Byte.valueOf((byte) URI), Byte.valueOf((byte) INDOOR_POSITIONING), Byte.valueOf((byte) TRANSPORT_DISCOVERY_DATA), Byte.valueOf((byte) LE_SUPPORTED_FEATURES), Byte.valueOf((byte) CHANNEL_MAP_UPDATE_INDICATION), Byte.valueOf((byte) PB_ADV), Byte.valueOf((byte) MESH_MESSAGE), Byte.valueOf((byte) MESH_BEACON), Byte.valueOf((byte) INFORMATION_DATA_3D), (byte) -1);
}
