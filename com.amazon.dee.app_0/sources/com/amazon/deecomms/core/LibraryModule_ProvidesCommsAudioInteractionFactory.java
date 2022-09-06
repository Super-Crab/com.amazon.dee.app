package com.amazon.deecomms.core;

import com.amazon.deecomms.alexa.CommsAudioInteraction;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsAudioInteractionFactory implements Factory<CommsAudioInteraction> {
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsAudioInteractionFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesCommsAudioInteractionFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesCommsAudioInteractionFactory(libraryModule);
    }

    public static CommsAudioInteraction provideInstance(LibraryModule libraryModule) {
        return (CommsAudioInteraction) Preconditions.checkNotNull(libraryModule.providesCommsAudioInteraction(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsAudioInteraction proxyProvidesCommsAudioInteraction(LibraryModule libraryModule) {
        return (CommsAudioInteraction) Preconditions.checkNotNull(libraryModule.providesCommsAudioInteraction(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsAudioInteraction mo10268get() {
        return provideInstance(this.module);
    }
}
