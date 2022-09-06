package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.views;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.R;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.notificationmetadata.NotificationMetadata;
/* loaded from: classes11.dex */
public class AppDownloadNotification {
    private final AppDownloadNotificationPresenter mAppDownloadNotificationPresenter = new AppDownloadNotificationPresenter();
    private final String mChannelId;

    public AppDownloadNotification(@NonNull String str) {
        this.mChannelId = str;
    }

    public String getChannelId() {
        return this.mChannelId;
    }

    public Notification getNotification(@NonNull Context context) {
        return getNotificationHelper(context, this.mAppDownloadNotificationPresenter);
    }

    @VisibleForTesting
    public Notification getNotificationHelper(@NonNull Context context, AppDownloadNotificationPresenter appDownloadNotificationPresenter) {
        NotificationMetadata notificationMetadata = appDownloadNotificationPresenter.getNotificationMetadata();
        if (notificationMetadata == null) {
            Log.d("ContentValues", "Notification metadata is null, no notification required");
            return null;
        }
        String notificationText = notificationMetadata.getNotificationText(context);
        int notificationIconAsset = notificationMetadata.getNotificationIconAsset(context);
        PendingIntent contentIntent = appDownloadNotificationPresenter.getContentIntent(context);
        return new Notification.Builder(context, getChannelId()).setColor(ContextCompat.getColor(context, R.color.handsfree_setup_notification)).setSmallIcon(notificationIconAsset).setContentText(notificationText).setStyle(new Notification.BigTextStyle().bigText(notificationText)).setContentIntent(contentIntent).setDeleteIntent(appDownloadNotificationPresenter.getDismissIntent(context, notificationMetadata.getNotificationText(context))).setAutoCancel(true).build();
    }
}
