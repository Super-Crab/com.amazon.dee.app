package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.controller.CallHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCallHelperFactory implements Factory<CallHelper> {
    private final LibraryModule module;

    public LibraryModule_ProvidesCallHelperFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesCallHelperFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesCallHelperFactory(libraryModule);
    }

    public static CallHelper provideInstance(LibraryModule libraryModule) {
        return (CallHelper) Preconditions.checkNotNull(libraryModule.providesCallHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallHelper proxyProvidesCallHelper(LibraryModule libraryModule) {
        return (CallHelper) Preconditions.checkNotNull(libraryModule.providesCallHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallHelper mo10268get() {
        return provideInstance(this.module);
    }
}
