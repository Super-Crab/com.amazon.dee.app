package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class NetworkingModule_ProvidesDSSServiceConfigurationFactory implements Factory<DSSServiceConfiguration> {
    private final NetworkingModule module;

    public NetworkingModule_ProvidesDSSServiceConfigurationFactory(NetworkingModule networkingModule) {
        this.module = networkingModule;
    }

    public static NetworkingModule_ProvidesDSSServiceConfigurationFactory create(NetworkingModule networkingModule) {
        return new NetworkingModule_ProvidesDSSServiceConfigurationFactory(networkingModule);
    }

    public static DSSServiceConfiguration provideInstance(NetworkingModule networkingModule) {
        return proxyProvidesDSSServiceConfiguration(networkingModule);
    }

    public static DSSServiceConfiguration proxyProvidesDSSServiceConfiguration(NetworkingModule networkingModule) {
        return (DSSServiceConfiguration) Preconditions.checkNotNull(networkingModule.providesDSSServiceConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DSSServiceConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
