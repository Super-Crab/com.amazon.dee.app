package com.amazon.deecomms.core;

import android.content.Context;
import android.media.AudioManager;
import com.amazon.deecomms.media.audio.AudioPlayer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAudioPlayerFactory implements Factory<AudioPlayer> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesAudioPlayerFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<AudioManager> provider2) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.audioManagerProvider = provider2;
    }

    public static LibraryModule_ProvidesAudioPlayerFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<AudioManager> provider2) {
        return new LibraryModule_ProvidesAudioPlayerFactory(libraryModule, provider, provider2);
    }

    public static AudioPlayer provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<AudioManager> provider2) {
        return (AudioPlayer) Preconditions.checkNotNull(libraryModule.providesAudioPlayer(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioPlayer proxyProvidesAudioPlayer(LibraryModule libraryModule, Context context, AudioManager audioManager) {
        return (AudioPlayer) Preconditions.checkNotNull(libraryModule.providesAudioPlayer(context, audioManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioPlayer mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.audioManagerProvider);
    }
}
