package com.amazon.alexa.handsfree.metrics.dependencies;

import com.amazon.alexa.handsfree.protocols.metrics.MetricRecorder;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideMetricsEmitterFactory implements Factory<MetricsEmitter> {
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final Provider<MetricsEmitterConfig> metricsEmitterConfigProvider;
    private final AhfMetricsModule module;

    public AhfMetricsModule_ProvideMetricsEmitterFactory(AhfMetricsModule ahfMetricsModule, Provider<MetricRecorder> provider, Provider<MetricsEmitterConfig> provider2) {
        this.module = ahfMetricsModule;
        this.metricRecorderProvider = provider;
        this.metricsEmitterConfigProvider = provider2;
    }

    public static AhfMetricsModule_ProvideMetricsEmitterFactory create(AhfMetricsModule ahfMetricsModule, Provider<MetricRecorder> provider, Provider<MetricsEmitterConfig> provider2) {
        return new AhfMetricsModule_ProvideMetricsEmitterFactory(ahfMetricsModule, provider, provider2);
    }

    public static MetricsEmitter provideInstance(AhfMetricsModule ahfMetricsModule, Provider<MetricRecorder> provider, Provider<MetricsEmitterConfig> provider2) {
        return proxyProvideMetricsEmitter(ahfMetricsModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MetricsEmitter proxyProvideMetricsEmitter(AhfMetricsModule ahfMetricsModule, MetricRecorder metricRecorder, MetricsEmitterConfig metricsEmitterConfig) {
        return (MetricsEmitter) Preconditions.checkNotNull(ahfMetricsModule.provideMetricsEmitter(metricRecorder, metricsEmitterConfig), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsEmitter mo10268get() {
        return provideInstance(this.module, this.metricRecorderProvider, this.metricsEmitterConfigProvider);
    }
}
