package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideBilobaUrlResolverFactory implements Factory<BilobaUrlResolver> {
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideBilobaUrlResolverFactory(ServiceModule serviceModule, Provider<EnvironmentService> provider) {
        this.module = serviceModule;
        this.environmentServiceProvider = provider;
    }

    public static ServiceModule_ProvideBilobaUrlResolverFactory create(ServiceModule serviceModule, Provider<EnvironmentService> provider) {
        return new ServiceModule_ProvideBilobaUrlResolverFactory(serviceModule, provider);
    }

    public static BilobaUrlResolver provideInstance(ServiceModule serviceModule, Provider<EnvironmentService> provider) {
        return proxyProvideBilobaUrlResolver(serviceModule, provider.mo10268get());
    }

    public static BilobaUrlResolver proxyProvideBilobaUrlResolver(ServiceModule serviceModule, EnvironmentService environmentService) {
        return (BilobaUrlResolver) Preconditions.checkNotNull(serviceModule.provideBilobaUrlResolver(environmentService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BilobaUrlResolver mo10268get() {
        return provideInstance(this.module, this.environmentServiceProvider);
    }
}
