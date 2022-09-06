package com.amazon.alexa.handsfree.notification.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationScheduler;
import com.amazon.alexa.handsfree.notification.notifiers.Notifier;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public abstract class NotifierService extends SafeDequeueJobIntentService {
    private static final String TAG = NotifierService.class.getSimpleName();
    private final HandsFreeUserIdentity mHandsFreeUserIdentity;
    private final Initializer mInitializer;

    public NotifierService() {
        this.mInitializer = InitializerProvider.getInitializer();
        this.mHandsFreeUserIdentity = NotificationModule.getInstance().getHandsFreeUserIdentity();
    }

    public boolean isFeatureEnabled(NotificationType notificationType) {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUserIdentity;
        if (handsFreeUserIdentity == null) {
            return true;
        }
        if (!handsFreeUserIdentity.hasComponent(HandsFreeComponent.HANDS_FREE_NOTIFICATIONS)) {
            return false;
        }
        if (notificationType != NotificationType.LANGUAGE_SWITCHING) {
            return true;
        }
        return this.mHandsFreeUserIdentity.hasComponent(HandsFreeComponent.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING) && this.mHandsFreeUserIdentity.hasComponent(HandsFreeComponent.ALEXA_VOX_DLS);
    }

    public void notify(@Nullable Notifier notifier) {
        if (notifier == null) {
            Log.d(TAG, "No notifier required");
        } else if (isFeatureEnabled(notifier.getNotificationType()) && notifier.isNotificationRequired() && notifier.isNotificationAllowed()) {
            notifier.updateNotificationCount();
            notifier.showNotification();
        } else {
            Log.d(TAG, "Notification not required");
        }
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mInitializer.initialize(this);
    }

    public void schedule(@NonNull NotificationScheduler notificationScheduler) {
        if (notificationScheduler.isSchedulingRequired()) {
            notificationScheduler.scheduleNextNotification();
        }
    }

    @VisibleForTesting
    NotifierService(@NonNull Initializer initializer, @Nullable HandsFreeUserIdentity handsFreeUserIdentity) {
        this.mInitializer = initializer;
        this.mHandsFreeUserIdentity = handsFreeUserIdentity;
    }
}
