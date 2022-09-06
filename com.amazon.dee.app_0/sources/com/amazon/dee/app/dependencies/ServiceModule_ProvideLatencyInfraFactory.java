package com.amazon.dee.app.dependencies;

import com.amazon.latencyinfra.LatencyInfra;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideLatencyInfraFactory implements Factory<LatencyInfra> {
    private final ServiceModule module;

    public ServiceModule_ProvideLatencyInfraFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideLatencyInfraFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideLatencyInfraFactory(serviceModule);
    }

    public static LatencyInfra provideInstance(ServiceModule serviceModule) {
        return proxyProvideLatencyInfra(serviceModule);
    }

    public static LatencyInfra proxyProvideLatencyInfra(ServiceModule serviceModule) {
        return (LatencyInfra) Preconditions.checkNotNull(serviceModule.provideLatencyInfra(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LatencyInfra mo10268get() {
        return provideInstance(this.module);
    }
}
