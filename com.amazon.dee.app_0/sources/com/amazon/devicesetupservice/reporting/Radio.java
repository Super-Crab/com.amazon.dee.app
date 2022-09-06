package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class Radio {
    public static final String BLUETOOTH_LOW_ENERGY = "BluetoothLowEnergy";
    public static final String WIFI = "Wi-Fi";
    public static final String THREAD = "Thread";
    private static final String[] values = {BLUETOOTH_LOW_ENERGY, WIFI, THREAD};

    private Radio() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
