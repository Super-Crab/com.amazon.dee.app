package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum AlexaConnectionState implements TEnum {
    DISCONNECTED(1),
    CONNECTED(2),
    UNKNOWN(3);
    
    private final int value;

    AlexaConnectionState(int i) {
        this.value = i;
    }

    public static AlexaConnectionState findByValue(int i) {
        if (i != 1) {
            if (i == 2) {
                return CONNECTED;
            }
            if (i == 3) {
                return UNKNOWN;
            }
            return null;
        }
        return DISCONNECTED;
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
