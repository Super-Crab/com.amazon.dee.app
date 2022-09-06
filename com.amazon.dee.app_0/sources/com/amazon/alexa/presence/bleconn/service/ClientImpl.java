package com.amazon.alexa.presence.bleconn.service;

import android.bluetooth.BluetoothDevice;
import java.util.Objects;
/* loaded from: classes9.dex */
public class ClientImpl implements Client {
    private final BluetoothDevice device;

    public ClientImpl(BluetoothDevice bluetoothDevice) {
        Objects.requireNonNull(bluetoothDevice);
        this.device = bluetoothDevice;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Client
    public String getAddress() {
        return this.device.getAddress();
    }
}
