package com.amazon.alexa.voice.handsfree.mike.vesper.notifications;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.channels.NotificationChannelManager;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations.NotificationScheduler;
/* loaded from: classes11.dex */
public class NotificationModule {
    private boolean mIsInitialized;
    private Intent mNotificationClickIntent;

    /* loaded from: classes11.dex */
    private static class LazyHolder {
        private static final NotificationModule INSTANCE = new NotificationModule();

        private LazyHolder() {
        }
    }

    @NonNull
    public static NotificationModule getInstance() {
        return LazyHolder.INSTANCE;
    }

    @NonNull
    public Intent getNotificationClickIntent() {
        return this.mNotificationClickIntent;
    }

    public synchronized void initialize(@NonNull Context context, @NonNull Intent intent) {
        if (!this.mIsInitialized) {
            new NotificationChannelManager(context).createNotificationChannel();
            new NotificationScheduler(context).scheduleNextNotification();
            Log.d("NotificationModule", "initialize: Mike Vesper notifications initialized");
            this.mNotificationClickIntent = intent;
            this.mIsInitialized = true;
        }
    }

    private NotificationModule() {
    }
}
