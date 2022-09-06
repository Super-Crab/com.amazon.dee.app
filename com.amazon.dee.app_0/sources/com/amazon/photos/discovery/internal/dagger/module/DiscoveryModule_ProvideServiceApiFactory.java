package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.internal.server.ServiceApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideServiceApiFactory implements Factory<ServiceApi> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideServiceApiFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideServiceApiFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideServiceApiFactory(discoveryModule);
    }

    public static ServiceApi provideServiceApi(DiscoveryModule discoveryModule) {
        return (ServiceApi) Preconditions.checkNotNull(discoveryModule.provideServiceApi(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ServiceApi mo10268get() {
        return provideServiceApi(this.module);
    }
}
