package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class NetworkingModule_ProviesObjectMapperFactory implements Factory<ObjectMapper> {
    private final NetworkingModule module;

    public NetworkingModule_ProviesObjectMapperFactory(NetworkingModule networkingModule) {
        this.module = networkingModule;
    }

    public static NetworkingModule_ProviesObjectMapperFactory create(NetworkingModule networkingModule) {
        return new NetworkingModule_ProviesObjectMapperFactory(networkingModule);
    }

    public static ObjectMapper provideInstance(NetworkingModule networkingModule) {
        return proxyProviesObjectMapper(networkingModule);
    }

    public static ObjectMapper proxyProviesObjectMapper(NetworkingModule networkingModule) {
        return (ObjectMapper) Preconditions.checkNotNull(networkingModule.proviesObjectMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ObjectMapper mo10268get() {
        return provideInstance(this.module);
    }
}
