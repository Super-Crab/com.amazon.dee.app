package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class ShortValue implements Value {
    private short mValue;

    public ShortValue(short s) {
        this.mValue = s;
    }

    public short getValue() {
        return this.mValue;
    }

    public void setValue(short s) {
        this.mValue = s;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Short.valueOf(getValue());
    }

    public ShortValue() {
        this((short) 0);
    }
}
