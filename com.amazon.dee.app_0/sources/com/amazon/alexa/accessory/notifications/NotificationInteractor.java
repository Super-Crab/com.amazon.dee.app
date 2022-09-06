package com.amazon.alexa.accessory.notifications;
/* loaded from: classes.dex */
public interface NotificationInteractor {
    boolean areNotificationsEnabled();

    void cancel(int i);

    void show(int i, LocalNotification localNotification);
}
