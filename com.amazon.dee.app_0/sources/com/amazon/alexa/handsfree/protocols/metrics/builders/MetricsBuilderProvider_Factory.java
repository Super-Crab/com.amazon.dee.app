package com.amazon.alexa.handsfree.protocols.metrics.builders;

import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsBuilderProvider_Factory implements Factory<MetricsBuilderProvider> {
    private final Provider<MetricsConfiguration> metricsConfigurationProvider;

    public MetricsBuilderProvider_Factory(Provider<MetricsConfiguration> provider) {
        this.metricsConfigurationProvider = provider;
    }

    public static MetricsBuilderProvider_Factory create(Provider<MetricsConfiguration> provider) {
        return new MetricsBuilderProvider_Factory(provider);
    }

    public static MetricsBuilderProvider newMetricsBuilderProvider(MetricsConfiguration metricsConfiguration) {
        return new MetricsBuilderProvider(metricsConfiguration);
    }

    public static MetricsBuilderProvider provideInstance(Provider<MetricsConfiguration> provider) {
        return new MetricsBuilderProvider(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsBuilderProvider mo10268get() {
        return provideInstance(this.metricsConfigurationProvider);
    }
}
