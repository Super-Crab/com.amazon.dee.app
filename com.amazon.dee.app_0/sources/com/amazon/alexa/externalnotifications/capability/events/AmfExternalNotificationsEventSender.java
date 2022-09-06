package com.amazon.alexa.externalnotifications.capability.events;

import android.util.Log;
import androidx.core.util.Preconditions;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgentConstants;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes7.dex */
public class AmfExternalNotificationsEventSender implements ExternalNotificationsEventSender {
    private static final String TAG = "AmfExternalNotificationsEventSender";
    private final AlexaMobileFrameworkApis amfApis;
    private final Gson gson;
    private final AtomicBoolean isInitialized;
    private final ExternalNotificationsMetricsRecorder metricsRecorder;

    public AmfExternalNotificationsEventSender(Gson gson, AlexaMobileFrameworkApis alexaMobileFrameworkApis, ExternalNotificationsMetricsRecorder externalNotificationsMetricsRecorder) {
        Preconditions.checkNotNull(gson, "Gson cannot be null");
        Preconditions.checkNotNull(alexaMobileFrameworkApis, "AlexaMobileFrameworkApis cannot be null");
        this.isInitialized = new AtomicBoolean(false);
        this.gson = gson;
        this.amfApis = alexaMobileFrameworkApis;
        this.metricsRecorder = externalNotificationsMetricsRecorder;
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void initialize() {
        this.amfApis.start();
        this.isInitialized.set(true);
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendNotificationsDismissedEvent(NotificationId notificationId) {
        Preconditions.checkNotNull(notificationId, "NotificationId cannot be null");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendNotificationsDismissedEvent with a notificationId, AmfExternalNotificationsEventSender is not initialized");
        } else {
            sendNotificationsDismissedEvent(Collections.singletonList(notificationId));
        }
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendNotificationsReadEvent(NotificationId notificationId) {
        Preconditions.checkNotNull(notificationId, "NotificationId cannot be null");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendNotificationsReadEvent with a notificationId, AmfExternalNotificationsEventSender is not initialized");
        } else {
            sendNotificationsReadEvent(Collections.singletonList(notificationId));
        }
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendNotificationsReceivedEvent(Notification notification) {
        Preconditions.checkNotNull(notification, "Notification cannot be null");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendNotificationsReceivedEvent with a notificationId, AmfExternalNotificationsEventSender is not initialized");
        } else {
            sendNotificationsReceivedEvent(Collections.singletonList(notification));
        }
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendPlayPendingNotificationsEvent() {
        Log.i(TAG, "sendPlayPendingNotificationsEvent");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendPlayPendingNotificationsEvent, AmfExternalNotificationsEventSender is not initialized");
            return;
        }
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(ExternalNotificationsCapabilityAgentConstants.INTERFACE_NAME, ExternalNotificationsCapabilityAgentConstants.PLAY_PENDING_EVENT_NAME), null), false, new AmfApiCallbacks(ExternalNotificationsCapabilityAgentConstants.PLAY_PENDING_EVENT_NAME, this.metricsRecorder));
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void teardown() {
        this.isInitialized.set(false);
        this.amfApis.stop();
        this.amfApis.destroy();
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendNotificationsDismissedEvent(List<NotificationId> list) {
        Preconditions.checkNotNull(list, "NotificationIds list cannot be null");
        Preconditions.checkArgument(!list.isEmpty(), "NotificationIds list cannot be empty");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendNotificationsDismissedEvent with notificationIds, AmfExternalNotificationsEventSender is not initialized");
            return;
        }
        Log.i(TAG, "sendNotificationsDismissedEvent");
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(ExternalNotificationsCapabilityAgentConstants.INTERFACE_NAME, "Dismissed"), new AlexaPayload(this.gson.toJson(NotificationsDismissedEventPayload.create(list)))), false, new AmfApiCallbacks("Dismissed", this.metricsRecorder));
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendNotificationsReadEvent(List<NotificationId> list) {
        Preconditions.checkNotNull(list, "NotificationIds list cannot be null");
        Preconditions.checkArgument(!list.isEmpty(), "NotificationIds list cannot be empty");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendNotificationsReadEvent with notificationIds, AmfExternalNotificationsEventSender is not initialized");
            return;
        }
        Log.i(TAG, "AmfExternalNotificationsEventSender: sendNotificationsReadEvent");
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(ExternalNotificationsCapabilityAgentConstants.INTERFACE_NAME, ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME), new AlexaPayload(this.gson.toJson(NotificationsReadEventPayload.create(list)))), false, new AmfApiCallbacks(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, this.metricsRecorder));
    }

    @Override // com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender
    public synchronized void sendNotificationsReceivedEvent(List<Notification> list) {
        Preconditions.checkNotNull(list, "Notifications list cannot be null");
        Preconditions.checkArgument(!list.isEmpty(), "Notifications list cannot be empty");
        if (!this.isInitialized.get()) {
            Log.w(TAG, "On sendNotificationsReceivedEvent with notificationIds, AmfExternalNotificationsEventSender is not initialized");
            return;
        }
        String str = TAG;
        Log.i(str, "sendNotificationsReceivedEvent with size: " + list.size());
        this.amfApis.getEventSender().send(new AlexaEvent(AlexaHeader.create(ExternalNotificationsCapabilityAgentConstants.INTERFACE_NAME, "Received"), new AlexaPayload(this.gson.toJson(NotificationsReceivedEventPayload.create(list)))), false, new AmfApiCallbacks("Received", this.metricsRecorder));
    }
}
