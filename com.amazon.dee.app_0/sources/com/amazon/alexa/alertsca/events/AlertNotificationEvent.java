package com.amazon.alexa.alertsca.events;

import android.app.Notification;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.utils.validation.Preconditions;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlertNotificationEvent extends Event {
    public static AlertNotificationEvent create(AlertRecord alertRecord, Notification notification) {
        Preconditions.notNull(alertRecord, "alertRecord is null");
        Preconditions.notNull(notification, "notification is null");
        return new AutoValue_AlertNotificationEvent(alertRecord, notification);
    }

    public abstract AlertRecord getAlertRecord();

    public abstract Notification getNotification();
}
