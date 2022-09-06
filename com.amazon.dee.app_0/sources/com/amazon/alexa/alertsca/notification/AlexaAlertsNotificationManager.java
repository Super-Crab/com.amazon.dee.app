package com.amazon.alexa.alertsca.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.AlertRecordComparator;
import com.amazon.alexa.alertsca.AlertsAuthority;
import com.amazon.alexa.alertsca.AlertsEventBus;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.R;
import com.amazon.alexa.alertsca.events.AlertCleanUpEvent;
import com.amazon.alexa.alertsca.events.AlertNotificationEvent;
import com.amazon.alexa.alertsca.events.AlertScheduledEvent;
import com.amazon.alexa.alertsca.events.AlertStartedEvent;
import com.amazon.alexa.alertsca.events.AlertStoppedEvent;
import com.amazon.alexa.alertsca.events.AlertUnscheduledEvent;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationBuilderFactory;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.device.api.DeviceInformation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
@Singleton
/* loaded from: classes6.dex */
public class AlexaAlertsNotificationManager implements AlexaAlertsNotificationBuilderFactory.NotificationUpdateHandler {
    @VisibleForTesting
    static final String NOTIFICATION_CHANNEL = "alexa_alerts_channel";
    @VisibleForTesting
    static final String NOTIFICATION_HEADS_UP_CHANNEL = "alexa_alerts_heads_up_channel";
    private static final String TAG = "AlexaAlertsNotificationManager";
    private final NotificationManager androidNotificationManager;
    private final Context context;
    @Nullable
    private final DeviceInformation deviceInformation;
    private final AlertsEventBus eventBus;
    private final MetricsService metricsService;
    private final ScheduledExecutorService sharedExecutor;
    private final Deque<AlertRecord> activeAlerts = new LinkedList();
    private final SortedSet<AlertRecord> scheduledAlerts = Collections.synchronizedSortedSet(new TreeSet(new AlertRecordComparator()));
    private final Map<AlertsToken, AlexaAlertsNotificationBuilderFactory.NotificationBuilder> notifications = new HashMap();
    private final Map<AlertsToken, CountdownClock> clocks = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public AlexaAlertsNotificationManager(Context context, AlertsEventBus alertsEventBus, NotificationManager notificationManager, MetricsService metricsService, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, @Nullable DeviceInformation deviceInformation) {
        this.context = context;
        this.eventBus = alertsEventBus;
        this.androidNotificationManager = notificationManager;
        this.sharedExecutor = scheduledExecutorService;
        this.deviceInformation = deviceInformation;
        this.metricsService = metricsService;
        createNotificationChannels();
        alertsEventBus.register(this);
    }

