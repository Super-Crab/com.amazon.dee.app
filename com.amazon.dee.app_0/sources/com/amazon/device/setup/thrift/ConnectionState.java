package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum ConnectionState implements TEnum {
    NOT_CONNECTED(1),
    CONNECTED(2),
    OUTOFRANGE(3),
    UNKNOWN(4);
    
    private final int value;

    ConnectionState(int i) {
        this.value = i;
    }

    public static ConnectionState findByValue(int i) {
        if (i != 1) {
            if (i == 2) {
                return CONNECTED;
            }
            if (i == 3) {
                return OUTOFRANGE;
            }
            if (i == 4) {
                return UNKNOWN;
            }
            return null;
        }
        return NOT_CONNECTED;
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
