package com.amazon.alexa.voice.tta.metrics;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesMetricEventProcessingServiceFactory implements Factory<MetricEventProcessingService> {
    private final MetricsModule module;
    private final Provider<SimbaEventProcessor> simbaEventProcessorProvider;
    private final Provider<TtaIngressEventProcessor> ttaIngressEventProcessorProvider;
    private final Provider<TtaUiEventProcessor> ttaUiEventProcessorProvider;
    private final Provider<TtaUiEventWithCountProcessor> ttaUiEventWithCountProcessorProvider;
    private final Provider<TtaUiEventWithTypeProcessor> ttaUiEventWithTypeProcessorProvider;
    private final Provider<TtaUplEventProcessor> ttaUplEventProcessorProvider;

    public MetricsModule_ProvidesMetricEventProcessingServiceFactory(MetricsModule metricsModule, Provider<TtaUiEventProcessor> provider, Provider<TtaIngressEventProcessor> provider2, Provider<TtaUplEventProcessor> provider3, Provider<TtaUiEventWithTypeProcessor> provider4, Provider<SimbaEventProcessor> provider5, Provider<TtaUiEventWithCountProcessor> provider6) {
        this.module = metricsModule;
        this.ttaUiEventProcessorProvider = provider;
        this.ttaIngressEventProcessorProvider = provider2;
        this.ttaUplEventProcessorProvider = provider3;
        this.ttaUiEventWithTypeProcessorProvider = provider4;
        this.simbaEventProcessorProvider = provider5;
        this.ttaUiEventWithCountProcessorProvider = provider6;
    }

    public static MetricsModule_ProvidesMetricEventProcessingServiceFactory create(MetricsModule metricsModule, Provider<TtaUiEventProcessor> provider, Provider<TtaIngressEventProcessor> provider2, Provider<TtaUplEventProcessor> provider3, Provider<TtaUiEventWithTypeProcessor> provider4, Provider<SimbaEventProcessor> provider5, Provider<TtaUiEventWithCountProcessor> provider6) {
        return new MetricsModule_ProvidesMetricEventProcessingServiceFactory(metricsModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static MetricEventProcessingService provideInstance(MetricsModule metricsModule, Provider<TtaUiEventProcessor> provider, Provider<TtaIngressEventProcessor> provider2, Provider<TtaUplEventProcessor> provider3, Provider<TtaUiEventWithTypeProcessor> provider4, Provider<SimbaEventProcessor> provider5, Provider<TtaUiEventWithCountProcessor> provider6) {
        return proxyProvidesMetricEventProcessingService(metricsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static MetricEventProcessingService proxyProvidesMetricEventProcessingService(MetricsModule metricsModule, TtaUiEventProcessor ttaUiEventProcessor, TtaIngressEventProcessor ttaIngressEventProcessor, TtaUplEventProcessor ttaUplEventProcessor, TtaUiEventWithTypeProcessor ttaUiEventWithTypeProcessor, SimbaEventProcessor simbaEventProcessor, TtaUiEventWithCountProcessor ttaUiEventWithCountProcessor) {
        return (MetricEventProcessingService) Preconditions.checkNotNull(metricsModule.providesMetricEventProcessingService(ttaUiEventProcessor, ttaIngressEventProcessor, ttaUplEventProcessor, ttaUiEventWithTypeProcessor, simbaEventProcessor, ttaUiEventWithCountProcessor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricEventProcessingService mo10268get() {
        return provideInstance(this.module, this.ttaUiEventProcessorProvider, this.ttaIngressEventProcessorProvider, this.ttaUplEventProcessorProvider, this.ttaUiEventWithTypeProcessorProvider, this.simbaEventProcessorProvider, this.ttaUiEventWithCountProcessorProvider);
    }
}
