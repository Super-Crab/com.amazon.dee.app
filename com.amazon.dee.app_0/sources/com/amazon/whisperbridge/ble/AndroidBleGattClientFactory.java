package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes13.dex */
public class AndroidBleGattClientFactory implements BluetoothGattFactory {
    private static final String CONNECT_GATT_METHOD_NAME = "connectGatt";
    private static final String TAG = "com.amazon.whisperbridge.ble.AndroidBleGattClientFactory";

    @Override // com.amazon.whisperbridge.ble.BluetoothGattFactory
    public BluetoothGatt createGattClient(Context context, BluetoothDevice bluetoothDevice, BluetoothGattCallback bluetoothGattCallback, boolean z) {
        try {
            return (BluetoothGatt) BluetoothDevice.class.getMethod(CONNECT_GATT_METHOD_NAME, Context.class, Boolean.TYPE, BluetoothGattCallback.class, Integer.TYPE).invoke(bluetoothDevice, context, Boolean.valueOf(z), bluetoothGattCallback, 2);
        } catch (IllegalAccessException e) {
            WJLog.e(TAG, "IllegalAccessException in createGattClientWithCommandQueue() ", e);
            return null;
        } catch (NoSuchMethodException e2) {
            WJLog.e(TAG, "NoSuchMethodException in createGattClientWithCommandQueue() ", e2);
            return null;
        } catch (InvocationTargetException e3) {
            WJLog.e(TAG, "InvocationTargetException in createGattClientWithCommandQueue() ", e3);
            return null;
        }
    }
}
