package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.service.BilobaFrontEndService;
import com.amazon.alexa.biloba.service.FrontEndServiceRequest;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideBilobaFrontEndServiceFactory implements Factory<BilobaFrontEndService> {
    private final Provider<FrontEndServiceRequest> frontEndServiceRequestProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideBilobaFrontEndServiceFactory(ServiceModule serviceModule, Provider<FrontEndServiceRequest> provider) {
        this.module = serviceModule;
        this.frontEndServiceRequestProvider = provider;
    }

    public static ServiceModule_ProvideBilobaFrontEndServiceFactory create(ServiceModule serviceModule, Provider<FrontEndServiceRequest> provider) {
        return new ServiceModule_ProvideBilobaFrontEndServiceFactory(serviceModule, provider);
    }

    public static BilobaFrontEndService provideInstance(ServiceModule serviceModule, Provider<FrontEndServiceRequest> provider) {
        return proxyProvideBilobaFrontEndService(serviceModule, provider.mo10268get());
    }

    public static BilobaFrontEndService proxyProvideBilobaFrontEndService(ServiceModule serviceModule, FrontEndServiceRequest frontEndServiceRequest) {
        return (BilobaFrontEndService) Preconditions.checkNotNull(serviceModule.provideBilobaFrontEndService(frontEndServiceRequest), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BilobaFrontEndService mo10268get() {
        return provideInstance(this.module, this.frontEndServiceRequestProvider);
    }
}
