package com.amazon.deecomms.core;

import com.amazon.deecomms.messaging.controller.AudioStateManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAudioStateManagerFactory implements Factory<AudioStateManager> {
    private final LibraryModule module;

    public LibraryModule_ProvidesAudioStateManagerFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesAudioStateManagerFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesAudioStateManagerFactory(libraryModule);
    }

    public static AudioStateManager provideInstance(LibraryModule libraryModule) {
        return (AudioStateManager) Preconditions.checkNotNull(libraryModule.providesAudioStateManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioStateManager proxyProvidesAudioStateManager(LibraryModule libraryModule) {
        return (AudioStateManager) Preconditions.checkNotNull(libraryModule.providesAudioStateManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioStateManager mo10268get() {
        return provideInstance(this.module);
    }
}
