package com.amazon.deecomms.core;

import com.amazon.deecomms.identity.MigrationCommsIdentityStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesMigrationCommsIdentityStoreFactory implements Factory<MigrationCommsIdentityStore> {
    private final LibraryModule module;

    public LibraryModule_ProvidesMigrationCommsIdentityStoreFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesMigrationCommsIdentityStoreFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesMigrationCommsIdentityStoreFactory(libraryModule);
    }

    public static MigrationCommsIdentityStore provideInstance(LibraryModule libraryModule) {
        return (MigrationCommsIdentityStore) Preconditions.checkNotNull(libraryModule.providesMigrationCommsIdentityStore(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static MigrationCommsIdentityStore proxyProvidesMigrationCommsIdentityStore(LibraryModule libraryModule) {
        return (MigrationCommsIdentityStore) Preconditions.checkNotNull(libraryModule.providesMigrationCommsIdentityStore(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MigrationCommsIdentityStore mo10268get() {
        return provideInstance(this.module);
    }
}
