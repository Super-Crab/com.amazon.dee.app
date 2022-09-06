package com.amazon.dee.app.dependencies;

import android.webkit.CookieManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class NetworkModule_ProvideCookieManagerFactory implements Factory<CookieManager> {
    private final NetworkModule module;

    public NetworkModule_ProvideCookieManagerFactory(NetworkModule networkModule) {
        this.module = networkModule;
    }

    public static NetworkModule_ProvideCookieManagerFactory create(NetworkModule networkModule) {
        return new NetworkModule_ProvideCookieManagerFactory(networkModule);
    }

    public static CookieManager provideInstance(NetworkModule networkModule) {
        return proxyProvideCookieManager(networkModule);
    }

    public static CookieManager proxyProvideCookieManager(NetworkModule networkModule) {
        return (CookieManager) Preconditions.checkNotNull(networkModule.provideCookieManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CookieManager mo10268get() {
        return provideInstance(this.module);
    }
}
