package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class UiModule_ProvideMetricsBridgeFactory implements Factory<MetricsBridge> {
    private final Provider<VoxMetricEventProcessingService> eventProcessingServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public UiModule_ProvideMetricsBridgeFactory(Provider<MetricsService> provider, Provider<VoxMetricEventProcessingService> provider2) {
        this.metricsServiceProvider = provider;
        this.eventProcessingServiceProvider = provider2;
    }

    public static UiModule_ProvideMetricsBridgeFactory create(Provider<MetricsService> provider, Provider<VoxMetricEventProcessingService> provider2) {
        return new UiModule_ProvideMetricsBridgeFactory(provider, provider2);
    }

    public static MetricsBridge provideInstance(Provider<MetricsService> provider, Provider<VoxMetricEventProcessingService> provider2) {
        return proxyProvideMetricsBridge(provider.mo10268get(), provider2.mo10268get());
    }

    public static MetricsBridge proxyProvideMetricsBridge(MetricsService metricsService, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        return (MetricsBridge) Preconditions.checkNotNull(UiModule.provideMetricsBridge(metricsService, voxMetricEventProcessingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsBridge mo10268get() {
        return provideInstance(this.metricsServiceProvider, this.eventProcessingServiceProvider);
    }
}
