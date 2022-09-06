package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesTtaEventProcessorFactory implements Factory<TtaUiEventWithTypeProcessor> {
    private final MetricsModule module;
    private final Provider<UiEventReporter> sdkEventReporterProvider;

    public MetricsModule_ProvidesTtaEventProcessorFactory(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        this.module = metricsModule;
        this.sdkEventReporterProvider = provider;
    }

    public static MetricsModule_ProvidesTtaEventProcessorFactory create(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return new MetricsModule_ProvidesTtaEventProcessorFactory(metricsModule, provider);
    }

    public static TtaUiEventWithTypeProcessor provideInstance(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return proxyProvidesTtaEventProcessor(metricsModule, provider.mo10268get());
    }

    public static TtaUiEventWithTypeProcessor proxyProvidesTtaEventProcessor(MetricsModule metricsModule, UiEventReporter uiEventReporter) {
        return (TtaUiEventWithTypeProcessor) Preconditions.checkNotNull(metricsModule.providesTtaEventProcessor(uiEventReporter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaUiEventWithTypeProcessor mo10268get() {
        return provideInstance(this.module, this.sdkEventReporterProvider);
    }
}
