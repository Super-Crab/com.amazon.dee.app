package com.amazon.alexa.accessory.internal.monitor;

import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.PeripheralDevices;
/* loaded from: classes.dex */
public interface BluetoothBondMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onBondRemoved(PeripheralDevice peripheralDevice);

        void onBondedAdded(PeripheralDevice peripheralDevice);
    }

    void addObserver(Observer observer);

    PeripheralDevices getBondedDevices();

    void removeObserver(Observer observer);
}