    private Notification create(AlertRecord alertRecord, String str, boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("create: ");
        outline107.append(alertRecord.getToken());
        outline107.toString();
        AlexaAlertsNotificationBuilderFactory.NotificationBuilder create = AlexaAlertsNotificationBuilderFactory.create(alertRecord, this.context, str, this.deviceInformation, NOTIFICATION_HEADS_UP_CHANNEL.equals(str));
        if (this.deviceInformation == null) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.UNEXPECTED.NULL_DEVICE_INFO);
        }
        if (z && Build.VERSION.SDK_INT >= 29 && AlertsAuthority.ALERT_TYPES_SHOWING_FULL_SCREEN.contains(alertRecord.getType())) {
            create.setFullScreenIntent(PendingIntent.getActivity(this.context, 0, AlertsIntentFactory.getAlertsDisplayActivityIntent(this.context, alertRecord), 134217728), true);
            this.metricsService.recordEvent(MetricsConstants.ALERTS.NOTIFICATIONS.FULL_SCREEN_INTENT.element(alertRecord.getType().name()));
        }
        this.notifications.put(alertRecord.getToken(), create);
        int i = Build.VERSION.SDK_INT;
        return create.create();
    }

    @TargetApi(26)
    private void createHeadsUpNotificationChannel() {
        String string = this.context.getString(R.string.amazon_avs_alert_notification_heads_up_channel_name);
        String string2 = this.context.getString(R.string.amazon_avs_alert_notification_heads_up_channel_description);
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_HEADS_UP_CHANNEL, string, 4);
        setNotificationChannelProperty(notificationChannel, string2);
        this.androidNotificationManager.createNotificationChannel(notificationChannel);
    }

    @TargetApi(26)
    private void createNotificationChannel() {
        String string = this.context.getString(R.string.amazon_avs_alert_notification_channel_name);
        String string2 = this.context.getString(R.string.amazon_avs_alert_notification_channel_description);
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL, string, 2);
        setNotificationChannelProperty(notificationChannel, string2);
        this.androidNotificationManager.createNotificationChannel(notificationChannel);
    }

    private void createNotificationChannels() {
        int i = Build.VERSION.SDK_INT;
        createNotificationChannel();
        createHeadsUpNotificationChannel();
    }

    private void removeFromActiveAlerts(AlertRecord alertRecord, boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeFromActiveAlerts: ");
        outline107.append(alertRecord.getToken());
        outline107.toString();
        this.activeAlerts.remove(alertRecord);
        if (this.clocks.containsKey(alertRecord.getToken())) {
            this.clocks.remove(alertRecord.getToken()).stop();
        }
        this.notifications.remove(alertRecord.getToken());
        if (z || !alertRecord.isReminder()) {
            cancel(alertRecord);
        }
    }

    @TargetApi(26)
    private void setNotificationChannelProperty(NotificationChannel notificationChannel, String str) {
        notificationChannel.setDescription(str);
        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(false);
    }

    public void cancel(AlertRecord alertRecord) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cancelling the notification : ");
        outline107.append(alertRecord.getToken());
        outline107.toString();
        this.androidNotificationManager.cancel(alertRecord.getNotificationID());
        this.metricsService.recordEvent(MetricsConstants.ALERTS.NOTIFICATIONS.CANCEL.element(alertRecord.getType().name()));
    }

    @Subscribe
    public synchronized void on(AlertScheduledEvent alertScheduledEvent) {
        AlertRecord alertRecord = alertScheduledEvent.getAlertRecord();
        String str = "AlertScheduledEvent received: " + alertRecord.getToken();
        if (alertRecord.getType() == AlertType.TIMER) {
            this.scheduledAlerts.add(alertRecord);
            updateActiveAlert(alertRecord, NOTIFICATION_CHANNEL, false);
        }
    }

    @Override // com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationBuilderFactory.NotificationUpdateHandler
    public synchronized void onUpdate(AlertRecord alertRecord, String str) {
        String str2 = "onUpdate: " + alertRecord.getToken();
        if (this.notifications.containsKey(alertRecord.getToken())) {
            AlexaAlertsNotificationBuilderFactory.NotificationBuilder notificationBuilder = this.notifications.get(alertRecord.getToken());
            notificationBuilder.setTime(str);
            this.androidNotificationManager.notify(alertRecord.getNotificationID(), notificationBuilder.create());
        }
    }

    public void teardown() {
        this.eventBus.unregister(this);
    }

    @VisibleForTesting
    void updateActiveAlert(AlertRecord alertRecord, String str, boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateActiveAlert: ");
        outline107.append(alertRecord.getToken());
        outline107.toString();
        Notification create = create(alertRecord, str, z);
        this.eventBus.post(AlertNotificationEvent.create(alertRecord, create));
        this.androidNotificationManager.notify(alertRecord.getNotificationID(), create);
        this.metricsService.recordEvent(MetricsConstants.ALERTS.NOTIFICATIONS.NOTIFY.element(alertRecord.getType().name()));
    }

    private void cancel(int i) {
        this.androidNotificationManager.cancel(i);
        this.metricsService.recordEvent(MetricsConstants.ALERTS.NOTIFICATIONS.CANCEL);
    }

    @Subscribe
    public synchronized void on(AlertUnscheduledEvent alertUnscheduledEvent) {
        String str = "AlertUnscheduledEvent received: " + alertUnscheduledEvent.getAlertRecord().getToken();
        removeFromActiveAlerts(alertUnscheduledEvent.getAlertRecord(), alertUnscheduledEvent.isDismissedByUser());
        this.scheduledAlerts.remove(alertUnscheduledEvent.getAlertRecord());
    }

    @Subscribe
    public synchronized void on(AlertStartedEvent alertStartedEvent) {
        AlertRecord alertRecord = alertStartedEvent.getAlertRecord();
        String str = "AlertStartedEvent received: " + alertRecord.getToken();
        this.activeAlerts.push(alertRecord);
        updateActiveAlert(alertRecord, NOTIFICATION_HEADS_UP_CHANNEL, true);
        this.scheduledAlerts.remove(alertRecord);
    }

    @Subscribe
    public synchronized void on(AlertStoppedEvent alertStoppedEvent) {
        AlertRecord alertRecord;
        String str = "AlertStoppedEvent received: " + alertRecord.getToken();
        removeFromActiveAlerts(alertStoppedEvent.getAlertRecord(), alertStoppedEvent.isDismissedByUser());
    }

    @Subscribe
    public synchronized void on(AlertCleanUpEvent alertCleanUpEvent) {
        String str = "AlertCleanUpEvent received: " + alertCleanUpEvent.getAlertRecordId();
        cancel(alertCleanUpEvent.getAlertRecordId());
    }
}
