package com.amazon.alexa.handsfree.storage.metrics;

import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.storage.metrics.database.MetricsCacheDatabaseHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class CacheMetricsService_MembersInjector implements MembersInjector<CacheMetricsService> {
    private final Provider<Initializer> mInitializerProvider;
    private final Provider<MetricsCacheDatabaseHelper> mMetricsCacheDatabaseHelperProvider;

    public CacheMetricsService_MembersInjector(Provider<Initializer> provider, Provider<MetricsCacheDatabaseHelper> provider2) {
        this.mInitializerProvider = provider;
        this.mMetricsCacheDatabaseHelperProvider = provider2;
    }

    public static MembersInjector<CacheMetricsService> create(Provider<Initializer> provider, Provider<MetricsCacheDatabaseHelper> provider2) {
        return new CacheMetricsService_MembersInjector(provider, provider2);
    }

    public static void injectMInitializer(CacheMetricsService cacheMetricsService, Initializer initializer) {
        cacheMetricsService.mInitializer = initializer;
    }

    public static void injectMMetricsCacheDatabaseHelper(CacheMetricsService cacheMetricsService, MetricsCacheDatabaseHelper metricsCacheDatabaseHelper) {
        cacheMetricsService.mMetricsCacheDatabaseHelper = metricsCacheDatabaseHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CacheMetricsService cacheMetricsService) {
        injectMInitializer(cacheMetricsService, this.mInitializerProvider.mo10268get());
        injectMMetricsCacheDatabaseHelper(cacheMetricsService, this.mMetricsCacheDatabaseHelperProvider.mo10268get());
    }
}
