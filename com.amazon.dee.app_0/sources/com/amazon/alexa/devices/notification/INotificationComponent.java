package com.amazon.alexa.devices.notification;

import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface INotificationComponent {
    <N> void subscribe(NotificationSubscription<N> notificationSubscription) throws AlexaException;

    <N> void unsubscribe(NotificationSubscription<N> notificationSubscription) throws AlexaException;
}
