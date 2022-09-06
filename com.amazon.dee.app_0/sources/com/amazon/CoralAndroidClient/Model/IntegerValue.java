package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class IntegerValue implements Value {
    private int mValue;

    public IntegerValue(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    public void setValue(int i) {
        this.mValue = i;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Integer.valueOf(getValue());
    }

    public IntegerValue() {
        this(0);
    }
}
