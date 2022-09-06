package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum LinkCodeSource implements TEnum {
    DMS(1),
    PANDA(2);
    
    private final int value;

    LinkCodeSource(int i) {
        this.value = i;
    }

    public static LinkCodeSource findByValue(int i) {
        if (i != 1) {
            if (i == 2) {
                return PANDA;
            }
            return null;
        }
        return DMS;
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
