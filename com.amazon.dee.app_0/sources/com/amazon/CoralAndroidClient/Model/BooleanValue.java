package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class BooleanValue implements Value {
    private boolean mValue;

    public BooleanValue(boolean z) {
        this.mValue = z;
    }

    public boolean getValue() {
        return this.mValue;
    }

    public void setValue(boolean z) {
        this.mValue = z;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Boolean.valueOf(getValue());
    }

    public BooleanValue() {
        this(false);
    }
}
