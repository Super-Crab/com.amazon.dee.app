package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class FloatValue implements Value {
    private float mValue;

    public FloatValue(float f) {
        this.mValue = f;
    }

    public float getValue() {
        return this.mValue;
    }

    public void setValue(float f) {
        this.mValue = f;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Float.valueOf(getValue());
    }

    public FloatValue() {
        this(0.0f);
    }
}
