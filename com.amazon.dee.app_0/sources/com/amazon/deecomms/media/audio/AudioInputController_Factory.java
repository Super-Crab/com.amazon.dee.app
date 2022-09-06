package com.amazon.deecomms.media.audio;

import android.media.AudioManager;
import com.amazon.deecomms.calling.model.CallContext;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AudioInputController_Factory implements Factory<AudioInputController> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<CallContext> callContextProvider;

    public AudioInputController_Factory(Provider<AudioManager> provider, Provider<CallContext> provider2) {
        this.audioManagerProvider = provider;
        this.callContextProvider = provider2;
    }

    public static AudioInputController_Factory create(Provider<AudioManager> provider, Provider<CallContext> provider2) {
        return new AudioInputController_Factory(provider, provider2);
    }

    public static AudioInputController newAudioInputController(AudioManager audioManager, CallContext callContext) {
        return new AudioInputController(audioManager, callContext);
    }

    public static AudioInputController provideInstance(Provider<AudioManager> provider, Provider<CallContext> provider2) {
        return new AudioInputController(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioInputController mo10268get() {
        return provideInstance(this.audioManagerProvider, this.callContextProvider);
    }
}
