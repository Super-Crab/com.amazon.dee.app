package com.amazon.alexa.alertsca;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.alertsca.dependencies.AlertsEventBusModule;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule;
import com.amazon.alexa.alertsca.dependencies.DataModule;
import com.amazon.alexa.alertsca.dependencies.ExecutorModule;
import com.amazon.alexa.alertsca.dependencies.GsonModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import dagger.Component;
import javax.inject.Inject;
import javax.inject.Singleton;
/* loaded from: classes6.dex */
public class AlertReschedulingBootCompletedReceiver extends BroadcastReceiver {
    private static final String TAG = AlertReschedulingBootCompletedReceiver.class.getSimpleName();
    @Inject
    AlertsAnalyzer alertsAnalyzer;
    @Inject
    AlertsScheduler alertsScheduler;
    @Inject
    AlertsStore alertsStore;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {ApplicationModule.class, DataModule.class, ExecutorModule.class, AlertsEventBusModule.class, GsonModule.class, MetricsModule.class})
    @Singleton
    /* loaded from: classes6.dex */
    public interface Injector {
        void inject(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver);
    }

    private void injectServiceDependencies(Context context) {
        DaggerAlertReschedulingBootCompletedReceiver_Injector.builder().applicationModule(new ApplicationModule((Application) context.getApplicationContext())).build().inject(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals("android.intent.action.LOCKED_BOOT_COMPLETED")) {
            injectServiceDependencies(context);
            new RescheduleAllAlertsAsyncTask(context, this.alertsStore, this.alertsAnalyzer, this.alertsScheduler, goAsync()).execute(new Void[0]);
        }
    }
}
