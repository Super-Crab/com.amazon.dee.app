package com.amazon.alexa.alertsca;

import android.content.Context;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertView_Factory implements Factory<AlertView> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public AlertView_Factory(Provider<Context> provider, Provider<MetricsService> provider2) {
        this.contextProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static AlertView_Factory create(Provider<Context> provider, Provider<MetricsService> provider2) {
        return new AlertView_Factory(provider, provider2);
    }

    public static AlertView newAlertView(Context context, MetricsService metricsService) {
        return new AlertView(context, metricsService);
    }

    public static AlertView provideInstance(Provider<Context> provider, Provider<MetricsService> provider2) {
        return new AlertView(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertView mo10268get() {
        return provideInstance(this.contextProvider, this.metricsServiceProvider);
    }
}
