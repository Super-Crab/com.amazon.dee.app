package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.service.BilobaFrontEndServiceUrlResolver;
import com.amazon.alexa.biloba.service.FrontEndServiceRequest;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideFrontEndServiceRequestsFactory implements Factory<FrontEndServiceRequest> {
    private final Provider<BilobaFrontEndServiceUrlResolver> frontEndServiceUrlResolverProvider;
    private final ServiceModule module;

    public ServiceModule_ProvideFrontEndServiceRequestsFactory(ServiceModule serviceModule, Provider<BilobaFrontEndServiceUrlResolver> provider) {
        this.module = serviceModule;
        this.frontEndServiceUrlResolverProvider = provider;
    }

    public static ServiceModule_ProvideFrontEndServiceRequestsFactory create(ServiceModule serviceModule, Provider<BilobaFrontEndServiceUrlResolver> provider) {
        return new ServiceModule_ProvideFrontEndServiceRequestsFactory(serviceModule, provider);
    }

    public static FrontEndServiceRequest provideInstance(ServiceModule serviceModule, Provider<BilobaFrontEndServiceUrlResolver> provider) {
        return proxyProvideFrontEndServiceRequests(serviceModule, provider.mo10268get());
    }

    public static FrontEndServiceRequest proxyProvideFrontEndServiceRequests(ServiceModule serviceModule, BilobaFrontEndServiceUrlResolver bilobaFrontEndServiceUrlResolver) {
        return (FrontEndServiceRequest) Preconditions.checkNotNull(serviceModule.provideFrontEndServiceRequests(bilobaFrontEndServiceUrlResolver), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FrontEndServiceRequest mo10268get() {
        return provideInstance(this.module, this.frontEndServiceUrlResolverProvider);
    }
}
