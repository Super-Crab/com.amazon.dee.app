package com.amazon.alexa.handsfree.protocols.metrics;

import com.amazon.alexa.handsfree.protocols.metrics.caching.MetricSerializer;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsConfiguration_Factory implements Factory<MetricsConfiguration> {
    private final Provider<MetricFactoryProvider> metricFactoryProvider;
    private final Provider<MetricSerializer> metricSerializerProvider;
    private final Provider<MetricsEmitter> metricsEmitterProvider;

    public MetricsConfiguration_Factory(Provider<MetricsEmitter> provider, Provider<MetricFactoryProvider> provider2, Provider<MetricSerializer> provider3) {
        this.metricsEmitterProvider = provider;
        this.metricFactoryProvider = provider2;
        this.metricSerializerProvider = provider3;
    }

    public static MetricsConfiguration_Factory create(Provider<MetricsEmitter> provider, Provider<MetricFactoryProvider> provider2, Provider<MetricSerializer> provider3) {
        return new MetricsConfiguration_Factory(provider, provider2, provider3);
    }

    public static MetricsConfiguration newMetricsConfiguration(MetricsEmitter metricsEmitter, MetricFactoryProvider metricFactoryProvider, MetricSerializer metricSerializer) {
        return new MetricsConfiguration(metricsEmitter, metricFactoryProvider, metricSerializer);
    }

    public static MetricsConfiguration provideInstance(Provider<MetricsEmitter> provider, Provider<MetricFactoryProvider> provider2, Provider<MetricSerializer> provider3) {
        return new MetricsConfiguration(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsConfiguration mo10268get() {
        return provideInstance(this.metricsEmitterProvider, this.metricFactoryProvider, this.metricSerializerProvider);
    }
}
