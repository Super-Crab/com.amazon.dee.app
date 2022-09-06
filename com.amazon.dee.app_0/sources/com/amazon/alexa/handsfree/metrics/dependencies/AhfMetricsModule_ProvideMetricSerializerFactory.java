package com.amazon.alexa.handsfree.metrics.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideMetricSerializerFactory implements Factory<MetricSerializer> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProviderLazyProvider;
    private final AhfMetricsModule module;

    public AhfMetricsModule_ProvideMetricSerializerFactory(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        this.module = ahfMetricsModule;
        this.contextProvider = provider;
        this.metricsBuilderProviderLazyProvider = provider2;
    }

    public static AhfMetricsModule_ProvideMetricSerializerFactory create(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new AhfMetricsModule_ProvideMetricSerializerFactory(ahfMetricsModule, provider, provider2);
    }

    public static MetricSerializer provideInstance(AhfMetricsModule ahfMetricsModule, Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return proxyProvideMetricSerializer(ahfMetricsModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static MetricSerializer proxyProvideMetricSerializer(AhfMetricsModule ahfMetricsModule, Context context, Lazy<MetricsBuilderProvider> lazy) {
        return (MetricSerializer) Preconditions.checkNotNull(ahfMetricsModule.provideMetricSerializer(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricSerializer mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.metricsBuilderProviderLazyProvider);
    }
}
