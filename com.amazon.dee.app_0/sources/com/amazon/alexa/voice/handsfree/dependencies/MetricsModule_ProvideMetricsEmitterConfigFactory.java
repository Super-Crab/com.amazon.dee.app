package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvideMetricsEmitterConfigFactory implements Factory<MetricsEmitterConfig> {
    private final MetricsModule module;

    public MetricsModule_ProvideMetricsEmitterConfigFactory(MetricsModule metricsModule) {
        this.module = metricsModule;
    }

    public static MetricsModule_ProvideMetricsEmitterConfigFactory create(MetricsModule metricsModule) {
        return new MetricsModule_ProvideMetricsEmitterConfigFactory(metricsModule);
    }

    public static MetricsEmitterConfig provideInstance(MetricsModule metricsModule) {
        return proxyProvideMetricsEmitterConfig(metricsModule);
    }

    public static MetricsEmitterConfig proxyProvideMetricsEmitterConfig(MetricsModule metricsModule) {
        return (MetricsEmitterConfig) Preconditions.checkNotNull(metricsModule.provideMetricsEmitterConfig(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsEmitterConfig mo10268get() {
        return provideInstance(this.module);
    }
}
