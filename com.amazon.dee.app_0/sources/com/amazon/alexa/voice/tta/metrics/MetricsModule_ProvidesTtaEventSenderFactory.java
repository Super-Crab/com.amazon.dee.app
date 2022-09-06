package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvidesTtaEventSenderFactory implements Factory<TtaEventSender> {
    private final Provider<MetricEventProcessingService> eventProcessingServiceProvider;
    private final MetricsModule module;

    public MetricsModule_ProvidesTtaEventSenderFactory(MetricsModule metricsModule, Provider<MetricEventProcessingService> provider) {
        this.module = metricsModule;
        this.eventProcessingServiceProvider = provider;
    }

    public static MetricsModule_ProvidesTtaEventSenderFactory create(MetricsModule metricsModule, Provider<MetricEventProcessingService> provider) {
        return new MetricsModule_ProvidesTtaEventSenderFactory(metricsModule, provider);
    }

    public static TtaEventSender provideInstance(MetricsModule metricsModule, Provider<MetricEventProcessingService> provider) {
        return proxyProvidesTtaEventSender(metricsModule, provider.mo10268get());
    }

    public static TtaEventSender proxyProvidesTtaEventSender(MetricsModule metricsModule, MetricEventProcessingService metricEventProcessingService) {
        return (TtaEventSender) Preconditions.checkNotNull(metricsModule.providesTtaEventSender(metricEventProcessingService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaEventSender mo10268get() {
        return provideInstance(this.module, this.eventProcessingServiceProvider);
    }
}
