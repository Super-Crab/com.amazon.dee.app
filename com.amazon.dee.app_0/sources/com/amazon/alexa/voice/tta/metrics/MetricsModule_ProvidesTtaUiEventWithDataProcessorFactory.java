package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesTtaUiEventWithDataProcessorFactory implements Factory<TtaUiEventWithCountProcessor> {
    private final MetricsModule module;
    private final Provider<UiEventReporter> sdkEventReporterProvider;

    public MetricsModule_ProvidesTtaUiEventWithDataProcessorFactory(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        this.module = metricsModule;
        this.sdkEventReporterProvider = provider;
    }

    public static MetricsModule_ProvidesTtaUiEventWithDataProcessorFactory create(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return new MetricsModule_ProvidesTtaUiEventWithDataProcessorFactory(metricsModule, provider);
    }

    public static TtaUiEventWithCountProcessor provideInstance(MetricsModule metricsModule, Provider<UiEventReporter> provider) {
        return proxyProvidesTtaUiEventWithDataProcessor(metricsModule, provider.mo10268get());
    }

    public static TtaUiEventWithCountProcessor proxyProvidesTtaUiEventWithDataProcessor(MetricsModule metricsModule, UiEventReporter uiEventReporter) {
        return (TtaUiEventWithCountProcessor) Preconditions.checkNotNull(metricsModule.providesTtaUiEventWithDataProcessor(uiEventReporter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaUiEventWithCountProcessor mo10268get() {
        return provideInstance(this.module, this.sdkEventReporterProvider);
    }
}
