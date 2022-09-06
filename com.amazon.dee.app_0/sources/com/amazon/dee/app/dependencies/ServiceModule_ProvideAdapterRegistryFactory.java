package com.amazon.dee.app.dependencies;

import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideAdapterRegistryFactory implements Factory<RoutingRegistryAdapter> {
    private final ServiceModule module;

    public ServiceModule_ProvideAdapterRegistryFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideAdapterRegistryFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideAdapterRegistryFactory(serviceModule);
    }

    public static RoutingRegistryAdapter provideInstance(ServiceModule serviceModule) {
        return proxyProvideAdapterRegistry(serviceModule);
    }

    public static RoutingRegistryAdapter proxyProvideAdapterRegistry(ServiceModule serviceModule) {
        return (RoutingRegistryAdapter) Preconditions.checkNotNull(serviceModule.provideAdapterRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingRegistryAdapter mo10268get() {
        return provideInstance(this.module);
    }
}
