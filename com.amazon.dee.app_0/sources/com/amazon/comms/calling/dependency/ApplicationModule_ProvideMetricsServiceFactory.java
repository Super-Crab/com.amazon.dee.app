package com.amazon.comms.calling.dependency;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideMetricsServiceFactory implements Factory<MetricsService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideMetricsServiceFactory(ApplicationModule applicationModule, Provider<ComponentRegistry> provider) {
        this.module = applicationModule;
        this.componentRegistryProvider = provider;
    }

    public static ApplicationModule_ProvideMetricsServiceFactory create(ApplicationModule applicationModule, Provider<ComponentRegistry> provider) {
        return new ApplicationModule_ProvideMetricsServiceFactory(applicationModule, provider);
    }

    public static MetricsService provideInstance(ApplicationModule applicationModule, Provider<ComponentRegistry> provider) {
        return proxyProvideMetricsService(applicationModule, provider.mo10268get());
    }

    public static MetricsService proxyProvideMetricsService(ApplicationModule applicationModule, ComponentRegistry componentRegistry) {
        return (MetricsService) Preconditions.checkNotNull(applicationModule.provideMetricsService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
