package com.amazon.alexa.accessory.repositories.notification;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Notification;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface NotificationRepository {
    Single<Common.ErrorCode> addOutgoingNotification(int i, Notification.NotificationContent notificationContent);

    Flowable<Notification.ExecuteNotificationAction> queryActionCommandsForOutgoingNotifications();

    Single<Common.ErrorCode> removeOutgoingNotification(int i);

    Single<Common.ErrorCode> updateOutgoingNotification(int i, Notification.NotificationContent notificationContent);
}
