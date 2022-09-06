package com.amazon.alexa.sensor.api.metrics.events;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public abstract class Event {
    @NonNull
    public final String component;
    @NonNull
    public final String name;
    @NonNull
    public final String subComponent;

    /* JADX INFO: Access modifiers changed from: protected */
    public Event(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.name = str;
        this.component = str2;
        this.subComponent = str3;
    }
}
