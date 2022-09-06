package com.amazon.alexa.alertsca;

import android.app.AlarmManager;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsScheduler_Factory implements Factory<AlertsScheduler> {
    private final Provider<AlarmManager> alarmManagerProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public AlertsScheduler_Factory(Provider<AlarmManager> provider, Provider<MetricsService> provider2) {
        this.alarmManagerProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static AlertsScheduler_Factory create(Provider<AlarmManager> provider, Provider<MetricsService> provider2) {
        return new AlertsScheduler_Factory(provider, provider2);
    }

    public static AlertsScheduler newAlertsScheduler(AlarmManager alarmManager, MetricsService metricsService) {
        return new AlertsScheduler(alarmManager, metricsService);
    }

    public static AlertsScheduler provideInstance(Provider<AlarmManager> provider, Provider<MetricsService> provider2) {
        return new AlertsScheduler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsScheduler mo10268get() {
        return provideInstance(this.alarmManagerProvider, this.metricsServiceProvider);
    }
}
