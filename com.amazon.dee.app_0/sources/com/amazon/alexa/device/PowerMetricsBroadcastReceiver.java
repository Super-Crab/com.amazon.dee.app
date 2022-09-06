package com.amazon.alexa.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/* loaded from: classes.dex */
class PowerMetricsBroadcastReceiver extends BroadcastReceiver {
    private final AndroidBatteryMetrics batteryMetrics;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PowerMetricsBroadcastReceiver(AndroidBatteryMetrics androidBatteryMetrics) {
        this.batteryMetrics = androidBatteryMetrics;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c;
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode == -1980154005) {
            if (action.equals("android.intent.action.BATTERY_OKAY")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 490310653) {
            if (hashCode == 1779291251 && action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (action.equals("android.intent.action.BATTERY_LOW")) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            this.batteryMetrics.recordPowerSaveMode();
        } else if (c == 1) {
            this.batteryMetrics.recordBatteryOkay();
        } else if (c != 2) {
            Log.e("ContentValues", "Unexpected broadcast: " + intent);
        } else {
            this.batteryMetrics.recordBatteryLow();
        }
    }
}
