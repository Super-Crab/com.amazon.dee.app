package com.amazon.commscore.metrics.dependencies;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MetricServiceModule_ProvidesMetricsServiceFactory implements Factory<AlexaCommsCoreMetricsService> {
    private final MetricServiceModule module;

    public MetricServiceModule_ProvidesMetricsServiceFactory(MetricServiceModule metricServiceModule) {
        this.module = metricServiceModule;
    }

    public static MetricServiceModule_ProvidesMetricsServiceFactory create(MetricServiceModule metricServiceModule) {
        return new MetricServiceModule_ProvidesMetricsServiceFactory(metricServiceModule);
    }

    public static AlexaCommsCoreMetricsService provideInstance(MetricServiceModule metricServiceModule) {
        return proxyProvidesMetricsService(metricServiceModule);
    }

    public static AlexaCommsCoreMetricsService proxyProvidesMetricsService(MetricServiceModule metricServiceModule) {
        return (AlexaCommsCoreMetricsService) Preconditions.checkNotNull(metricServiceModule.providesMetricsService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsCoreMetricsService mo10268get() {
        return provideInstance(this.module);
    }
}
