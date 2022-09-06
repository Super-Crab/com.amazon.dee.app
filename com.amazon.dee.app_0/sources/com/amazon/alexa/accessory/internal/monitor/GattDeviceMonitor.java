package com.amazon.alexa.accessory.internal.monitor;

import com.amazon.alexa.accessory.internal.PeripheralDevice;
/* loaded from: classes.dex */
public interface GattDeviceMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onConnected(PeripheralDevice peripheralDevice);

        void onDisconnected(PeripheralDevice peripheralDevice);
    }

    void addObserver(Observer observer);

    void cancel(PeripheralDevice peripheralDevice);

    void monitor(PeripheralDevice peripheralDevice);

    void removeObserver(Observer observer);
}
