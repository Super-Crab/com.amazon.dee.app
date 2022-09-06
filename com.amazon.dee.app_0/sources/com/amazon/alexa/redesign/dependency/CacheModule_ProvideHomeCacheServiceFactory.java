package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.cache.HomeCacheService;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class CacheModule_ProvideHomeCacheServiceFactory implements Factory<HomeCacheService> {
    private final Provider<Cache<AppDataCacheEntry>> cacheProvider;
    private final CacheModule module;

    public CacheModule_ProvideHomeCacheServiceFactory(CacheModule cacheModule, Provider<Cache<AppDataCacheEntry>> provider) {
        this.module = cacheModule;
        this.cacheProvider = provider;
    }

    public static CacheModule_ProvideHomeCacheServiceFactory create(CacheModule cacheModule, Provider<Cache<AppDataCacheEntry>> provider) {
        return new CacheModule_ProvideHomeCacheServiceFactory(cacheModule, provider);
    }

    public static HomeCacheService provideInstance(CacheModule cacheModule, Provider<Cache<AppDataCacheEntry>> provider) {
        return proxyProvideHomeCacheService(cacheModule, provider.mo10268get());
    }

    public static HomeCacheService proxyProvideHomeCacheService(CacheModule cacheModule, Cache<AppDataCacheEntry> cache) {
        return (HomeCacheService) Preconditions.checkNotNull(cacheModule.provideHomeCacheService(cache), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeCacheService mo10268get() {
        return provideInstance(this.module, this.cacheProvider);
    }
}
