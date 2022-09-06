package com.amazon.alexa.alertsca.alertdisplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
/* loaded from: classes6.dex */
public class AlertDisplayReceiver extends BroadcastReceiver {
    private final Context context;
    private final AlertDisplayListener listener;
    private static final String TAG = AlertDisplayReceiver.class.getSimpleName();
    private static final IntentFilter ALERT_DISPLAY_RECEIVER_INTENT_FILTER = new IntentFilter();

    static {
        ALERT_DISPLAY_RECEIVER_INTENT_FILTER.addAction(AlertsIntentFactory.Actions.DISMISS_UI);
    }

    public AlertDisplayReceiver(Context context, AlertDisplayListener alertDisplayListener) {
        this.context = context;
        this.listener = alertDisplayListener;
    }

    @VisibleForTesting
    LocalBroadcastManager getLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(this.context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.listener.onDismiss(intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_TOKEN));
    }

    public void register() {
        getLocalBroadcastManager().registerReceiver(this, ALERT_DISPLAY_RECEIVER_INTENT_FILTER);
    }

    public void unregister() {
        getLocalBroadcastManager().unregisterReceiver(this);
    }
}
