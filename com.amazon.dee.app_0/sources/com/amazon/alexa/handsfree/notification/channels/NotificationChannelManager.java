package com.amazon.alexa.handsfree.notification.channels;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class NotificationChannelManager {
    private final NotificationManager mNotificationManager;

    public NotificationChannelManager(@NonNull Context context) {
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    @VisibleForTesting
    void createNotificationChannel(@NonNull NotificationChannelProperties notificationChannelProperties) {
        if (this.mNotificationManager.getNotificationChannel(notificationChannelProperties.getChannelId()) == null) {
            NotificationChannel notificationChannel = getNotificationChannel(notificationChannelProperties);
            notificationChannel.enableVibration(false);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void createNotificationChannels() {
        createNotificationChannel(NotificationChannelProperties.HANDS_FREE_SETUP);
    }

    @NonNull
    @VisibleForTesting
    NotificationChannel getNotificationChannel(@NonNull NotificationChannelProperties notificationChannelProperties) {
        return new NotificationChannel(notificationChannelProperties.getChannelId(), notificationChannelProperties.getChannelName(), notificationChannelProperties.getImportance());
    }
}
