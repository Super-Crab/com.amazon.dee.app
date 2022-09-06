package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.internal.PeripheralDevice;
/* loaded from: classes.dex */
public interface LowEnergyScanner {

    /* loaded from: classes.dex */
    public interface Listener {
        void onPeripheralFound(PeripheralDevice peripheralDevice, BleAdvertisementData bleAdvertisementData, int i);

        void onScanCancelled();

        void onScanCompleted();

        void onScanFailed(int i);

        void onScanStarted();
    }

    void cancelScan(Listener listener);

    boolean isScanning(Listener listener);

    boolean startScan(Listener listener);
}
