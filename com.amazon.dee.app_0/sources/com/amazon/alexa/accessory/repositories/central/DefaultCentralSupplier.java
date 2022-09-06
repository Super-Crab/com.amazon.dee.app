package com.amazon.alexa.accessory.repositories.central;

import android.bluetooth.BluetoothAdapter;
/* loaded from: classes6.dex */
public class DefaultCentralSupplier implements CentralSupplier {
    @Override // com.amazon.alexa.accessory.repositories.central.CentralSupplier
    public String getName() {
        return BluetoothAdapter.getDefaultAdapter().getName();
    }
}
