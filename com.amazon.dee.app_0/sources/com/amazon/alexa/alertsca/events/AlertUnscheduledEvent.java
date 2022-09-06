package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.alertsca.AlertRecord;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertUnscheduledEvent extends AlertEvent {
    public static AlertUnscheduledEvent create(AlertRecord alertRecord) {
        return create(alertRecord, false);
    }

    public abstract boolean isDismissedByUser();

    public static AlertUnscheduledEvent create(AlertRecord alertRecord, boolean z) {
        return new AutoValue_AlertUnscheduledEvent(alertRecord, z);
    }
}
