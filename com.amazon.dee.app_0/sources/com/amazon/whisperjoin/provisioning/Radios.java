package com.amazon.whisperjoin.provisioning;
/* loaded from: classes13.dex */
public enum Radios {
    BLE("BluetoothLE"),
    SOFT_AP("SoftAP");
    
    public final String name;

    Radios(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
