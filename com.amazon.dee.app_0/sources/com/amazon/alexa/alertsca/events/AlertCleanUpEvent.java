package com.amazon.alexa.alertsca.events;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertCleanUpEvent extends Event {
    public static AlertCleanUpEvent create(int i) {
        return new AutoValue_AlertCleanUpEvent(i);
    }

    public abstract int getAlertRecordId();
}
