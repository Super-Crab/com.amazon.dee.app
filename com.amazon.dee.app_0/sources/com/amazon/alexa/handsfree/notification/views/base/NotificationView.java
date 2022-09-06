package com.amazon.alexa.handsfree.notification.views.base;

import android.app.Notification;
import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public abstract class NotificationView {
    private final String mChannelId;
    private final int mNotificationId;
    private final NotificationViewPresenter mNotificationViewPresenter;

    public NotificationView(@NonNull String str, @NonNull NotificationViewPresenter notificationViewPresenter, int i) {
        this.mChannelId = str;
        this.mNotificationViewPresenter = notificationViewPresenter;
        this.mNotificationId = i;
    }

    public String getChannelId() {
        return this.mChannelId;
    }

    public abstract Notification getNotification(@NonNull Context context);

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public NotificationViewPresenter getPresenter() {
        return this.mNotificationViewPresenter;
    }
}
