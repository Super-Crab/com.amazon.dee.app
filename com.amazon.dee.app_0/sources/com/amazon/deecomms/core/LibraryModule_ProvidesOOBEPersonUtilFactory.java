package com.amazon.deecomms.core;

import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesOOBEPersonUtilFactory implements Factory<OOBEPersonManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesOOBEPersonUtilFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesOOBEPersonUtilFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesOOBEPersonUtilFactory(libraryModule);
    }

    public static OOBEPersonManager provideInstance(LibraryModule libraryModule) {
        return (OOBEPersonManager) Preconditions.checkNotNull(libraryModule.providesOOBEPersonUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static OOBEPersonManager proxyProvidesOOBEPersonUtil(LibraryModule libraryModule) {
        return (OOBEPersonManager) Preconditions.checkNotNull(libraryModule.providesOOBEPersonUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OOBEPersonManager mo10268get() {
        return provideInstance(this.module);
    }
}
