package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvidePersonIdProviderFactory implements Factory<PersonIdProvider> {
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<BilobaMetricsService> metricsServiceProvider;
    private final ServiceModule module;

    public ServiceModule_ProvidePersonIdProviderFactory(ServiceModule serviceModule, Provider<IdentityService> provider, Provider<BilobaMetricsService> provider2) {
        this.module = serviceModule;
        this.identityServiceProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static ServiceModule_ProvidePersonIdProviderFactory create(ServiceModule serviceModule, Provider<IdentityService> provider, Provider<BilobaMetricsService> provider2) {
        return new ServiceModule_ProvidePersonIdProviderFactory(serviceModule, provider, provider2);
    }

    public static PersonIdProvider provideInstance(ServiceModule serviceModule, Provider<IdentityService> provider, Provider<BilobaMetricsService> provider2) {
        return proxyProvidePersonIdProvider(serviceModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static PersonIdProvider proxyProvidePersonIdProvider(ServiceModule serviceModule, IdentityService identityService, BilobaMetricsService bilobaMetricsService) {
        return (PersonIdProvider) Preconditions.checkNotNull(serviceModule.providePersonIdProvider(identityService, bilobaMetricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersonIdProvider mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider, this.metricsServiceProvider);
    }
}
