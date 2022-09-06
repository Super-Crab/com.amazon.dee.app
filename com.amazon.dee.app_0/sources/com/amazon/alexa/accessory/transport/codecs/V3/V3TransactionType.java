package com.amazon.alexa.accessory.transport.codecs.V3;

import com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec;
/* loaded from: classes6.dex */
public enum V3TransactionType {
    FIRST_PACKET(0),
    NORMAL_PACKET(1),
    FINAL_PACKET(2),
    CONTROL_PACKET(3);
    
    private final int value;
    private static final V3TransactionType[] VALUES = {FIRST_PACKET, NORMAL_PACKET, FINAL_PACKET, CONTROL_PACKET};

    V3TransactionType(int i) {
        this.value = i;
    }

    public static V3TransactionType fromValue(int i) throws V3TransportCodec.InvalidTransactionTypeException {
        if (i >= 0) {
            V3TransactionType[] v3TransactionTypeArr = VALUES;
            if (i < v3TransactionTypeArr.length) {
                return v3TransactionTypeArr[i];
            }
        }
        throw new V3TransportCodec.InvalidTransactionTypeException(i);
    }

    public int getValue() {
        return this.value;
    }
}
