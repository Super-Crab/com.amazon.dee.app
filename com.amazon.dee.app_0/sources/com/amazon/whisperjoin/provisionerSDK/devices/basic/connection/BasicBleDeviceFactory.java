package com.amazon.whisperjoin.provisionerSDK.devices.basic.connection;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice;
/* loaded from: classes13.dex */
public class BasicBleDeviceFactory implements BLEConnectionFactory {
    private final BleManager mBleManager;
    private final Context mContext;

    public BasicBleDeviceFactory(Context context, BleManager bleManager) {
        this.mContext = context;
        this.mBleManager = bleManager;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.connection.BLEConnectionFactory
    public BasicBLEDevice createBleDevice(BluetoothDevice bluetoothDevice) {
        return new BasicBLEDevice(bluetoothDevice, this.mBleManager, this.mContext);
    }
}
