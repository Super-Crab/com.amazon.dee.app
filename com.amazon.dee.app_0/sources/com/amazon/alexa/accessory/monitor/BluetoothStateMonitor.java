package com.amazon.alexa.accessory.monitor;
/* loaded from: classes.dex */
public interface BluetoothStateMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onBluetoothDisabled();

        void onBluetoothEnabled();
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
