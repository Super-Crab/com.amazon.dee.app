package com.amazon.alexa.presence.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.dee.app.metrics.MetricsServiceV2;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class AlexaPresenceBluetoothReceiver extends BroadcastReceiver {
    private static final String TAG = Presence.tag();
    private final PresenceController controller;
    private final MetricsServiceV2 metricsServiceV2;

    @Inject
    public AlexaPresenceBluetoothReceiver(MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        this.metricsServiceV2 = metricsServiceV2;
        this.controller = presenceController;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
            if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1) == 12) {
                Log.i(TAG, "Bluetooth was turned ON. Starting beacon scanning if enabled");
                MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.Method.BLUETOOTH_STATE, MetricsUtil.MetricsId.BLUETOOTH_TURNED_ON);
                this.controller.enablePresence(context);
                return;
            }
            MetricsUtil.recordZeroCount(this.metricsServiceV2, MetricsUtil.Method.BLUETOOTH_STATE, MetricsUtil.MetricsId.BLUETOOTH_TURNED_ON);
            MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.START_SCANNING_BLUETOOTH_ON, MetricsUtil.Method.START_SCANNING_WORKFLOW);
            this.controller.disablePresence(context, true);
            return;
        }
        Log.w(TAG, "Bluetooth receiver triggered with an unexpected intent action.");
    }
}
