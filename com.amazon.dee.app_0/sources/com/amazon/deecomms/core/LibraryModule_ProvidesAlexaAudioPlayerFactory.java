package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAlexaAudioPlayerFactory implements Factory<AlexaAudioPlayer> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesAlexaAudioPlayerFactory(LibraryModule libraryModule, Provider<Context> provider) {
        this.module = libraryModule;
        this.contextProvider = provider;
    }

    public static LibraryModule_ProvidesAlexaAudioPlayerFactory create(LibraryModule libraryModule, Provider<Context> provider) {
        return new LibraryModule_ProvidesAlexaAudioPlayerFactory(libraryModule, provider);
    }

    public static AlexaAudioPlayer provideInstance(LibraryModule libraryModule, Provider<Context> provider) {
        return (AlexaAudioPlayer) Preconditions.checkNotNull(libraryModule.providesAlexaAudioPlayer(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AlexaAudioPlayer proxyProvidesAlexaAudioPlayer(LibraryModule libraryModule, Context context) {
        return (AlexaAudioPlayer) Preconditions.checkNotNull(libraryModule.providesAlexaAudioPlayer(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaAudioPlayer mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
