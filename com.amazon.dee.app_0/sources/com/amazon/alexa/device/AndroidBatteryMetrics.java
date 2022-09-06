package com.amazon.alexa.device;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.amazon.alexa.device.MetricsConstants;
import com.amazon.alexa.device.api.DevicePowerMonitor;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes.dex */
public class AndroidBatteryMetrics implements DevicePowerMonitor {
    private final Context context;
    private final LazyComponent<Mobilytics> mobilyticsLazy;
    private final PowerManager powerManager;
    private PowerMetricsBroadcastReceiver powerMetricsBroadcastReceiver;

    public AndroidBatteryMetrics(ComponentGetter componentGetter, Context context) {
        this.mobilyticsLazy = componentGetter.get(Mobilytics.class);
        this.context = context;
        this.powerManager = (PowerManager) context.getSystemService("power");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordBatteryLow() {
        this.mobilyticsLazy.mo10268get().recordOperationalEvent(this.mobilyticsLazy.mo10268get().createOperationalEvent(MetricsConstants.Components.POWER, OperationalEventType.DIAGNOSTIC, MetricsConstants.Events.BATTERY_LOW, AndroidBatteryMetrics.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordBatteryOkay() {
        this.mobilyticsLazy.mo10268get().recordOperationalEvent(this.mobilyticsLazy.mo10268get().createOperationalEvent(MetricsConstants.Components.POWER, OperationalEventType.DIAGNOSTIC, MetricsConstants.Events.BATTERY_OK, AndroidBatteryMetrics.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordPowerSaveMode() {
        this.mobilyticsLazy.mo10268get().recordOccurrence(MetricsConstants.Components.POWER, this.powerManager.isPowerSaveMode(), MetricsConstants.Events.POWERSAVE_MODE, AndroidBatteryMetrics.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void start() {
        this.powerMetricsBroadcastReceiver = new PowerMetricsBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        this.context.registerReceiver(this.powerMetricsBroadcastReceiver, intentFilter);
        recordPowerSaveMode();
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public void stop() {
        PowerMetricsBroadcastReceiver powerMetricsBroadcastReceiver = this.powerMetricsBroadcastReceiver;
        if (powerMetricsBroadcastReceiver != null) {
            this.context.unregisterReceiver(powerMetricsBroadcastReceiver);
            this.powerMetricsBroadcastReceiver = null;
        }
    }
}
