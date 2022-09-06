package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.channels;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes11.dex */
public class NotificationChannelManager {
    private final Context mContext;
    private final NotificationManager mNotificationManager;

    public NotificationChannelManager(@NonNull Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public void createNotificationChannel() {
        NotificationChannelProperties notificationChannelProperties = NotificationChannelProperties.HANDS_FREE_SETUP;
        if (this.mNotificationManager.getNotificationChannel(notificationChannelProperties.getChannelId()) == null) {
            NotificationChannel notificationChannel = getNotificationChannel(notificationChannelProperties);
            notificationChannel.enableVibration(false);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @NonNull
    @VisibleForTesting
    NotificationChannel getNotificationChannel(@NonNull NotificationChannelProperties notificationChannelProperties) {
        return new NotificationChannel(notificationChannelProperties.getChannelId(), this.mContext.getString(notificationChannelProperties.getChannelNameStringRes()), notificationChannelProperties.getImportance());
    }
}
