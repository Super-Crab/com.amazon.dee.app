package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
/* loaded from: classes13.dex */
public interface BluetoothGattFactory {
    BluetoothGatt createGattClient(Context context, BluetoothDevice bluetoothDevice, BluetoothGattCallback bluetoothGattCallback, boolean z);
}
