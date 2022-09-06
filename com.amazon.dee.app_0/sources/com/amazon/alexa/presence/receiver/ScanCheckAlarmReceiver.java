package com.amazon.alexa.presence.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.alarm.PresenceAlarmManager;
import com.amazon.alexa.presence.service.AlexaBeaconDetectorService;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.dee.app.metrics.MetricsServiceV2;
/* loaded from: classes9.dex */
public class ScanCheckAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = Presence.tag();
    private final PresenceController controller;
    private final MetricsServiceV2 metricsServiceV2;

    public ScanCheckAlarmReceiver(MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        this.metricsServiceV2 = metricsServiceV2;
        this.controller = presenceController;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (PresenceAlarmManager.ALARM_INTENT_ACTION.equals(intent.getAction())) {
            restartBeaconScanningWorkflow(context);
        }
    }

    void restartBeaconScanningWorkflow(Context context) {
        MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.RESTART_SCANNING_PERIODIC_ALARM, MetricsUtil.Method.RESTART_SCANNING_WORKFLOW);
        AlexaBeaconDetectorService.forceStopScanning(context);
        this.controller.enablePresence(context);
    }
}
