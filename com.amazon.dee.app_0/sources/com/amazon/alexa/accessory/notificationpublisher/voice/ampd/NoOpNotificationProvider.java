package com.amazon.alexa.accessory.notificationpublisher.voice.ampd;

import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsProvider;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class NoOpNotificationProvider implements ExternalNotificationsProvider {
    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsProvider
    public List<Notification> getActiveNotifications() {
        return Collections.emptyList();
    }
}
