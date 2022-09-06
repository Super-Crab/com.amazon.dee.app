package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes13.dex */
public final class NetworkingModule_ProvidesOkHttpClientFactory implements Factory<OkHttpClient> {
    private final Provider<DSSServiceConfiguration> dssServiceConfigurationProvider;
    private final NetworkingModule module;

    public NetworkingModule_ProvidesOkHttpClientFactory(NetworkingModule networkingModule, Provider<DSSServiceConfiguration> provider) {
        this.module = networkingModule;
        this.dssServiceConfigurationProvider = provider;
    }

    public static NetworkingModule_ProvidesOkHttpClientFactory create(NetworkingModule networkingModule, Provider<DSSServiceConfiguration> provider) {
        return new NetworkingModule_ProvidesOkHttpClientFactory(networkingModule, provider);
    }

    public static OkHttpClient provideInstance(NetworkingModule networkingModule, Provider<DSSServiceConfiguration> provider) {
        return proxyProvidesOkHttpClient(networkingModule, provider.mo10268get());
    }

    public static OkHttpClient proxyProvidesOkHttpClient(NetworkingModule networkingModule, DSSServiceConfiguration dSSServiceConfiguration) {
        return (OkHttpClient) Preconditions.checkNotNull(networkingModule.providesOkHttpClient(dSSServiceConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public OkHttpClient mo10268get() {
        return provideInstance(this.module, this.dssServiceConfigurationProvider);
    }
}
