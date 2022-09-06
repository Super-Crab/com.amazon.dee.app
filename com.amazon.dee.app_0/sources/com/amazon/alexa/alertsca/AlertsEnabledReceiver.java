package com.amazon.alexa.alertsca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.alertsca.dependencies.AccessoryModule;
import com.amazon.alexa.alertsca.dependencies.AlertsEventBusModule;
import com.amazon.alexa.alertsca.dependencies.AlexaServicesModule;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule;
import com.amazon.alexa.alertsca.dependencies.DataModule;
import com.amazon.alexa.alertsca.dependencies.DeviceInformationModule;
import com.amazon.alexa.alertsca.dependencies.ExecutorModule;
import com.amazon.alexa.alertsca.dependencies.GsonModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Component;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertsEnabledReceiver extends BroadcastReceiver {
    private static final String TAG = AlertsEnabledReceiver.class.getSimpleName();
    @Inject
    AlertsCapabilityAgent alertsCapabilityAgent;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {ApplicationModule.class, DataModule.class, ExecutorModule.class, AlertsEventBusModule.class, GsonModule.class, AccessoryModule.class, MetricsModule.class, DeviceInformationModule.class, AlexaServicesModule.class})
    @Singleton
    /* loaded from: classes6.dex */
    public interface Injector {
        void inject(AlertsEnabledReceiver alertsEnabledReceiver);
    }

    @VisibleForTesting
    AlertsCapabilityAgent getAlertCapabilityAgent() {
        return this.alertsCapabilityAgent;
    }

    @VisibleForTesting
    void injectDependencies(Context context) {
        DaggerAlertsEnabledReceiver_Injector.builder().applicationModule(new ApplicationModule(context.getApplicationContext())).build().inject(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (AlertsIntentFactory.Actions.UPDATE_TIMERS_AND_ALARMS_AVAILABILITY.equals(intent.getAction())) {
            boolean booleanExtra = intent.getBooleanExtra(AlertsIntentFactory.ExtraKeys.ARE_TIMERS_AND_ALARMS_AVAILABLE, false);
            GeneratedOutlineSupport1.outline173("onReceive: areTimersAndAlarmsEnabled = ", booleanExtra, TAG);
            injectDependencies(context);
            this.alertsCapabilityAgent.updateTimersAndAlarmsAvailability(booleanExtra);
        }
    }
}
