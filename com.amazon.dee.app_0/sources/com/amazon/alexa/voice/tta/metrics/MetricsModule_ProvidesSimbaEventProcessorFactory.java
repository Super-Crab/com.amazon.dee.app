package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesSimbaEventProcessorFactory implements Factory<SimbaEventProcessor> {
    private final MetricsModule module;
    private final Provider<UiEventReporter> sdkEventReporterProvider;

    public MetricsModule_ProvidesSimbaEventProcessorFactory(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        this.module = metricsModule;
        this.sdkEventReporterProvider = provider;
    }

    public static MetricsModule_ProvidesSimbaEventProcessorFactory create(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return new MetricsModule_ProvidesSimbaEventProcessorFactory(metricsModule, provider);
    }

    public static SimbaEventProcessor provideInstance(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return proxyProvidesSimbaEventProcessor(metricsModule, provider.mo10268get());
    }

    public static SimbaEventProcessor proxyProvidesSimbaEventProcessor(MetricsModule metricsModule, UiEventReporter uiEventReporter) {
        return (SimbaEventProcessor) Preconditions.checkNotNull(metricsModule.providesSimbaEventProcessor(uiEventReporter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SimbaEventProcessor mo10268get() {
        return provideInstance(this.module, this.sdkEventReporterProvider);
    }
}
