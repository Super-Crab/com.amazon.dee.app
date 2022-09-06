package com.amazon.alexa.voice.alerts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.voice.features.FeatureEnabler;
import com.amazon.alexa.voice.features.VoiceFeature;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class AlertsFeatureEnabler implements FeatureEnabler {
    public static final String ALERTS_RECEIVER_CLASS_NAME = "com.amazon.alexa.alertsca.AlertsEnabledReceiver";
    public static final String TAG = "AlertsFeatureEnabler";
    private final Context context;

    @Inject
    public AlertsFeatureEnabler(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override // com.amazon.alexa.voice.features.FeatureEnabler
    public void enableFeature(boolean z) {
        try {
            Intent createUpdateAvailabilityIntent = AlertsIntentFactory.createUpdateAvailabilityIntent(this.context, z);
            createUpdateAvailabilityIntent.setComponent(new ComponentName(this.context.getPackageName(), ALERTS_RECEIVER_CLASS_NAME));
            this.context.sendBroadcast(createUpdateAvailabilityIntent);
        } catch (Exception e) {
            Log.e(TAG, z ? "Failed to enable Alerts" : "Failed to disable Alerts", e);
        }
    }

    @Override // com.amazon.alexa.voice.features.FeatureEnabler
    public VoiceFeature getFeature() {
        return VoiceFeature.ALEXA_TIMER_AND_ALARM_ON_VOX_GUI_ANDROID;
    }
}
