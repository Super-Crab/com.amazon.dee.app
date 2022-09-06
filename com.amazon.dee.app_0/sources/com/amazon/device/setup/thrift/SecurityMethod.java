package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum SecurityMethod implements TEnum {
    NONE(1),
    WPA_PSK(2),
    WPA2_PSK(3),
    WEP(4),
    WPA_EAP(5),
    WPA2_EAP(6);
    
    private final int value;

    SecurityMethod(int i) {
        this.value = i;
    }

    public static SecurityMethod findByValue(int i) {
        switch (i) {
            case 1:
                return NONE;
            case 2:
                return WPA_PSK;
            case 3:
                return WPA2_PSK;
            case 4:
                return WEP;
            case 5:
                return WPA_EAP;
            case 6:
                return WPA2_EAP;
            default:
                return null;
        }
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
