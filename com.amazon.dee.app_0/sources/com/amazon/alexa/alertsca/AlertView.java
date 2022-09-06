package com.amazon.alexa.alertsca;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertView {
    private static final String TAG = "AlertView";
    private final Context context;
    private final MetricsService metricsService;

    @Inject
    public AlertView(Context context, MetricsService metricsService) {
        this.context = context;
        this.metricsService = metricsService;
    }

    public void dismissAlert(AlertsToken alertsToken) {
        Intent intent = new Intent();
        intent.setAction(AlertsIntentFactory.Actions.DISMISS_UI);
        intent.putExtra(AlertsIntentFactory.ExtraKeys.ALERT_TOKEN, alertsToken.getValue());
        getLocalBroadcastManager().sendBroadcast(intent);
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.ALERT_VIEW_DISMISS);
    }

    @VisibleForTesting
    LocalBroadcastManager getLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(this.context);
    }

    public void showAlert(AlertRecord alertRecord) {
        this.context.startActivity(AlertsIntentFactory.getAlertsDisplayActivityIntent(this.context, alertRecord));
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.ALERT_VIEW_SHOW.element(alertRecord.getType().name()));
    }
}
