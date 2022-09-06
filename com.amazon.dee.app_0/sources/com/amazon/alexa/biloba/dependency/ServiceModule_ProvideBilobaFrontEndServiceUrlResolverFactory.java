package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.service.BilobaFrontEndServiceUrlResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideBilobaFrontEndServiceUrlResolverFactory implements Factory<BilobaFrontEndServiceUrlResolver> {
    private final ServiceModule module;

    public ServiceModule_ProvideBilobaFrontEndServiceUrlResolverFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideBilobaFrontEndServiceUrlResolverFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideBilobaFrontEndServiceUrlResolverFactory(serviceModule);
    }

    public static BilobaFrontEndServiceUrlResolver provideInstance(ServiceModule serviceModule) {
        return proxyProvideBilobaFrontEndServiceUrlResolver(serviceModule);
    }

    public static BilobaFrontEndServiceUrlResolver proxyProvideBilobaFrontEndServiceUrlResolver(ServiceModule serviceModule) {
        return (BilobaFrontEndServiceUrlResolver) Preconditions.checkNotNull(serviceModule.provideBilobaFrontEndServiceUrlResolver(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BilobaFrontEndServiceUrlResolver mo10268get() {
        return provideInstance(this.module);
    }
}
