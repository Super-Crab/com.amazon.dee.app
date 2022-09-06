package com.amazon.alexa.accessory.internal.monitor;

import com.amazon.alexa.accessory.internal.PeripheralDevice;
/* loaded from: classes.dex */
public interface AclDeviceMonitor {

    /* loaded from: classes.dex */
    public interface Observer {
        void onAclConnected(PeripheralDevice peripheralDevice);

        void onAclDisconnected(PeripheralDevice peripheralDevice);
    }

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
