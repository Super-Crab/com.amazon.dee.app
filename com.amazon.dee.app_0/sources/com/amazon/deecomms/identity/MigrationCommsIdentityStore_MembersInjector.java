package com.amazon.deecomms.identity;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MigrationCommsIdentityStore_MembersInjector implements MembersInjector<MigrationCommsIdentityStore> {
    private final Provider<Context> contextProvider;
    private final Provider<CommsIdentityStoreV2Impl> mCommsIdentityStoreV2ImplProvider;
    private final Provider<CommsIdentityStore> mLegacyCommsIdentityStoreProvider;

    public MigrationCommsIdentityStore_MembersInjector(Provider<Context> provider, Provider<CommsIdentityStore> provider2, Provider<CommsIdentityStoreV2Impl> provider3) {
        this.contextProvider = provider;
        this.mLegacyCommsIdentityStoreProvider = provider2;
        this.mCommsIdentityStoreV2ImplProvider = provider3;
    }

    public static MembersInjector<MigrationCommsIdentityStore> create(Provider<Context> provider, Provider<CommsIdentityStore> provider2, Provider<CommsIdentityStoreV2Impl> provider3) {
        return new MigrationCommsIdentityStore_MembersInjector(provider, provider2, provider3);
    }

    public static void injectContext(MigrationCommsIdentityStore migrationCommsIdentityStore, Context context) {
        migrationCommsIdentityStore.context = context;
    }

    public static void injectMCommsIdentityStoreV2Impl(MigrationCommsIdentityStore migrationCommsIdentityStore, CommsIdentityStoreV2Impl commsIdentityStoreV2Impl) {
        migrationCommsIdentityStore.mCommsIdentityStoreV2Impl = commsIdentityStoreV2Impl;
    }

    public static void injectMLegacyCommsIdentityStore(MigrationCommsIdentityStore migrationCommsIdentityStore, CommsIdentityStore commsIdentityStore) {
        migrationCommsIdentityStore.mLegacyCommsIdentityStore = commsIdentityStore;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MigrationCommsIdentityStore migrationCommsIdentityStore) {
        migrationCommsIdentityStore.context = this.contextProvider.mo10268get();
        migrationCommsIdentityStore.mLegacyCommsIdentityStore = this.mLegacyCommsIdentityStoreProvider.mo10268get();
        migrationCommsIdentityStore.mCommsIdentityStoreV2Impl = this.mCommsIdentityStoreV2ImplProvider.mo10268get();
    }
}
