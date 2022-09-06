package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class ByteValue implements Value {
    private byte mValue;

    public ByteValue(byte b) {
        this.mValue = b;
    }

    public byte getValue() {
        return this.mValue;
    }

    public void setValue(byte b) {
        this.mValue = b;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Byte.valueOf(getValue());
    }

    public ByteValue() {
        this((byte) 0);
    }
}
