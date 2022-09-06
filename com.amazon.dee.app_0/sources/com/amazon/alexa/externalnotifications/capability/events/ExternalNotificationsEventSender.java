package com.amazon.alexa.externalnotifications.capability.events;

import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import java.util.List;
/* loaded from: classes7.dex */
public interface ExternalNotificationsEventSender {
    void initialize();

    void sendNotificationsDismissedEvent(NotificationId notificationId);

    void sendNotificationsDismissedEvent(List<NotificationId> list);

    void sendNotificationsReadEvent(NotificationId notificationId);

    void sendNotificationsReadEvent(List<NotificationId> list);

    void sendNotificationsReceivedEvent(Notification notification);

    void sendNotificationsReceivedEvent(List<Notification> list);

    void sendPlayPendingNotificationsEvent();

    void teardown();
}
