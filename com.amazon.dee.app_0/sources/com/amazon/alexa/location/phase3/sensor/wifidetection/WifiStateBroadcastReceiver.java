package com.amazon.alexa.location.phase3.sensor.wifidetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.location.phase3.sensor.SensorIntentService;
/* loaded from: classes9.dex */
public class WifiStateBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(WifiDetectionSensor.WIFI_DETECTION_ACTION);
        if (intent != null && intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
            intent2.setClassName(context, SensorIntentService.class.getName());
            ContextCompat.startForegroundService(context, intent2);
            return;
        }
        Log.e("Receiver Error:", "Intent is null or intent action is not correct.");
    }
}
