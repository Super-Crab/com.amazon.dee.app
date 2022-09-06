package com.amazon.deecomms.core;

import android.media.AudioManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.media.audio.AudioOutputController;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AudioModule_ProvidesAudioStateControllerFactory implements Factory<AudioOutputController> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<AudioStateObservable> audioStateObservableProvider;
    private final AudioModule module;
    private final Provider<SipClientState> sipClientStateProvider;

    public AudioModule_ProvidesAudioStateControllerFactory(AudioModule audioModule, Provider<AudioStateObservable> provider, Provider<AudioManager> provider2, Provider<SipClientState> provider3) {
        this.module = audioModule;
        this.audioStateObservableProvider = provider;
        this.audioManagerProvider = provider2;
        this.sipClientStateProvider = provider3;
    }

    public static AudioModule_ProvidesAudioStateControllerFactory create(AudioModule audioModule, Provider<AudioStateObservable> provider, Provider<AudioManager> provider2, Provider<SipClientState> provider3) {
        return new AudioModule_ProvidesAudioStateControllerFactory(audioModule, provider, provider2, provider3);
    }

    public static AudioOutputController provideInstance(AudioModule audioModule, Provider<AudioStateObservable> provider, Provider<AudioManager> provider2, Provider<SipClientState> provider3) {
        return (AudioOutputController) Preconditions.checkNotNull(audioModule.providesAudioStateController(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioOutputController proxyProvidesAudioStateController(AudioModule audioModule, AudioStateObservable audioStateObservable, AudioManager audioManager, SipClientState sipClientState) {
        return (AudioOutputController) Preconditions.checkNotNull(audioModule.providesAudioStateController(audioStateObservable, audioManager, sipClientState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioOutputController mo10268get() {
        return provideInstance(this.module, this.audioStateObservableProvider, this.audioManagerProvider, this.sipClientStateProvider);
    }
}
