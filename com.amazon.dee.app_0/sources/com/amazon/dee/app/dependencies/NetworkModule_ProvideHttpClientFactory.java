package com.amazon.dee.app.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;
/* loaded from: classes12.dex */
public final class NetworkModule_ProvideHttpClientFactory implements Factory<OkHttpClient> {
    private final NetworkModule module;

    public NetworkModule_ProvideHttpClientFactory(NetworkModule networkModule) {
        this.module = networkModule;
    }

    public static NetworkModule_ProvideHttpClientFactory create(NetworkModule networkModule) {
        return new NetworkModule_ProvideHttpClientFactory(networkModule);
    }

    public static OkHttpClient provideInstance(NetworkModule networkModule) {
        return proxyProvideHttpClient(networkModule);
    }

    public static OkHttpClient proxyProvideHttpClient(NetworkModule networkModule) {
        return (OkHttpClient) Preconditions.checkNotNull(networkModule.provideHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public OkHttpClient mo10268get() {
        return provideInstance(this.module);
    }
}
