package com.amazon.whisperbridge.ble;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
/* loaded from: classes13.dex */
public class BluetoothDisabledException extends Exception {
    private static final String TAG = BluetoothDisabledException.class.getSimpleName();

    public BluetoothDisabledException() {
        super("Bluetooth is not enabled. Please prompt user for permission and enable Bluetooth before calling initializeBluetoothLE.");
        WJLog.e(TAG, getMessage());
    }
}
