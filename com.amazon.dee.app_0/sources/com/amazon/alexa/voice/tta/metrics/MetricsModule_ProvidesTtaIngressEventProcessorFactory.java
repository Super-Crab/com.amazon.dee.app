package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesTtaIngressEventProcessorFactory implements Factory<TtaIngressEventProcessor> {
    private final MetricsModule module;
    private final Provider<UiEventReporter> sdkEventReporterProvider;

    public MetricsModule_ProvidesTtaIngressEventProcessorFactory(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        this.module = metricsModule;
        this.sdkEventReporterProvider = provider;
    }

    public static MetricsModule_ProvidesTtaIngressEventProcessorFactory create(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return new MetricsModule_ProvidesTtaIngressEventProcessorFactory(metricsModule, provider);
    }

    public static TtaIngressEventProcessor provideInstance(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return proxyProvidesTtaIngressEventProcessor(metricsModule, provider.mo10268get());
    }

    public static TtaIngressEventProcessor proxyProvidesTtaIngressEventProcessor(MetricsModule metricsModule, UiEventReporter uiEventReporter) {
        return (TtaIngressEventProcessor) Preconditions.checkNotNull(metricsModule.providesTtaIngressEventProcessor(uiEventReporter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaIngressEventProcessor mo10268get() {
        return provideInstance(this.module, this.sdkEventReporterProvider);
    }
}
