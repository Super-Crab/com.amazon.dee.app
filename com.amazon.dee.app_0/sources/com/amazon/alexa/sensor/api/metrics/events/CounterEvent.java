package com.amazon.alexa.sensor.api.metrics.events;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public class CounterEvent extends Event {
    protected int value;

    public CounterEvent(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        super(str, str2, str3);
        setValue(0);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }
}
