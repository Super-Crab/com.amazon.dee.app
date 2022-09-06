package com.amazon.alexa.accessory.internal.monitor;

import android.bluetooth.BluetoothSocket;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
/* loaded from: classes.dex */
public interface SppSocketConnectionMonitor {

    /* loaded from: classes.dex */
    public interface Listener {
        void onSocketConnected(PeripheralDevice peripheralDevice, BluetoothSocket bluetoothSocket);
    }

    void setListener(Listener listener);

    void unsetListener(Listener listener);
}
