package com.amazon.alexa.handsfree.storage.metrics;

import com.amazon.alexa.handsfree.storage.metrics.ProcessMetricsCacheService;
import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class ProcessMetricsCacheService_ServiceHelper_Factory implements Factory<ProcessMetricsCacheService.ServiceHelper> {
    private static final ProcessMetricsCacheService_ServiceHelper_Factory INSTANCE = new ProcessMetricsCacheService_ServiceHelper_Factory();

    public static ProcessMetricsCacheService_ServiceHelper_Factory create() {
        return INSTANCE;
    }

    public static ProcessMetricsCacheService.ServiceHelper newServiceHelper() {
        return new ProcessMetricsCacheService.ServiceHelper();
    }

    public static ProcessMetricsCacheService.ServiceHelper provideInstance() {
        return new ProcessMetricsCacheService.ServiceHelper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProcessMetricsCacheService.ServiceHelper mo10268get() {
        return provideInstance();
    }
}
