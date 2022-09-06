package com.amazon.deecomms.core;

import com.amazon.deecomms.common.controller.CommsDisposableManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsDisposableManagerFactory implements Factory<CommsDisposableManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsDisposableManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesCommsDisposableManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesCommsDisposableManagerFactory(libraryModule);
    }

    public static CommsDisposableManager provideInstance(LibraryModule libraryModule) {
        return (CommsDisposableManager) Preconditions.checkNotNull(libraryModule.providesCommsDisposableManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsDisposableManager proxyProvidesCommsDisposableManager(LibraryModule libraryModule) {
        return (CommsDisposableManager) Preconditions.checkNotNull(libraryModule.providesCommsDisposableManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDisposableManager mo10268get() {
        return provideInstance(this.module);
    }
}
