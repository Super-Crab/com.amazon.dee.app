package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.alertsca.AlertRecord;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertStoppedEvent extends AlertEvent {
    public static AlertStoppedEvent create(AlertRecord alertRecord) {
        return new AutoValue_AlertStoppedEvent(alertRecord, false);
    }

    public abstract boolean isDismissedByUser();

    public static AlertStoppedEvent create(AlertRecord alertRecord, boolean z) {
        return new AutoValue_AlertStoppedEvent(alertRecord, z);
    }
}
