package com.amazon.deecomms.core;

import com.amazon.deecomms.util.IBuildVersionProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesBuildVersionFactory implements Factory<IBuildVersionProvider> {
    private final LibraryModule module;

    public LibraryModule_ProvidesBuildVersionFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesBuildVersionFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesBuildVersionFactory(libraryModule);
    }

    public static IBuildVersionProvider provideInstance(LibraryModule libraryModule) {
        return (IBuildVersionProvider) Preconditions.checkNotNull(libraryModule.providesBuildVersion(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static IBuildVersionProvider proxyProvidesBuildVersion(LibraryModule libraryModule) {
        return (IBuildVersionProvider) Preconditions.checkNotNull(libraryModule.providesBuildVersion(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IBuildVersionProvider mo10268get() {
        return provideInstance(this.module);
    }
}
