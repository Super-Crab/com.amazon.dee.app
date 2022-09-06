package com.amazon.alexa.eventbus.api;

import androidx.annotation.NonNull;
/* loaded from: classes7.dex */
public class EventBusException extends RuntimeException {
    public EventBusException(@NonNull Exception exc) {
        super(exc);
    }

    public EventBusException(@NonNull String str) {
        super(str);
    }
}
