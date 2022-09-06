package com.amazon.alexa.accessory.notificationpublisher.voice;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.providers.ThinTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.voice.ampd.NoOpDirectiveListener;
import com.amazon.alexa.accessory.notificationpublisher.voice.ampd.NoOpNotificationProvider;
import com.amazon.alexa.accessory.notificationpublisher.voice.ampd.NoOpVoiceMetricsRecorder;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import java.util.Set;
/* loaded from: classes.dex */
public class AmpdVoiceModule {
    private static final String TAG = "AmpdVoiceModule";
    private final ExternalNotificationsCapabilityAgent capabilityAgent;
    private final NotificationKeyToIdsMap notificationKeyToIdsMap;
    private final NotificationProcessor notificationProcessor;

    public AmpdVoiceModule(Context context) {
        ThinTemplateProvider.init(context);
        this.notificationProcessor = new NotificationProcessor(context, ThinTemplateProvider.getInstance(), null);
        this.capabilityAgent = new ExternalNotificationsCapabilityAgent(context, new NoOpNotificationProvider(), new NoOpDirectiveListener(), new NoOpVoiceMetricsRecorder());
        this.notificationKeyToIdsMap = new NotificationKeyToIdsMap();
    }

    public void initialize() {
        this.capabilityAgent.initialize();
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        Log.i(TAG, "onNotificationPosted");
        Notification processStatusBarNotification = this.notificationProcessor.processStatusBarNotification(statusBarNotification);
        if (processStatusBarNotification == null) {
            Log.w(TAG, "onNotificationPosted - processed notification is null");
            return;
        }
        this.notificationKeyToIdsMap.put(statusBarNotification.getKey(), processStatusBarNotification.getNotificationId());
        this.capabilityAgent.onNotificationReceived(processStatusBarNotification);
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        Log.i(TAG, "onNotificationRemoved");
        Notification processStatusBarNotification = this.notificationProcessor.processStatusBarNotification(statusBarNotification);
        if (processStatusBarNotification == null) {
            Log.w(TAG, "onNotificationRemoved - processed notification is null");
            return;
        }
        Set<NotificationId> andRemove = this.notificationKeyToIdsMap.getAndRemove(statusBarNotification.getKey());
        andRemove.add(processStatusBarNotification.getNotificationId());
        for (NotificationId notificationId : andRemove) {
            this.capabilityAgent.onNotificationDismissed(notificationId);
        }
    }

    public void teardown() {
        this.capabilityAgent.destroy();
    }

    public void uploadActiveNotifications(StatusBarNotification[] statusBarNotificationArr) {
        Log.i(TAG, "uploadActiveNotifications");
        Preconditions.checkNotNull(statusBarNotificationArr, "activeNotifications is null.");
        NotificationProcessor notificationProcessor = this.notificationProcessor;
        if (notificationProcessor != null && this.capabilityAgent != null) {
            this.capabilityAgent.uploadActiveNotifications(notificationProcessor.processStatusBarNotifications(statusBarNotificationArr));
            return;
        }
        Log.e(TAG, "Either notificationProvider or capabilityAgent is null.");
    }
}
