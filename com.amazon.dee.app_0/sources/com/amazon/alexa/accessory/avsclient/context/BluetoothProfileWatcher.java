package com.amazon.alexa.accessory.avsclient.context;

import android.bluetooth.BluetoothDevice;
import java.util.List;
/* loaded from: classes.dex */
public interface BluetoothProfileWatcher {
    void deactivate();

    void ensureActive(int i);

    List<BluetoothDevice> getActiveDevices();
}
