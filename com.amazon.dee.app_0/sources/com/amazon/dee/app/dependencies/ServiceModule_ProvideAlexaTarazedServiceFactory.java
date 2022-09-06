package com.amazon.dee.app.dependencies;

import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideAlexaTarazedServiceFactory implements Factory<AlexaTarazedService> {
    private final ServiceModule module;

    public ServiceModule_ProvideAlexaTarazedServiceFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideAlexaTarazedServiceFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideAlexaTarazedServiceFactory(serviceModule);
    }

    public static AlexaTarazedService provideInstance(ServiceModule serviceModule) {
        return proxyProvideAlexaTarazedService(serviceModule);
    }

    public static AlexaTarazedService proxyProvideAlexaTarazedService(ServiceModule serviceModule) {
        return (AlexaTarazedService) Preconditions.checkNotNull(serviceModule.provideAlexaTarazedService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaTarazedService mo10268get() {
        return provideInstance(this.module);
    }
}
