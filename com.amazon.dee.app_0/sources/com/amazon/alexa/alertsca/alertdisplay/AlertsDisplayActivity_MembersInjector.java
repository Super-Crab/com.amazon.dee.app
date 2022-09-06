package com.amazon.alexa.alertsca.alertdisplay;

import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsDisplayActivity_MembersInjector implements MembersInjector<AlertsDisplayActivity> {
    private final Provider<MetricsService> metricsServiceProvider;

    public AlertsDisplayActivity_MembersInjector(Provider<MetricsService> provider) {
        this.metricsServiceProvider = provider;
    }

    public static MembersInjector<AlertsDisplayActivity> create(Provider<MetricsService> provider) {
        return new AlertsDisplayActivity_MembersInjector(provider);
    }

    public static void injectMetricsService(AlertsDisplayActivity alertsDisplayActivity, MetricsService metricsService) {
        alertsDisplayActivity.metricsService = metricsService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertsDisplayActivity alertsDisplayActivity) {
        injectMetricsService(alertsDisplayActivity, this.metricsServiceProvider.mo10268get());
    }
}
