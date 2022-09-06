package com.amazon.alexa.fitness.repository;

import android.content.Context;
import com.amazon.alexa.fitness.configuration.CacheProviderConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class CacheProviderImpl_Factory implements Factory<CacheProviderImpl> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<CacheProviderConfigurationProvider> cacheProviderConfigurationProvider;

    public CacheProviderImpl_Factory(Provider<Context> provider, Provider<CacheProviderConfigurationProvider> provider2) {
        this.applicationContextProvider = provider;
        this.cacheProviderConfigurationProvider = provider2;
    }

    public static CacheProviderImpl_Factory create(Provider<Context> provider, Provider<CacheProviderConfigurationProvider> provider2) {
        return new CacheProviderImpl_Factory(provider, provider2);
    }

    public static CacheProviderImpl newCacheProviderImpl(Context context, CacheProviderConfigurationProvider cacheProviderConfigurationProvider) {
        return new CacheProviderImpl(context, cacheProviderConfigurationProvider);
    }

    public static CacheProviderImpl provideInstance(Provider<Context> provider, Provider<CacheProviderConfigurationProvider> provider2) {
        return new CacheProviderImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CacheProviderImpl mo10268get() {
        return provideInstance(this.applicationContextProvider, this.cacheProviderConfigurationProvider);
    }
}
