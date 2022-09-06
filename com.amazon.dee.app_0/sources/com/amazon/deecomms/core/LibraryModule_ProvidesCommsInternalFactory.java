package com.amazon.deecomms.core;

import com.amazon.deecomms.common.CommsInternal;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsInternalFactory implements Factory<CommsInternal> {
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsInternalFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesCommsInternalFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesCommsInternalFactory(libraryModule);
    }

    public static CommsInternal provideInstance(LibraryModule libraryModule) {
        return (CommsInternal) Preconditions.checkNotNull(libraryModule.providesCommsInternal(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsInternal proxyProvidesCommsInternal(LibraryModule libraryModule) {
        return (CommsInternal) Preconditions.checkNotNull(libraryModule.providesCommsInternal(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsInternal mo10268get() {
        return provideInstance(this.module);
    }
}
