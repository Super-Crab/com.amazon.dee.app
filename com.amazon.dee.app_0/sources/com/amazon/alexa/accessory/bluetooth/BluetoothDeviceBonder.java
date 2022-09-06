package com.amazon.alexa.accessory.bluetooth;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes.dex */
public interface BluetoothDeviceBonder {

    /* loaded from: classes.dex */
    public interface Callback {
        void onBondCompleted(BluetoothDevice bluetoothDevice);

        void onBondFailed(BluetoothDevice bluetoothDevice);
    }

    void cancel();

    void cancel(BluetoothDevice bluetoothDevice);

    void createBond(BluetoothDevice bluetoothDevice, int i, Callback callback);
}
