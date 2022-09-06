package com.amazon.dee.app.dependencies;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class NetworkModule_ProvideGsonFactory implements Factory<Gson> {
    private final NetworkModule module;

    public NetworkModule_ProvideGsonFactory(NetworkModule networkModule) {
        this.module = networkModule;
    }

    public static NetworkModule_ProvideGsonFactory create(NetworkModule networkModule) {
        return new NetworkModule_ProvideGsonFactory(networkModule);
    }

    public static Gson provideInstance(NetworkModule networkModule) {
        return proxyProvideGson(networkModule);
    }

    public static Gson proxyProvideGson(NetworkModule networkModule) {
        return (Gson) Preconditions.checkNotNull(networkModule.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Gson mo10268get() {
        return provideInstance(this.module);
    }
}
