package com.amazon.alexa.voice.handsfree.initialization;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.NotificationModule;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.configurations.NotificationScheduler;
import com.amazon.alexa.voice.handsfree.settings.VoiceAppInstallExplainerActivity;
/* loaded from: classes11.dex */
public class VesperMikeNotificationInitializer {
    @VisibleForTesting
    static final String NOTIFICATIONS_WEBLAB = "AMPD_MIKE_VESPER_NOTIFICATIONS";
    private static final String TAG = "VesperMikeNotificationInitializer";
    private boolean mIsInitialized;
    private final NotificationModule mNotificationModule;

    /* loaded from: classes11.dex */
    private static class LazyHolder {
        private static final VesperMikeNotificationInitializer INSTANCE = new VesperMikeNotificationInitializer(NotificationModule.getInstance());

        private LazyHolder() {
        }
    }

    @VisibleForTesting
    VesperMikeNotificationInitializer(@NonNull NotificationModule notificationModule) {
        this.mNotificationModule = notificationModule;
    }

    @NonNull
    public static VesperMikeNotificationInitializer getInstance() {
        return LazyHolder.INSTANCE;
    }

    @VisibleForTesting
    synchronized void cancelNotifications(@NonNull Context context) {
        new NotificationScheduler(context).cancelScheduledNotifications();
        Log.d(TAG, "cancelNotifications: Cancel mike vesper notifications");
    }

    @VisibleForTesting
    AMPDInformationProvider getAMPDInformationProvider(@NonNull Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    public void initialize(@NonNull Context context) {
        initialize(context, getAMPDInformationProvider(context));
    }

    @VisibleForTesting
    synchronized void scheduleNotifications(@NonNull Context context) {
        if (!this.mIsInitialized) {
            this.mNotificationModule.initialize(context, new Intent().setComponent(new ComponentName(context.getApplicationContext(), VoiceAppInstallExplainerActivity.class)));
            Log.d(TAG, "scheduleNotifications: Initializing mike vesper notifications");
            this.mIsInitialized = true;
        }
    }

    public void initialize(@NonNull Context context, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        if (!aMPDInformationProvider.isVesper() || !aMPDInformationProvider.isHandsFreeMike()) {
            return;
        }
        scheduleNotifications(context);
    }
}
