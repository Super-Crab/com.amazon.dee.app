package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCache;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsAggregatorRecoveryImpl_Factory implements Factory<MetricsAggregatorRecoveryImpl> {
    private final Provider<MetricsAggregatorCache> metricsAggregatorCacheProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;

    public MetricsAggregatorRecoveryImpl_Factory(Provider<MetricsAggregatorCache> provider, Provider<MetricsAggregator> provider2) {
        this.metricsAggregatorCacheProvider = provider;
        this.metricsAggregatorProvider = provider2;
    }

    public static MetricsAggregatorRecoveryImpl_Factory create(Provider<MetricsAggregatorCache> provider, Provider<MetricsAggregator> provider2) {
        return new MetricsAggregatorRecoveryImpl_Factory(provider, provider2);
    }

    public static MetricsAggregatorRecoveryImpl newMetricsAggregatorRecoveryImpl(MetricsAggregatorCache metricsAggregatorCache, MetricsAggregator metricsAggregator) {
        return new MetricsAggregatorRecoveryImpl(metricsAggregatorCache, metricsAggregator);
    }

    public static MetricsAggregatorRecoveryImpl provideInstance(Provider<MetricsAggregatorCache> provider, Provider<MetricsAggregator> provider2) {
        return new MetricsAggregatorRecoveryImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsAggregatorRecoveryImpl mo10268get() {
        return provideInstance(this.metricsAggregatorCacheProvider, this.metricsAggregatorProvider);
    }
}
