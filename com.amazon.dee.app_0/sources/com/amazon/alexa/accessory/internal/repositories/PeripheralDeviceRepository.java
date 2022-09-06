package com.amazon.alexa.accessory.internal.repositories;

import com.amazon.alexa.accessory.internal.PeripheralDevice;
import java.util.Set;
/* loaded from: classes.dex */
public interface PeripheralDeviceRepository {

    /* loaded from: classes.dex */
    public interface Callback {
        void onConnectedBluetoothDevicesFound(Set<PeripheralDevice> set);

        void onQueryFailed(Throwable th);
    }

    void queryConnectedBluetoothDevices(Callback callback);
}
