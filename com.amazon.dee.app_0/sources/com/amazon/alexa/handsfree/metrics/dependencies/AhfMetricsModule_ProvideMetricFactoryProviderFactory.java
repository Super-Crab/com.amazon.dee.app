package com.amazon.alexa.handsfree.metrics.dependencies;

import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideMetricFactoryProviderFactory implements Factory<MetricFactoryProvider> {
    private final AhfMetricsModule module;

    public AhfMetricsModule_ProvideMetricFactoryProviderFactory(AhfMetricsModule ahfMetricsModule) {
        this.module = ahfMetricsModule;
    }

    public static AhfMetricsModule_ProvideMetricFactoryProviderFactory create(AhfMetricsModule ahfMetricsModule) {
        return new AhfMetricsModule_ProvideMetricFactoryProviderFactory(ahfMetricsModule);
    }

    public static MetricFactoryProvider provideInstance(AhfMetricsModule ahfMetricsModule) {
        return proxyProvideMetricFactoryProvider(ahfMetricsModule);
    }

    public static MetricFactoryProvider proxyProvideMetricFactoryProvider(AhfMetricsModule ahfMetricsModule) {
        return (MetricFactoryProvider) Preconditions.checkNotNull(ahfMetricsModule.provideMetricFactoryProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricFactoryProvider mo10268get() {
        return provideInstance(this.module);
    }
}
