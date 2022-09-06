package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class NowPlayingModule_ProvideAudioPlaybackControllerFactory implements Factory<AudioPlaybackController> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;

    public NowPlayingModule_ProvideAudioPlaybackControllerFactory(Provider<AlexaServicesConnection> provider) {
        this.alexaServicesConnectionProvider = provider;
    }

    public static NowPlayingModule_ProvideAudioPlaybackControllerFactory create(Provider<AlexaServicesConnection> provider) {
        return new NowPlayingModule_ProvideAudioPlaybackControllerFactory(provider);
    }

    public static AudioPlaybackController provideInstance(Provider<AlexaServicesConnection> provider) {
        return proxyProvideAudioPlaybackController(provider.mo10268get());
    }

    public static AudioPlaybackController proxyProvideAudioPlaybackController(AlexaServicesConnection alexaServicesConnection) {
        return (AudioPlaybackController) Preconditions.checkNotNull(NowPlayingModule.provideAudioPlaybackController(alexaServicesConnection), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioPlaybackController mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider);
    }
}
