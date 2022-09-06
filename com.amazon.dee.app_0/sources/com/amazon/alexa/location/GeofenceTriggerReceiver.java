package com.amazon.alexa.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import java.util.Calendar;
/* loaded from: classes9.dex */
public class GeofenceTriggerReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceTriggerReceiver";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    private void sendNotification(Context context, Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.setClass(context, GeofenceTriggerIntentService.class);
        int i = Build.VERSION.SDK_INT;
        context.startForegroundService(intent2);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            LazyComponent lazy = ComponentRegistry.getInstance().getLazy(Mobilytics.class);
            ((Mobilytics) lazy.mo10268get()).recordOccurrence(MobilyticsUtil.MetricsID.INTENT_RECEIVED, true, COMPONENT_NAME, COMPONENT_NAME, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            MetricsUtil.recordTime(lazy, MetricsUtil.MetricsId.GEOFENCE_TRIGGER_RECEIVED, "trigger_geofence", timeInMillis);
            WriteToFile.appendLogForDebugBuild(context, "onReceive + start");
            intent.putExtra(LocationManager.GEOFENCE_OS_TRIGGER_DETECTED_TIME, timeInMillis);
            sendNotification(context, intent);
            WriteToFile.appendLogForDebugBuild(context, "onReceive + finish");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
