package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesTtaUplEventProcessorFactory implements Factory<TtaUplEventProcessor> {
    private final MetricsModule module;
    private final Provider<UiEventReporter> sdkEventReporterProvider;

    public MetricsModule_ProvidesTtaUplEventProcessorFactory(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        this.module = metricsModule;
        this.sdkEventReporterProvider = provider;
    }

    public static MetricsModule_ProvidesTtaUplEventProcessorFactory create(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return new MetricsModule_ProvidesTtaUplEventProcessorFactory(metricsModule, provider);
    }

    public static TtaUplEventProcessor provideInstance(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return proxyProvidesTtaUplEventProcessor(metricsModule, provider.mo10268get());
    }

    public static TtaUplEventProcessor proxyProvidesTtaUplEventProcessor(MetricsModule metricsModule, UiEventReporter uiEventReporter) {
        return (TtaUplEventProcessor) Preconditions.checkNotNull(metricsModule.providesTtaUplEventProcessor(uiEventReporter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaUplEventProcessor mo10268get() {
        return provideInstance(this.module, this.sdkEventReporterProvider);
    }
}
