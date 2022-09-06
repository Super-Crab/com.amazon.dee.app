package com.amazon.alexa.alertsca;

import android.app.Notification;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.events.AlertNotificationEvent;
import com.amazon.alexa.alertsca.events.AlertStoppedEvent;
import com.amazon.alexa.alertsca.events.AlertUnscheduledEvent;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationManager;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
@Singleton
/* loaded from: classes6.dex */
public class ServiceForegroundWatcher {
    private static final String TAG = "ServiceForegroundWatcher";
    @VisibleForTesting
    final LinkedList<AlertRecord> activeAlerts;
    @VisibleForTesting
    final Map<AlertRecord, Notification> alertNotifications;
    private final AlertsEventBus eventBus;
    private final MetricsService metricsService;
    private final AlexaAlertsNotificationManager notificationManager;
    private final AlertsCapabilityAgentService service;

    public ServiceForegroundWatcher(AlertsCapabilityAgentService alertsCapabilityAgentService, AlexaAlertsNotificationManager alexaAlertsNotificationManager, AlertsEventBus alertsEventBus, MetricsService metricsService) {
        Preconditions.notNull(alertsCapabilityAgentService, "service is null");
        Preconditions.notNull(alexaAlertsNotificationManager, "notificationManager is null");
        Preconditions.notNull(alertsEventBus, "eventBus is null");
        Preconditions.notNull(metricsService, "metricsService is null");
        this.service = alertsCapabilityAgentService;
        this.notificationManager = alexaAlertsNotificationManager;
        this.eventBus = alertsEventBus;
        this.metricsService = metricsService;
        this.activeAlerts = new LinkedList<>();
        this.alertNotifications = new HashMap();
        alertsEventBus.register(this);
    }

    private void updateNotificationAndService(AlertRecord alertRecord, boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateNotificationAndService: ");
        outline107.append(alertRecord.getToken());
        outline107.append(", isDismissedByUser = ");
        outline107.append(z);
        outline107.toString();
        boolean z2 = z || !alertRecord.isReminder();
        if (this.activeAlerts.contains(alertRecord)) {
            this.activeAlerts.remove(alertRecord);
            this.alertNotifications.remove(alertRecord);
            if (this.activeAlerts.isEmpty()) {
                this.service.stopForeground(z2);
                return;
            }
            AlertRecord peekFirst = this.activeAlerts.peekFirst();
            if (peekFirst != null) {
                this.notificationManager.cancel(peekFirst);
                this.service.startForeground(peekFirst.getNotificationID(), this.alertNotifications.get(peekFirst));
                this.metricsService.recordEvent(MetricsConstants.ALERTS.SERVICE.START_FOREGROUND.element(peekFirst.getType().name()));
            }
            if (!z2) {
                return;
            }
            this.notificationManager.cancel(alertRecord);
        }
    }

    @Subscribe
    public synchronized void on(AlertNotificationEvent alertNotificationEvent) {
        AlertRecord alertRecord = alertNotificationEvent.getAlertRecord();
        Notification notification = alertNotificationEvent.getNotification();
        if (this.activeAlerts.isEmpty()) {
            this.service.startForeground(alertRecord.getNotificationID(), notification);
            this.metricsService.recordEvent(MetricsConstants.ALERTS.SERVICE.START_FOREGROUND.element(alertRecord.getType().name()));
        }
        if (!this.activeAlerts.contains(alertRecord)) {
            this.activeAlerts.push(alertRecord);
        }
        this.alertNotifications.put(alertRecord, notification);
    }

    public void teardown() {
        this.eventBus.unregister(this);
    }

    @Subscribe
    public synchronized void on(AlertStoppedEvent alertStoppedEvent) {
        updateNotificationAndService(alertStoppedEvent.getAlertRecord(), alertStoppedEvent.isDismissedByUser());
    }

    @Subscribe
    public synchronized void on(AlertUnscheduledEvent alertUnscheduledEvent) {
        updateNotificationAndService(alertUnscheduledEvent.getAlertRecord(), alertUnscheduledEvent.isDismissedByUser());
    }
}
