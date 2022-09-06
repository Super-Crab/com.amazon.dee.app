package com.amazon.alexa.handsfree.storage.metrics;

import com.amazon.alexa.handsfree.storage.metrics.CacheMetricsService;
import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class CacheMetricsService_ServiceHelper_Factory implements Factory<CacheMetricsService.ServiceHelper> {
    private static final CacheMetricsService_ServiceHelper_Factory INSTANCE = new CacheMetricsService_ServiceHelper_Factory();

    public static CacheMetricsService_ServiceHelper_Factory create() {
        return INSTANCE;
    }

    public static CacheMetricsService.ServiceHelper newServiceHelper() {
        return new CacheMetricsService.ServiceHelper();
    }

    public static CacheMetricsService.ServiceHelper provideInstance() {
        return new CacheMetricsService.ServiceHelper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CacheMetricsService.ServiceHelper mo10268get() {
        return provideInstance();
    }
}
