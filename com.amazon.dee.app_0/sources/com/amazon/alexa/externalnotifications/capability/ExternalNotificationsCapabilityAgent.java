package com.amazon.alexa.externalnotifications.capability;

import android.content.Context;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.amazon.alexa.externalnotifications.capability.dependencies.AlexaMobileFrameworkModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.ContextModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.ExternalNotificationsCapabilityAgentModule;
import com.amazon.alexa.externalnotifications.capability.dependencies.GsonModule;
import com.amazon.alexa.externalnotifications.capability.directive.ExternalNotificationsDirectiveListener;
import com.amazon.alexa.externalnotifications.capability.events.ExternalNotificationsEventSender;
import com.amazon.alexa.externalnotifications.capability.models.Notification;
import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import dagger.Component;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
/* loaded from: classes7.dex */
public class ExternalNotificationsCapabilityAgent {
    private static final String TAG = "ExternalNotificationsCapabilityAgent";
    private Context context;
    private ExternalNotificationsDirectiveListener directiveListener;
    @Inject
    public ExternalNotificationsDirectiveReceiver directiveReceiver;
    @Inject
    public ExternalNotificationsEventSender eventSender;
    private ExternalNotificationsMetricsRecorder metricsRecorder;
    private ExternalNotificationsProvider notificationsProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {ContextModule.class, GsonModule.class, AlexaMobileFrameworkModule.class, ExternalNotificationsCapabilityAgentModule.class})
    @Singleton
    /* loaded from: classes7.dex */
    public interface Injector {
        void inject(ExternalNotificationsCapabilityAgent externalNotificationsCapabilityAgent);
    }

    public ExternalNotificationsCapabilityAgent(Context context, ExternalNotificationsProvider externalNotificationsProvider, ExternalNotificationsDirectiveListener externalNotificationsDirectiveListener, ExternalNotificationsMetricsRecorder externalNotificationsMetricsRecorder) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(externalNotificationsDirectiveListener, "Directive listener cannot be null");
        this.context = context;
        this.notificationsProvider = externalNotificationsProvider;
        this.directiveListener = externalNotificationsDirectiveListener;
        this.metricsRecorder = externalNotificationsMetricsRecorder;
    }

    private void injectDependencies() {
        DaggerExternalNotificationsCapabilityAgent_Injector.builder().contextModule(new ContextModule(this.context)).externalNotificationsCapabilityAgentModule(new ExternalNotificationsCapabilityAgentModule(this.directiveListener, this.metricsRecorder)).build().inject(this);
    }

    public void destroy() {
        this.directiveReceiver.unregister();
        this.eventSender.teardown();
    }

    public void initialize() {
        injectDependencies();
        this.eventSender.initialize();
        this.directiveReceiver.register();
        uploadActiveNotifications();
    }

    public void onNotificationDismissed(NotificationId notificationId) {
        Preconditions.checkNotNull(TAG, "NotificationIds list cannot be null");
        String str = "onNotificationDismissed: " + notificationId.getValue();
        this.eventSender.sendNotificationsDismissedEvent(notificationId);
    }

    public void onNotificationRead(NotificationId notificationId) {
        Preconditions.checkNotNull(TAG, "NotificationId cannot be null");
        String str = "onNotificationRead: " + notificationId.getValue();
        this.eventSender.sendNotificationsReadEvent(notificationId);
    }

    public void onNotificationReceived(Notification notification) {
        Preconditions.checkNotNull(TAG, "Notification cannot be null");
        String str = "onNotificationReceived: " + notification.getNotificationId().getValue();
        this.eventSender.sendNotificationsReceivedEvent(notification);
    }

    public void uploadActiveNotifications() {
        ExternalNotificationsProvider externalNotificationsProvider = this.notificationsProvider;
        if (externalNotificationsProvider == null) {
            Log.e(TAG, "uploadActiveNotifications: notificationsProvider is null, can't upload.");
            return;
        }
        List<Notification> activeNotifications = externalNotificationsProvider.getActiveNotifications();
        if (activeNotifications == null || activeNotifications.isEmpty()) {
            return;
        }
        String.format("Uploading %d active notifications", Integer.valueOf(activeNotifications.size()));
        this.metricsRecorder.recordCounter("uploadActiveNotifications", activeNotifications.size());
        this.eventSender.sendNotificationsReceivedEvent(activeNotifications);
    }

    public void uploadActiveNotifications(List<Notification> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        String.format("Uploading %d active notifications", Integer.valueOf(list.size()));
        this.eventSender.sendNotificationsReceivedEvent(list);
    }
}
