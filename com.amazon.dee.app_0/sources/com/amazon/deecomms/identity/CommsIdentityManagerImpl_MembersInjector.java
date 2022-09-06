package com.amazon.deecomms.identity;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsIdentityManagerImpl_MembersInjector implements MembersInjector<CommsIdentityManagerImpl> {
    private final Provider<IdentityService> identityServiceLazyProvider;
    private final Provider<MigrationCommsIdentityStore> migrationCommsIdentityStoreProvider;

    public CommsIdentityManagerImpl_MembersInjector(Provider<MigrationCommsIdentityStore> provider, Provider<IdentityService> provider2) {
        this.migrationCommsIdentityStoreProvider = provider;
        this.identityServiceLazyProvider = provider2;
    }

    public static MembersInjector<CommsIdentityManagerImpl> create(Provider<MigrationCommsIdentityStore> provider, Provider<IdentityService> provider2) {
        return new CommsIdentityManagerImpl_MembersInjector(provider, provider2);
    }

    public static void injectIdentityServiceLazy(CommsIdentityManagerImpl commsIdentityManagerImpl, Lazy<IdentityService> lazy) {
        commsIdentityManagerImpl.identityServiceLazy = lazy;
    }

    public static void injectMigrationCommsIdentityStore(CommsIdentityManagerImpl commsIdentityManagerImpl, MigrationCommsIdentityStore migrationCommsIdentityStore) {
        commsIdentityManagerImpl.migrationCommsIdentityStore = migrationCommsIdentityStore;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsIdentityManagerImpl commsIdentityManagerImpl) {
        commsIdentityManagerImpl.migrationCommsIdentityStore = this.migrationCommsIdentityStoreProvider.mo10268get();
        commsIdentityManagerImpl.identityServiceLazy = DoubleCheck.lazy(this.identityServiceLazyProvider);
    }
}
