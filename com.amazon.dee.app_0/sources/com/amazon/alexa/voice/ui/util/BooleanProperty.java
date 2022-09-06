package com.amazon.alexa.voice.ui.util;
/* loaded from: classes11.dex */
public final class BooleanProperty extends BaseProperty {
    private volatile boolean hasValue;
    private volatile boolean value;

    public BooleanProperty() {
    }

    public void clear() {
        this.hasValue = false;
        notifyChanged();
    }

    public boolean get() {
        if (this.hasValue) {
            return this.value;
        }
        throw new IllegalStateException("Property does not hold any value");
    }

    public boolean hasValue() {
        return this.hasValue;
    }

    public void set(boolean z) {
        if (!this.hasValue || this.value != z) {
            this.value = z;
            this.hasValue = true;
            notifyChanged();
        }
    }

    public BooleanProperty(boolean z) {
        this.value = z;
        this.hasValue = true;
    }

    public boolean get(boolean z) {
        return this.hasValue ? this.value : z;
    }
}
