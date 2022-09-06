package com.amazon.alexa.handsfree.notification.dependencies;

import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import dagger.Component;
@Component(modules = {FalcoNotificationsModule.class})
@FalcoNotificationsScope
/* loaded from: classes8.dex */
public interface FalcoNotificationsComponent {
    NotificationModule notificationModule();

    NotificationOccurrenceCounter notificationOccurrenceCounter();
}
