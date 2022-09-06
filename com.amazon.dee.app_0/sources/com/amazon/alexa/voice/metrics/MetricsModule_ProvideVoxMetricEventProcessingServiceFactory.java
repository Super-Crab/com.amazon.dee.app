package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvideVoxMetricEventProcessingServiceFactory implements Factory<VoxMetricEventProcessingService> {
    private final Provider<MetricsService> metricsServiceProvider;

    public MetricsModule_ProvideVoxMetricEventProcessingServiceFactory(Provider<MetricsService> provider) {
        this.metricsServiceProvider = provider;
    }

    public static MetricsModule_ProvideVoxMetricEventProcessingServiceFactory create(Provider<MetricsService> provider) {
        return new MetricsModule_ProvideVoxMetricEventProcessingServiceFactory(provider);
    }

    public static VoxMetricEventProcessingService provideInstance(Provider<MetricsService> provider) {
        return proxyProvideVoxMetricEventProcessingService(provider.mo10268get());
    }

    public static VoxMetricEventProcessingService proxyProvideVoxMetricEventProcessingService(MetricsService metricsService) {
        return (VoxMetricEventProcessingService) Preconditions.checkNotNull(MetricsModule.provideVoxMetricEventProcessingService(metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoxMetricEventProcessingService mo10268get() {
        return provideInstance(this.metricsServiceProvider);
    }
}
