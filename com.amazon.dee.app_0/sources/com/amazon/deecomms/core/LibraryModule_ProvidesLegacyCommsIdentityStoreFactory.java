package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.identity.CommsIdentityStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesLegacyCommsIdentityStoreFactory implements Factory<CommsIdentityStore> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesLegacyCommsIdentityStoreFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesLegacyCommsIdentityStoreFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesLegacyCommsIdentityStoreFactory(libraryModule, provider);
    }

    public static CommsIdentityStore provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (CommsIdentityStore) Preconditions.checkNotNull(libraryModule.providesLegacyCommsIdentityStore(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsIdentityStore proxyProvidesLegacyCommsIdentityStore(LibraryModule libraryModule, Context context) {
        return (CommsIdentityStore) Preconditions.checkNotNull(libraryModule.providesLegacyCommsIdentityStore(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsIdentityStore mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
