package com.amazon.alexa.featureservice.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public final class NetworkModule_ProvidesOkHttpClientFactory implements Factory<OkHttpClient> {
    private final NetworkModule module;

    public NetworkModule_ProvidesOkHttpClientFactory(NetworkModule networkModule) {
        this.module = networkModule;
    }

    public static NetworkModule_ProvidesOkHttpClientFactory create(NetworkModule networkModule) {
        return new NetworkModule_ProvidesOkHttpClientFactory(networkModule);
    }

    public static OkHttpClient provideInstance(NetworkModule networkModule) {
        return proxyProvidesOkHttpClient(networkModule);
    }

    public static OkHttpClient proxyProvidesOkHttpClient(NetworkModule networkModule) {
        return (OkHttpClient) Preconditions.checkNotNull(networkModule.providesOkHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public OkHttpClient mo10268get() {
        return provideInstance(this.module);
    }
}
