package com.amazon.alexa.fitness.dagger;

import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.repository.MetricsAggregatorCache;
import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class StaticReleasePackageModule_ProvideMetricsAggregatorFactory implements Factory<MetricsAggregator> {
    private final Provider<MetricsAggregatorCache> metricsAggregatorCacheProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final StaticReleasePackageModule module;

    public StaticReleasePackageModule_ProvideMetricsAggregatorFactory(StaticReleasePackageModule staticReleasePackageModule, Provider<Mobilytics> provider, Provider<MetricsAggregatorCache> provider2) {
        this.module = staticReleasePackageModule;
        this.mobilyticsProvider = provider;
        this.metricsAggregatorCacheProvider = provider2;
    }

    public static StaticReleasePackageModule_ProvideMetricsAggregatorFactory create(StaticReleasePackageModule staticReleasePackageModule, Provider<Mobilytics> provider, Provider<MetricsAggregatorCache> provider2) {
        return new StaticReleasePackageModule_ProvideMetricsAggregatorFactory(staticReleasePackageModule, provider, provider2);
    }

    public static MetricsAggregator provideInstance(StaticReleasePackageModule staticReleasePackageModule, Provider<Mobilytics> provider, Provider<MetricsAggregatorCache> provider2) {
        return proxyProvideMetricsAggregator(staticReleasePackageModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MetricsAggregator proxyProvideMetricsAggregator(StaticReleasePackageModule staticReleasePackageModule, Mobilytics mobilytics, MetricsAggregatorCache metricsAggregatorCache) {
        return (MetricsAggregator) Preconditions.checkNotNull(staticReleasePackageModule.provideMetricsAggregator(mobilytics, metricsAggregatorCache), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsAggregator mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider, this.metricsAggregatorCacheProvider);
    }
}
