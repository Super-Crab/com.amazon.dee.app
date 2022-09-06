package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.alertsca.AlertRecord;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertScheduledEvent extends AlertEvent {
    public static AlertScheduledEvent create(AlertRecord alertRecord) {
        return new AutoValue_AlertScheduledEvent(alertRecord);
    }
}
