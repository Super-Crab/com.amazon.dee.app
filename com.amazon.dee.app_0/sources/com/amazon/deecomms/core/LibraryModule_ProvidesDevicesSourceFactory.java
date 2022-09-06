package com.amazon.deecomms.core;

import com.amazon.deecomms.ndt.DevicesSource;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesDevicesSourceFactory implements Factory<DevicesSource> {
    private final LibraryModule module;

    public LibraryModule_ProvidesDevicesSourceFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesDevicesSourceFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesDevicesSourceFactory(libraryModule);
    }

    public static DevicesSource provideInstance(LibraryModule libraryModule) {
        return (DevicesSource) Preconditions.checkNotNull(libraryModule.providesDevicesSource(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DevicesSource proxyProvidesDevicesSource(LibraryModule libraryModule) {
        return (DevicesSource) Preconditions.checkNotNull(libraryModule.providesDevicesSource(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DevicesSource mo10268get() {
        return provideInstance(this.module);
    }
}
