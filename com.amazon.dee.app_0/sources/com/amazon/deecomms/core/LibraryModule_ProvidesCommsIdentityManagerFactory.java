package com.amazon.deecomms.core;

import com.amazon.deecomms.api.CommsIdentityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsIdentityManagerFactory implements Factory<CommsIdentityManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsIdentityManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesCommsIdentityManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesCommsIdentityManagerFactory(libraryModule);
    }

    public static CommsIdentityManager provideInstance(LibraryModule libraryModule) {
        return (CommsIdentityManager) Preconditions.checkNotNull(libraryModule.providesCommsIdentityManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsIdentityManager proxyProvidesCommsIdentityManager(LibraryModule libraryModule) {
        return (CommsIdentityManager) Preconditions.checkNotNull(libraryModule.providesCommsIdentityManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsIdentityManager mo10268get() {
        return provideInstance(this.module);
    }
}
