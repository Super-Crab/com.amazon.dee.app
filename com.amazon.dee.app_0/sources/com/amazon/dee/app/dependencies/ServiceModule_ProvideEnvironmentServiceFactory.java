package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.environment.DataRegionEnvironmentService;
import com.amazon.dee.app.services.environment.PfmEnvironmentService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideEnvironmentServiceFactory implements Factory<EnvironmentService> {
    private final Provider<DataRegionEnvironmentService> dataRegionEnvironmentServiceProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final ServiceModule module;
    private final Provider<PfmEnvironmentService> pfmEnvironmentServiceProvider;

    public ServiceModule_ProvideEnvironmentServiceFactory(ServiceModule serviceModule, Provider<PfmEnvironmentService> provider, Provider<DataRegionEnvironmentService> provider2, Provider<IdentityService> provider3) {
        this.module = serviceModule;
        this.pfmEnvironmentServiceProvider = provider;
        this.dataRegionEnvironmentServiceProvider = provider2;
        this.identityServiceProvider = provider3;
    }

    public static ServiceModule_ProvideEnvironmentServiceFactory create(ServiceModule serviceModule, Provider<PfmEnvironmentService> provider, Provider<DataRegionEnvironmentService> provider2, Provider<IdentityService> provider3) {
        return new ServiceModule_ProvideEnvironmentServiceFactory(serviceModule, provider, provider2, provider3);
    }

    public static EnvironmentService provideInstance(ServiceModule serviceModule, Provider<PfmEnvironmentService> provider, Provider<DataRegionEnvironmentService> provider2, Provider<IdentityService> provider3) {
        return proxyProvideEnvironmentService(serviceModule, provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3));
    }

    public static EnvironmentService proxyProvideEnvironmentService(ServiceModule serviceModule, PfmEnvironmentService pfmEnvironmentService, DataRegionEnvironmentService dataRegionEnvironmentService, Lazy<IdentityService> lazy) {
        return (EnvironmentService) Preconditions.checkNotNull(serviceModule.provideEnvironmentService(pfmEnvironmentService, dataRegionEnvironmentService, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module, this.pfmEnvironmentServiceProvider, this.dataRegionEnvironmentServiceProvider, this.identityServiceProvider);
    }
}
