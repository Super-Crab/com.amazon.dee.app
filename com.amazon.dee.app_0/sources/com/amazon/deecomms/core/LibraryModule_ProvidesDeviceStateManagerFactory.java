package com.amazon.deecomms.core;

import com.amazon.deecomms.ndt.state.DeviceStateManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesDeviceStateManagerFactory implements Factory<DeviceStateManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesDeviceStateManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesDeviceStateManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesDeviceStateManagerFactory(libraryModule);
    }

    public static DeviceStateManager provideInstance(LibraryModule libraryModule) {
        return (DeviceStateManager) Preconditions.checkNotNull(libraryModule.providesDeviceStateManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceStateManager proxyProvidesDeviceStateManager(LibraryModule libraryModule) {
        return (DeviceStateManager) Preconditions.checkNotNull(libraryModule.providesDeviceStateManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceStateManager mo10268get() {
        return provideInstance(this.module);
    }
}
