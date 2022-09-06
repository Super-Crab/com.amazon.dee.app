package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.views;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.DismissIntentProvider;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.NotificationModule;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.notificationmetadata.NotificationMetadata;
/* loaded from: classes11.dex */
public class AppDownloadNotificationPresenter {
    private static final String EXPLAINER_ACTIVITY_CLASS = "com.amazon.alexa.voice.handsfree.settings.VoiceAppInstallExplainerActivity";
    private final NotificationMetadata mAppDownloadNotificationMetadata;
    private final DismissIntentProvider mDismissIntentProvider;
    private final NotificationModule mNotificationModule;

    public AppDownloadNotificationPresenter() {
        this(new DismissIntentProvider(), NotificationMetadata.PARTNER_VOICE_APP_DOWNLOAD, NotificationModule.getInstance());
    }

    @NonNull
    public PendingIntent getContentIntent(@NonNull Context context) {
        return PendingIntent.getActivity(context, 0, new Intent().setComponent(new ComponentName(context.getApplicationContext(), EXPLAINER_ACTIVITY_CLASS)).putExtra("extra_notification_text", this.mAppDownloadNotificationMetadata.getNotificationText(context)), 134217728);
    }

    public PendingIntent getDismissIntent(@NonNull Context context, @NonNull String str) {
        return this.mDismissIntentProvider.getDismissPendingIntent(context, str);
    }

    public NotificationMetadata getNotificationMetadata() {
        return this.mAppDownloadNotificationMetadata;
    }

    public AppDownloadNotificationPresenter(@NonNull DismissIntentProvider dismissIntentProvider, @NonNull NotificationMetadata notificationMetadata, @NonNull NotificationModule notificationModule) {
        this.mDismissIntentProvider = dismissIntentProvider;
        this.mAppDownloadNotificationMetadata = notificationMetadata;
        this.mNotificationModule = notificationModule;
    }
}
