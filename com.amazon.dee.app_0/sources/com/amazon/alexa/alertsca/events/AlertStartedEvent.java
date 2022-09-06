package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.alertsca.AlertRecord;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertStartedEvent extends AlertEvent {
    public static AlertStartedEvent create(AlertRecord alertRecord) {
        return new AutoValue_AlertStartedEvent(alertRecord);
    }
}
