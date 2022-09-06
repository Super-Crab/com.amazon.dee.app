package com.amazon.dee.app.services.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.Utils;
import com.amazon.device.messaging.ADMMessageHandlerJobBase;
import com.dee.app.metrics.MetricsService;
/* loaded from: classes12.dex */
public class PhoenixMessageHandler extends ADMMessageHandlerJobBase {
    static final String TAG = PhoenixMessageHandler.class.getSimpleName();
    AmazonDeviceMessagingService messagingService;
    MetricsService metricsService;

    @Override // com.amazon.device.messaging.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        MessagingService messagingService = ((AlexaApplication) getApplication()).getComponent().messagingService();
        if (messagingService instanceof AmazonDeviceMessagingService) {
            this.messagingService = (AmazonDeviceMessagingService) messagingService;
        } else {
            Log.w(TAG, "Messaging service is not amazon but amazon message handler is invoked!");
        }
        this.metricsService = ((AlexaApplication) getApplication()).getComponent().metricsService();
    }

    @Override // com.amazon.device.messaging.ADMMessageHandlerJobBase
    protected void onMessage(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String str = "Bundle: " + extras;
        AmazonDeviceMessagingService amazonDeviceMessagingService = this.messagingService;
        if (amazonDeviceMessagingService == null || extras == null) {
            return;
        }
        amazonDeviceMessagingService.handleMessage(extras);
    }

    @Override // com.amazon.device.messaging.ADMMessageHandlerJobBase
    protected void onRegistered(Context context, String str) {
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.ADM_REGISTRATION_SUCCESS, "PushNotifications", true, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        AmazonDeviceMessagingService amazonDeviceMessagingService = this.messagingService;
        if (amazonDeviceMessagingService != null) {
            amazonDeviceMessagingService.requestRegistration(str);
        }
    }

    @Override // com.amazon.device.messaging.ADMMessageHandlerJobBase
    protected void onRegistrationError(Context context, String str) {
        String str2 = TAG;
        Log.e(str2, "Error registering with ADM, reason: " + str);
        this.metricsService.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.ADM_REGISTRATION_SUCCESS, "PushNotifications", false, Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
        this.metricsService.recordError(AlexaMetricsConstants.MetricEvents.ADM_REGISTRATION_ERROR, str, "PushNotifications", Utils.getCustomEntriesWithOwnerIdentifier("1235005e-4e8f-4ef2-82bc-34157415015b"));
    }

    @Override // com.amazon.device.messaging.ADMMessageHandlerJobBase
    protected void onUnregistered(Context context, String str) {
        AmazonDeviceMessagingService amazonDeviceMessagingService = this.messagingService;
        if (amazonDeviceMessagingService != null) {
            amazonDeviceMessagingService.requestDeregistration();
        }
    }
}
