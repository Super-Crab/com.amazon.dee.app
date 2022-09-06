package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.repository.MetricsAggregatorCache;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsAggregatorImpl_Factory implements Factory<MetricsAggregatorImpl> {
    private final Provider<MetricsAggregatorCache> metricsAggregatorCacheProvider;
    private final Provider<Mobilytics> mobilyticsProvider;

    public MetricsAggregatorImpl_Factory(Provider<Mobilytics> provider, Provider<MetricsAggregatorCache> provider2) {
        this.mobilyticsProvider = provider;
        this.metricsAggregatorCacheProvider = provider2;
    }

    public static MetricsAggregatorImpl_Factory create(Provider<Mobilytics> provider, Provider<MetricsAggregatorCache> provider2) {
        return new MetricsAggregatorImpl_Factory(provider, provider2);
    }

    public static MetricsAggregatorImpl newMetricsAggregatorImpl(Mobilytics mobilytics, MetricsAggregatorCache metricsAggregatorCache) {
        return new MetricsAggregatorImpl(mobilytics, metricsAggregatorCache);
    }

    public static MetricsAggregatorImpl provideInstance(Provider<Mobilytics> provider, Provider<MetricsAggregatorCache> provider2) {
        return new MetricsAggregatorImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsAggregatorImpl mo10268get() {
        return provideInstance(this.mobilyticsProvider, this.metricsAggregatorCacheProvider);
    }
}
