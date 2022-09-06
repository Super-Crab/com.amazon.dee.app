package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.converter.jackson.JacksonConverterFactory;
/* loaded from: classes13.dex */
public final class NetworkingModule_ProvidesJacksonConverterFactoryFactory implements Factory<JacksonConverterFactory> {
    private final NetworkingModule module;
    private final Provider<ObjectMapper> objectMapperProvider;

    public NetworkingModule_ProvidesJacksonConverterFactoryFactory(NetworkingModule networkingModule, Provider<ObjectMapper> provider) {
        this.module = networkingModule;
        this.objectMapperProvider = provider;
    }

    public static NetworkingModule_ProvidesJacksonConverterFactoryFactory create(NetworkingModule networkingModule, Provider<ObjectMapper> provider) {
        return new NetworkingModule_ProvidesJacksonConverterFactoryFactory(networkingModule, provider);
    }

    public static JacksonConverterFactory provideInstance(NetworkingModule networkingModule, Provider<ObjectMapper> provider) {
        return proxyProvidesJacksonConverterFactory(networkingModule, provider.mo10268get());
    }

    public static JacksonConverterFactory proxyProvidesJacksonConverterFactory(NetworkingModule networkingModule, ObjectMapper objectMapper) {
        return (JacksonConverterFactory) Preconditions.checkNotNull(networkingModule.providesJacksonConverterFactory(objectMapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public JacksonConverterFactory mo10268get() {
        return provideInstance(this.module, this.objectMapperProvider);
    }
}
