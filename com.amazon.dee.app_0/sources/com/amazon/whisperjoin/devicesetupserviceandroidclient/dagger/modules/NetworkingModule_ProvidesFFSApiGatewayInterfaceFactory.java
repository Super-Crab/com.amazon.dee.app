package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.FFSApiGatewayInterface;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.converter.jackson.JacksonConverterFactory;
/* loaded from: classes13.dex */
public final class NetworkingModule_ProvidesFFSApiGatewayInterfaceFactory implements Factory<FFSApiGatewayInterface> {
    private final Provider<DSSServiceConfiguration> dssServiceConfigurationProvider;
    private final Provider<JacksonConverterFactory> jacksonConverterFactoryProvider;
    private final NetworkingModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public NetworkingModule_ProvidesFFSApiGatewayInterfaceFactory(NetworkingModule networkingModule, Provider<DSSServiceConfiguration> provider, Provider<OkHttpClient> provider2, Provider<JacksonConverterFactory> provider3) {
        this.module = networkingModule;
        this.dssServiceConfigurationProvider = provider;
        this.okHttpClientProvider = provider2;
        this.jacksonConverterFactoryProvider = provider3;
    }

    public static NetworkingModule_ProvidesFFSApiGatewayInterfaceFactory create(NetworkingModule networkingModule, Provider<DSSServiceConfiguration> provider, Provider<OkHttpClient> provider2, Provider<JacksonConverterFactory> provider3) {
        return new NetworkingModule_ProvidesFFSApiGatewayInterfaceFactory(networkingModule, provider, provider2, provider3);
    }

    public static FFSApiGatewayInterface provideInstance(NetworkingModule networkingModule, Provider<DSSServiceConfiguration> provider, Provider<OkHttpClient> provider2, Provider<JacksonConverterFactory> provider3) {
        return proxyProvidesFFSApiGatewayInterface(networkingModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static FFSApiGatewayInterface proxyProvidesFFSApiGatewayInterface(NetworkingModule networkingModule, DSSServiceConfiguration dSSServiceConfiguration, OkHttpClient okHttpClient, JacksonConverterFactory jacksonConverterFactory) {
        return (FFSApiGatewayInterface) Preconditions.checkNotNull(networkingModule.providesFFSApiGatewayInterface(dSSServiceConfiguration, okHttpClient, jacksonConverterFactory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FFSApiGatewayInterface mo10268get() {
        return provideInstance(this.module, this.dssServiceConfigurationProvider, this.okHttpClientProvider, this.jacksonConverterFactoryProvider);
    }
}
