package com.amazon.alexa.handsfree.metrics.dependencies;

import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class AhfMetricsModule_ProvideCacheMetricsServiceServiceHelperFactory implements Factory<CacheMetricsService.ServiceHelper> {
    private final AhfMetricsModule module;

    public AhfMetricsModule_ProvideCacheMetricsServiceServiceHelperFactory(AhfMetricsModule ahfMetricsModule) {
        this.module = ahfMetricsModule;
    }

    public static AhfMetricsModule_ProvideCacheMetricsServiceServiceHelperFactory create(AhfMetricsModule ahfMetricsModule) {
        return new AhfMetricsModule_ProvideCacheMetricsServiceServiceHelperFactory(ahfMetricsModule);
    }

    public static CacheMetricsService.ServiceHelper provideInstance(AhfMetricsModule ahfMetricsModule) {
        return proxyProvideCacheMetricsServiceServiceHelper(ahfMetricsModule);
    }

    public static CacheMetricsService.ServiceHelper proxyProvideCacheMetricsServiceServiceHelper(AhfMetricsModule ahfMetricsModule) {
        return (CacheMetricsService.ServiceHelper) Preconditions.checkNotNull(ahfMetricsModule.provideCacheMetricsServiceServiceHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CacheMetricsService.ServiceHelper mo10268get() {
        return provideInstance(this.module);
    }
}
