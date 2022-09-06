package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideBilobaMetricsServiceFactory implements Factory<BilobaMetricsService> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideBilobaMetricsServiceFactory(ServiceModule serviceModule, Provider<Mobilytics> provider, Provider<EnvironmentService> provider2) {
        this.module = serviceModule;
        this.mobilyticsProvider = provider;
        this.environmentServiceProvider = provider2;
    }

    public static ServiceModule_ProvideBilobaMetricsServiceFactory create(ServiceModule serviceModule, Provider<Mobilytics> provider, Provider<EnvironmentService> provider2) {
        return new ServiceModule_ProvideBilobaMetricsServiceFactory(serviceModule, provider, provider2);
    }

    public static BilobaMetricsService provideInstance(ServiceModule serviceModule, Provider<Mobilytics> provider, Provider<EnvironmentService> provider2) {
        return proxyProvideBilobaMetricsService(serviceModule, provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    public static BilobaMetricsService proxyProvideBilobaMetricsService(ServiceModule serviceModule, Mobilytics mobilytics, Lazy<EnvironmentService> lazy) {
        return (BilobaMetricsService) Preconditions.checkNotNull(serviceModule.provideBilobaMetricsService(mobilytics, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BilobaMetricsService mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider, this.environmentServiceProvider);
    }
}
