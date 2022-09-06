package com.amazon.deecomms.core;

import android.media.AudioManager;
import com.amazon.deecomms.media.audio.AudioRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesAudioRecorderFactory implements Factory<AudioRecorder> {
    private final Provider<AudioManager> audioManagerProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesAudioRecorderFactory(LibraryModule libraryModule, Provider<AudioManager> provider) {
        this.module = libraryModule;
        this.audioManagerProvider = provider;
    }

    public static LibraryModule_ProvidesAudioRecorderFactory create(LibraryModule libraryModule, Provider<AudioManager> provider) {
        return new LibraryModule_ProvidesAudioRecorderFactory(libraryModule, provider);
    }

    public static AudioRecorder provideInstance(LibraryModule libraryModule, Provider<AudioManager> provider) {
        return (AudioRecorder) Preconditions.checkNotNull(libraryModule.providesAudioRecorder(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioRecorder proxyProvidesAudioRecorder(LibraryModule libraryModule, AudioManager audioManager) {
        return (AudioRecorder) Preconditions.checkNotNull(libraryModule.providesAudioRecorder(audioManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioRecorder mo10268get() {
        return provideInstance(this.module, this.audioManagerProvider);
    }
}
