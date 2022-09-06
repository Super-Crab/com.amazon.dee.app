package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class SessionClientCacheService_MembersInjector implements MembersInjector<SessionClientCacheService> {
    private final Provider<SessionClientCache> sessionClientCacheProvider;

    public SessionClientCacheService_MembersInjector(Provider<SessionClientCache> provider) {
        this.sessionClientCacheProvider = provider;
    }

    public static MembersInjector<SessionClientCacheService> create(Provider<SessionClientCache> provider) {
        return new SessionClientCacheService_MembersInjector(provider);
    }

    public static void injectSessionClientCache(SessionClientCacheService sessionClientCacheService, SessionClientCache sessionClientCache) {
        sessionClientCacheService.sessionClientCache = sessionClientCache;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SessionClientCacheService sessionClientCacheService) {
        injectSessionClientCache(sessionClientCacheService, this.sessionClientCacheProvider.mo10268get());
    }
}
