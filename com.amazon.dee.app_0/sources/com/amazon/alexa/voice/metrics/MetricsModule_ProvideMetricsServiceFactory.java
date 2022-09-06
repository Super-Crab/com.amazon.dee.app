package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvideMetricsServiceFactory implements Factory<MetricsService> {
    private final Provider<Mobilytics> mobilyticsProvider;

    public MetricsModule_ProvideMetricsServiceFactory(Provider<Mobilytics> provider) {
        this.mobilyticsProvider = provider;
    }

    public static MetricsModule_ProvideMetricsServiceFactory create(Provider<Mobilytics> provider) {
        return new MetricsModule_ProvideMetricsServiceFactory(provider);
    }

    public static MetricsService provideInstance(Provider<Mobilytics> provider) {
        return proxyProvideMetricsService(provider.mo10268get());
    }

    public static MetricsService proxyProvideMetricsService(Mobilytics mobilytics) {
        return (MetricsService) Preconditions.checkNotNull(MetricsModule.provideMetricsService(mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsService mo10268get() {
        return provideInstance(this.mobilyticsProvider);
    }
}
