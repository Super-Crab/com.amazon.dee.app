package com.amazon.deecomms.core;

import android.media.AudioManager;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.media.audio.AudioInputController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AudioModule_ProvidesMicStateControllerFactory implements Factory<AudioInputController> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<CallContext> callContextProvider;
    private final AudioModule module;

    public AudioModule_ProvidesMicStateControllerFactory(AudioModule audioModule, Provider<AudioManager> provider, Provider<CallContext> provider2) {
        this.module = audioModule;
        this.audioManagerProvider = provider;
        this.callContextProvider = provider2;
    }

    public static AudioModule_ProvidesMicStateControllerFactory create(AudioModule audioModule, Provider<AudioManager> provider, Provider<CallContext> provider2) {
        return new AudioModule_ProvidesMicStateControllerFactory(audioModule, provider, provider2);
    }

    public static AudioInputController provideInstance(AudioModule audioModule, Provider<AudioManager> provider, Provider<CallContext> provider2) {
        return (AudioInputController) Preconditions.checkNotNull(audioModule.providesMicStateController(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioInputController proxyProvidesMicStateController(AudioModule audioModule, AudioManager audioManager, CallContext callContext) {
        return (AudioInputController) Preconditions.checkNotNull(audioModule.providesMicStateController(audioManager, callContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioInputController mo10268get() {
        return provideInstance(this.module, this.audioManagerProvider, this.callContextProvider);
    }
}
