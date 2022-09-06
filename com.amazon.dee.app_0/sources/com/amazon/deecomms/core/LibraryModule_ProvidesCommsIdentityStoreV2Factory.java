package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.identity.CommsIdentityStoreV2Impl;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsIdentityStoreV2Factory implements Factory<CommsIdentityStoreV2Impl> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsIdentityStoreV2Factory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesCommsIdentityStoreV2Factory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesCommsIdentityStoreV2Factory(libraryModule, provider);
    }

    public static CommsIdentityStoreV2Impl provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (CommsIdentityStoreV2Impl) Preconditions.checkNotNull(libraryModule.providesCommsIdentityStoreV2(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsIdentityStoreV2Impl proxyProvidesCommsIdentityStoreV2(LibraryModule libraryModule, Context context) {
        return (CommsIdentityStoreV2Impl) Preconditions.checkNotNull(libraryModule.providesCommsIdentityStoreV2(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsIdentityStoreV2Impl mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
