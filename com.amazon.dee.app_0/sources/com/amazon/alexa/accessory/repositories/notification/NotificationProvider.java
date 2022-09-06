package com.amazon.alexa.accessory.repositories.notification;

import com.amazon.alexa.accessory.protocol.Notification;
/* loaded from: classes6.dex */
public interface NotificationProvider {
    void provideActionCommandsForOutgoingNotification(Notification.ExecuteNotificationAction executeNotificationAction);
}
