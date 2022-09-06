package com.amazon.alexa.voice.ui.util;
/* loaded from: classes11.dex */
public class AlexaStateProperty extends BaseProperty {
    private volatile boolean hasValue;
    private volatile AlexaState value;

    public AlexaStateProperty() {
    }

    public void clear() {
        this.hasValue = false;
        notifyChanged();
    }

    public AlexaState get() {
        if (this.hasValue) {
            return this.value;
        }
        throw new IllegalStateException("Property does not hold any value");
    }

    public boolean hasValue() {
        return this.hasValue;
    }

    public void set(AlexaState alexaState) {
        if (!this.hasValue || this.value != alexaState) {
            this.value = alexaState;
            this.hasValue = true;
            notifyChanged();
        }
    }

    public AlexaStateProperty(AlexaState alexaState) {
        this.value = alexaState;
        this.hasValue = true;
    }

    public AlexaState get(AlexaState alexaState) {
        return this.hasValue ? this.value : alexaState;
    }
}
