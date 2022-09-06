package com.amazon.whisperjoin.provisionerSDK.devices.basic.connection;

import android.bluetooth.BluetoothDevice;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice;
/* loaded from: classes13.dex */
public interface BLEConnectionFactory {
    BasicBLEDevice createBleDevice(BluetoothDevice bluetoothDevice);
}
