package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class UserProfileCacheImpl_Factory implements Factory<UserProfileCacheImpl> {
    private final Provider<CacheProvider> cacheProvider;
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<IdentityManager> identityManagerProvider;
    private final Provider<ILog> logProvider;

    public UserProfileCacheImpl_Factory(Provider<ComponentRegistry> provider, Provider<CacheProvider> provider2, Provider<IdentityManager> provider3, Provider<ILog> provider4) {
        this.componentRegistryProvider = provider;
        this.cacheProvider = provider2;
        this.identityManagerProvider = provider3;
        this.logProvider = provider4;
    }

    public static UserProfileCacheImpl_Factory create(Provider<ComponentRegistry> provider, Provider<CacheProvider> provider2, Provider<IdentityManager> provider3, Provider<ILog> provider4) {
        return new UserProfileCacheImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static UserProfileCacheImpl newUserProfileCacheImpl(ComponentRegistry componentRegistry, CacheProvider cacheProvider, IdentityManager identityManager, ILog iLog) {
        return new UserProfileCacheImpl(componentRegistry, cacheProvider, identityManager, iLog);
    }

    public static UserProfileCacheImpl provideInstance(Provider<ComponentRegistry> provider, Provider<CacheProvider> provider2, Provider<IdentityManager> provider3, Provider<ILog> provider4) {
        return new UserProfileCacheImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserProfileCacheImpl mo10268get() {
        return provideInstance(this.componentRegistryProvider, this.cacheProvider, this.identityManagerProvider, this.logProvider);
    }
}
