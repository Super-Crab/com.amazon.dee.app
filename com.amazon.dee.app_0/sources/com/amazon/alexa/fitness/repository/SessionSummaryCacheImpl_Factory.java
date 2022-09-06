package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SessionSummaryCacheImpl_Factory implements Factory<SessionSummaryCacheImpl> {
    private final Provider<CacheProvider> cacheProvider;
    private final Provider<IdentityManager> identityManagerProvider;
    private final Provider<ILog> logProvider;

    public SessionSummaryCacheImpl_Factory(Provider<CacheProvider> provider, Provider<IdentityManager> provider2, Provider<ILog> provider3) {
        this.cacheProvider = provider;
        this.identityManagerProvider = provider2;
        this.logProvider = provider3;
    }

    public static SessionSummaryCacheImpl_Factory create(Provider<CacheProvider> provider, Provider<IdentityManager> provider2, Provider<ILog> provider3) {
        return new SessionSummaryCacheImpl_Factory(provider, provider2, provider3);
    }

    public static SessionSummaryCacheImpl newSessionSummaryCacheImpl(CacheProvider cacheProvider, IdentityManager identityManager, ILog iLog) {
        return new SessionSummaryCacheImpl(cacheProvider, identityManager, iLog);
    }

    public static SessionSummaryCacheImpl provideInstance(Provider<CacheProvider> provider, Provider<IdentityManager> provider2, Provider<ILog> provider3) {
        return new SessionSummaryCacheImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SessionSummaryCacheImpl mo10268get() {
        return provideInstance(this.cacheProvider, this.identityManagerProvider, this.logProvider);
    }
}
