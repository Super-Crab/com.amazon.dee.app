package com.amazon.whisperjoin.common.sharedtypes.smarthome.data;
/* loaded from: classes13.dex */
public enum ProtocolType {
    ZIGBEE(com.amazon.devicesetupservice.smarthome.ProtocolType.ZIGBEE),
    WIFI(com.amazon.devicesetupservice.smarthome.ProtocolType.WIFI),
    BLE(com.amazon.devicesetupservice.smarthome.ProtocolType.BLE);
    
    private final String mName;

    ProtocolType(String str) {
        this.mName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
