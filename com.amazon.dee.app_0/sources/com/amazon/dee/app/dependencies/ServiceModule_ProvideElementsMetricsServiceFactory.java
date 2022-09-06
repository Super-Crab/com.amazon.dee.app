package com.amazon.dee.app.dependencies;

import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideElementsMetricsServiceFactory implements Factory<MetricsServiceV2> {
    private final Provider<MetricsService> metricsServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideElementsMetricsServiceFactory(ServiceModule serviceModule, Provider<MetricsService> provider) {
        this.module = serviceModule;
        this.metricsServiceProvider = provider;
    }

    public static ServiceModule_ProvideElementsMetricsServiceFactory create(ServiceModule serviceModule, Provider<MetricsService> provider) {
        return new ServiceModule_ProvideElementsMetricsServiceFactory(serviceModule, provider);
    }

    public static MetricsServiceV2 provideInstance(ServiceModule serviceModule, Provider<MetricsService> provider) {
        return proxyProvideElementsMetricsService(serviceModule, provider.mo10268get());
    }

    public static MetricsServiceV2 proxyProvideElementsMetricsService(ServiceModule serviceModule, MetricsService metricsService) {
        return (MetricsServiceV2) Preconditions.checkNotNull(serviceModule.provideElementsMetricsService(metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsServiceV2 mo10268get() {
        return provideInstance(this.module, this.metricsServiceProvider);
    }
}
