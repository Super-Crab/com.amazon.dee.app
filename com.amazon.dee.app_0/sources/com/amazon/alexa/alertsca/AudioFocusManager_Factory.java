package com.amazon.alexa.alertsca;

import android.media.AudioManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AudioFocusManager_Factory implements Factory<AudioFocusManager> {
    private final Provider<AudioManager> audioManagerProvider;

    public AudioFocusManager_Factory(Provider<AudioManager> provider) {
        this.audioManagerProvider = provider;
    }

    public static AudioFocusManager_Factory create(Provider<AudioManager> provider) {
        return new AudioFocusManager_Factory(provider);
    }

    public static AudioFocusManager newAudioFocusManager(AudioManager audioManager) {
        return new AudioFocusManager(audioManager);
    }

    public static AudioFocusManager provideInstance(Provider<AudioManager> provider) {
        return new AudioFocusManager(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioFocusManager mo10268get() {
        return provideInstance(this.audioManagerProvider);
    }
}
