package com.amazon.alexa.alertsca;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertsScheduler {
    private static final String TAG = "AlertsScheduler";
    private final AlarmManager alarmManager;
    private final MetricsService metricsService;
    private final Map<AlertsToken, PendingIntent> scheduledAlerts = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public AlertsScheduler(AlarmManager alarmManager, MetricsService metricsService) {
        this.alarmManager = alarmManager;
        this.metricsService = metricsService;
    }

    @VisibleForTesting
    boolean isScheduled(AlertsToken alertsToken) {
        return this.scheduledAlerts.containsKey(alertsToken);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void schedule(Context context, AlertRecord alertRecord) {
        schedule(context, alertRecord, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unSchedule(AlertsToken alertsToken) {
        String str = "unScheduling alert: " + alertsToken;
        PendingIntent pendingIntent = this.scheduledAlerts.get(alertsToken);
        if (pendingIntent != null) {
            this.scheduledAlerts.remove(alertsToken);
            this.alarmManager.cancel(pendingIntent);
        }
        this.metricsService.recordEvent(MetricsConstants.ALERTS.UNSCHEDULE.result(pendingIntent != null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void schedule(Context context, AlertRecord alertRecord, boolean z) {
        long time = alertRecord.getScheduledTime().getTime();
        long currentTimeMillis = System.currentTimeMillis();
        long j = time - currentTimeMillis;
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Scheduling alert for ", j, "ms from ");
        outline111.append(currentTimeMillis);
        outline111.toString();
        PendingIntent createPendingIntent = AlertsIntentFactory.createPendingIntent(context, alertRecord);
        this.alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(currentTimeMillis + j, createPendingIntent), createPendingIntent);
        this.scheduledAlerts.put(alertRecord.getToken(), createPendingIntent);
        if (!z) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.SCHEDULE.element(alertRecord.getType().name()).success());
        }
    }
}
