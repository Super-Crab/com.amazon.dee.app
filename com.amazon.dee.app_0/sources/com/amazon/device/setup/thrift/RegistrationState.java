package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum RegistrationState implements TEnum {
    NOT_REGISTERED(1),
    REGISTERED(2),
    UNKNOWN(3);
    
    private final int value;

    RegistrationState(int i) {
        this.value = i;
    }

    public static RegistrationState findByValue(int i) {
        if (i != 1) {
            if (i == 2) {
                return REGISTERED;
            }
            if (i == 3) {
                return UNKNOWN;
            }
            return null;
        }
        return NOT_REGISTERED;
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
