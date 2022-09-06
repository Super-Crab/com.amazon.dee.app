package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations.NotificationScheduler;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.notifiers.AppDownloadNotifier;
/* loaded from: classes11.dex */
public class AppDownloadNotifierService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 30004;
    private static final String TAG = AppDownloadNotifierService.class.getSimpleName();
    private AppDownloadNotifier mAppDownloadNotifier;
    private NotificationScheduler mNotificationScheduler;

    public AppDownloadNotifierService() {
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, AppDownloadNotifierService.class, (int) JOB_ID, intent);
    }

    @VisibleForTesting
    void handleNotification() {
        if (this.mAppDownloadNotifier.isNotificationRequired()) {
            Log.d(TAG, "notification is required.");
            this.mAppDownloadNotifier.updateNotificationCount();
            this.mAppDownloadNotifier.showNotification();
            this.mNotificationScheduler.scheduleNextNotification();
        }
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        InitializerProvider.getInitializer().initialize(this);
        this.mAppDownloadNotifier = new AppDownloadNotifier(this);
        this.mNotificationScheduler = new NotificationScheduler(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        InitializerProvider.getInitializer().initialize(this);
        handleNotification();
    }

    @VisibleForTesting
    AppDownloadNotifierService(@NonNull AppDownloadNotifier appDownloadNotifier, @NonNull NotificationScheduler notificationScheduler) {
        this.mAppDownloadNotifier = appDownloadNotifier;
        this.mNotificationScheduler = notificationScheduler;
    }
}
