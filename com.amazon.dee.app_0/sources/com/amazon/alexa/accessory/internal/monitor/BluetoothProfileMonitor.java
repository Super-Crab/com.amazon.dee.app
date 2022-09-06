package com.amazon.alexa.accessory.internal.monitor;

import com.amazon.alexa.accessory.internal.PeripheralDevice;
/* loaded from: classes.dex */
public interface BluetoothProfileMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onProfileConnected(PeripheralDevice peripheralDevice, int i);

        void onProfileDisconnected(PeripheralDevice peripheralDevice, int i);
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
