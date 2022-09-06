package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesTtaUiEventProcessorFactory implements Factory<TtaUiEventProcessor> {
    private final MetricsModule module;
    private final Provider<UiEventReporter> sdkEventReporterProvider;

    public MetricsModule_ProvidesTtaUiEventProcessorFactory(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        this.module = metricsModule;
        this.sdkEventReporterProvider = provider;
    }

    public static MetricsModule_ProvidesTtaUiEventProcessorFactory create(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return new MetricsModule_ProvidesTtaUiEventProcessorFactory(metricsModule, provider);
    }

    public static TtaUiEventProcessor provideInstance(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return proxyProvidesTtaUiEventProcessor(metricsModule, provider.mo10268get());
    }

    public static TtaUiEventProcessor proxyProvidesTtaUiEventProcessor(MetricsModule metricsModule, UiEventReporter uiEventReporter) {
        return (TtaUiEventProcessor) Preconditions.checkNotNull(metricsModule.providesTtaUiEventProcessor(uiEventReporter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaUiEventProcessor mo10268get() {
        return provideInstance(this.module, this.sdkEventReporterProvider);
    }
}
