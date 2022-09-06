package com.amazon.alexa.accessory.repositories.notification;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface NotificationProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleAddOutgoingNotification(int i, Notification.NotificationContent notificationContent, Producer.Result<Common.ErrorCode> result);

        void handleRemoveOutgoingNotification(int i, Producer.Result<Common.ErrorCode> result);

        void handleUpdateOutgoingNotification(int i, Notification.NotificationContent notificationContent, Producer.Result<Common.ErrorCode> result);
    }
}
