package com.amazon.deecomms.core;

import com.amazon.deecomms.media.audio.AudioStateObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class AudioModule_ProvidesAudioStateObservableFactory implements Factory<AudioStateObservable> {
    private final AudioModule module;

    public AudioModule_ProvidesAudioStateObservableFactory(AudioModule audioModule) {
        this.module = audioModule;
    }

    public static AudioModule_ProvidesAudioStateObservableFactory create(AudioModule audioModule) {
        return new AudioModule_ProvidesAudioStateObservableFactory(audioModule);
    }

    public static AudioStateObservable provideInstance(AudioModule audioModule) {
        return (AudioStateObservable) Preconditions.checkNotNull(audioModule.providesAudioStateObservable(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioStateObservable proxyProvidesAudioStateObservable(AudioModule audioModule) {
        return (AudioStateObservable) Preconditions.checkNotNull(audioModule.providesAudioStateObservable(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioStateObservable mo10268get() {
        return provideInstance(this.module);
    }
}
