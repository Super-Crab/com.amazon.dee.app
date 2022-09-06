package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MetricsModule_ProvideMetricsServiceFactory implements Factory<MetricsService> {
    private final Provider<Mobilytics> mobilyticsProvider;
    private final MetricsModule module;

    public MetricsModule_ProvideMetricsServiceFactory(MetricsModule metricsModule, Provider<Mobilytics> provider) {
        this.module = metricsModule;
        this.mobilyticsProvider = provider;
    }

    public static MetricsModule_ProvideMetricsServiceFactory create(MetricsModule metricsModule, Provider<Mobilytics> provider) {
        return new MetricsModule_ProvideMetricsServiceFactory(metricsModule, provider);
    }

    public static MetricsService provideInstance(MetricsModule metricsModule, Provider<Mobilytics> provider) {
        return proxyProvideMetricsService(metricsModule, provider.mo10268get());
    }

    public static MetricsService proxyProvideMetricsService(MetricsModule metricsModule, Mobilytics mobilytics) {
        return (MetricsService) Preconditions.checkNotNull(metricsModule.provideMetricsService(mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsService mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider);
    }
}
