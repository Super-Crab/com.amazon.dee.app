package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsAggregatorCacheImpl_Factory implements Factory<MetricsAggregatorCacheImpl> {
    private final Provider<CacheProvider> cacheProvider;
    private final Provider<ILog> logProvider;

    public MetricsAggregatorCacheImpl_Factory(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        this.cacheProvider = provider;
        this.logProvider = provider2;
    }

    public static MetricsAggregatorCacheImpl_Factory create(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        return new MetricsAggregatorCacheImpl_Factory(provider, provider2);
    }

    public static MetricsAggregatorCacheImpl newMetricsAggregatorCacheImpl(CacheProvider cacheProvider, ILog iLog) {
        return new MetricsAggregatorCacheImpl(cacheProvider, iLog);
    }

    public static MetricsAggregatorCacheImpl provideInstance(Provider<CacheProvider> provider, Provider<ILog> provider2) {
        return new MetricsAggregatorCacheImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsAggregatorCacheImpl mo10268get() {
        return provideInstance(this.cacheProvider, this.logProvider);
    }
}
