package com.amazon.alexa.handsfree.metrics;

import com.amazon.alexa.handsfree.metrics.FirstStartupMetricJobService;
import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class FirstStartupMetricJobService_Helper_Factory implements Factory<FirstStartupMetricJobService.Helper> {
    private static final FirstStartupMetricJobService_Helper_Factory INSTANCE = new FirstStartupMetricJobService_Helper_Factory();

    public static FirstStartupMetricJobService_Helper_Factory create() {
        return INSTANCE;
    }

    public static FirstStartupMetricJobService.Helper newHelper() {
        return new FirstStartupMetricJobService.Helper();
    }

    public static FirstStartupMetricJobService.Helper provideInstance() {
        return new FirstStartupMetricJobService.Helper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FirstStartupMetricJobService.Helper mo10268get() {
        return provideInstance();
    }
}
