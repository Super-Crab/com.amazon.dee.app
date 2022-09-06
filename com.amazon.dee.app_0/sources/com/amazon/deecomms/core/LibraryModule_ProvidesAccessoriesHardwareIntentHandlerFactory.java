package com.amazon.deecomms.core;

import com.amazon.deecomms.accessories.AccessoriesHardwareIntentHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAccessoriesHardwareIntentHandlerFactory implements Factory<AccessoriesHardwareIntentHandler> {
    private final LibraryModule module;

    public LibraryModule_ProvidesAccessoriesHardwareIntentHandlerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesAccessoriesHardwareIntentHandlerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesAccessoriesHardwareIntentHandlerFactory(libraryModule);
    }

    public static AccessoriesHardwareIntentHandler provideInstance(LibraryModule libraryModule) {
        return (AccessoriesHardwareIntentHandler) Preconditions.checkNotNull(libraryModule.providesAccessoriesHardwareIntentHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AccessoriesHardwareIntentHandler proxyProvidesAccessoriesHardwareIntentHandler(LibraryModule libraryModule) {
        return (AccessoriesHardwareIntentHandler) Preconditions.checkNotNull(libraryModule.providesAccessoriesHardwareIntentHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccessoriesHardwareIntentHandler mo10268get() {
        return provideInstance(this.module);
    }
}
