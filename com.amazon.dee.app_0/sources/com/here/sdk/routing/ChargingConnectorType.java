package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum ChargingConnectorType {
    IEC_62196_TYPE_1_COMBO(0),
    IEC_62196_TYPE_2_COMBO(1),
    CHADEMO(2),
    TESLA(3);
    
    public final int value;

    ChargingConnectorType(int i) {
        this.value = i;
    }
}
