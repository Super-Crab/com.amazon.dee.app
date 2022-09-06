package com.amazon.tarazed.dagger.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideContextFactory implements Factory<Context> {
    private final LibraryModule module;

    public LibraryModule_ProvideContextFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvideContextFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvideContextFactory(libraryModule);
    }

    public static Context provideInstance(LibraryModule libraryModule) {
        return proxyProvideContext(libraryModule);
    }

    public static Context proxyProvideContext(LibraryModule libraryModule) {
        return (Context) Preconditions.checkNotNull(libraryModule.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
