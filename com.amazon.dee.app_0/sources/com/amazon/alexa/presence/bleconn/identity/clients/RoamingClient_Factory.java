package com.amazon.alexa.presence.bleconn.identity.clients;

import com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes9.dex */
public final class RoamingClient_Factory implements Factory<RoamingClient> {
    private final Provider<RoamingClient.Config> configProvider;
    private final Provider<OkHttpClient> httpClientProvider;

    public RoamingClient_Factory(Provider<RoamingClient.Config> provider, Provider<OkHttpClient> provider2) {
        this.configProvider = provider;
        this.httpClientProvider = provider2;
    }

    public static RoamingClient_Factory create(Provider<RoamingClient.Config> provider, Provider<OkHttpClient> provider2) {
        return new RoamingClient_Factory(provider, provider2);
    }

    public static RoamingClient newRoamingClient(RoamingClient.Config config, OkHttpClient okHttpClient) {
        return new RoamingClient(config, okHttpClient);
    }

    public static RoamingClient provideInstance(Provider<RoamingClient.Config> provider, Provider<OkHttpClient> provider2) {
        return new RoamingClient(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoamingClient mo10268get() {
        return provideInstance(this.configProvider, this.httpClientProvider);
    }
}
