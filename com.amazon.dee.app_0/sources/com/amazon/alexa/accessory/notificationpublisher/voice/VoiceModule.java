package com.amazon.alexa.accessory.notificationpublisher.voice;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerProxy;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.TemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgent;
/* loaded from: classes.dex */
public class VoiceModule {
    private static final String TAG = "VoiceModule";
    private NotificationBroadcastReceiver broadcastReceiver;
    private ExternalNotificationsCapabilityAgent capabilityAgent;

    public VoiceModule(Context context) {
        NotificationProcessor notificationProcessor = new NotificationProcessor(context, TemplateProvider.getInstance(), SettingsStorageModule.getInstance());
        this.capabilityAgent = new ExternalNotificationsCapabilityAgent(context, new NotificationProvider(notificationProcessor), new NotificationsDirectiveListener(), new DefaultVoiceMetricsRecorder(MetricsRecorder.getInstance()));
        HandlerThread handlerThread = new HandlerThread("VIP_Filter_Voice_Thread");
        handlerThread.start();
        this.broadcastReceiver = new NotificationBroadcastReceiver(context, new Handler(handlerThread.getLooper()), new NotificationKeyToIdsMap(), notificationProcessor, this.capabilityAgent);
        DependencyProvider.setExternalNotificationsCapabilityAgent(this.capabilityAgent);
    }

    public void initialize() {
        NotificationListenerProxy.create();
        this.broadcastReceiver.register();
        this.capabilityAgent.initialize();
    }

    public void teardown() {
        this.broadcastReceiver.unregister();
        this.capabilityAgent.destroy();
        DependencyProvider.setExternalNotificationsCapabilityAgent(null);
    }
}
