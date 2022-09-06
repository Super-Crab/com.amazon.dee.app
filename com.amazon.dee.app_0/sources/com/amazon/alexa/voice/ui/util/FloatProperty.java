package com.amazon.alexa.voice.ui.util;
/* loaded from: classes11.dex */
public final class FloatProperty extends BaseProperty {
    private volatile boolean hasValue;
    private volatile float value;

    public FloatProperty() {
    }

    public void clear() {
        this.hasValue = false;
        notifyChanged();
    }

    public float get() {
        if (this.hasValue) {
            return this.value;
        }
        throw new IllegalStateException("Property does not hold any value");
    }

    public boolean hasValue() {
        return this.hasValue;
    }

    public void set(float f) {
        if (!this.hasValue || this.value != f) {
            this.value = f;
            this.hasValue = true;
            notifyChanged();
        }
    }

    public FloatProperty(float f) {
        this.value = f;
        this.hasValue = true;
    }

    public float get(float f) {
        return this.hasValue ? this.value : f;
    }
}
