package com.amazon.dee.app.services.bluetooth;

import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public interface BluetoothService {
    List<? extends Map<String, String>> getBluetoothDevices();

    Observable<List<? extends Map<String, String>>> onBluetoothDevicesChanged();
}
