package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class NowPlayingModule_ProvideVoiceNowPlayingBridgeFactory implements Factory<VoiceNowPlayingEventBusModule> {
    private final Provider<AudioPlaybackController> audioPlaybackControllerProvider;

    public NowPlayingModule_ProvideVoiceNowPlayingBridgeFactory(Provider<AudioPlaybackController> provider) {
        this.audioPlaybackControllerProvider = provider;
    }

    public static NowPlayingModule_ProvideVoiceNowPlayingBridgeFactory create(Provider<AudioPlaybackController> provider) {
        return new NowPlayingModule_ProvideVoiceNowPlayingBridgeFactory(provider);
    }

    public static VoiceNowPlayingEventBusModule provideInstance(Provider<AudioPlaybackController> provider) {
        return proxyProvideVoiceNowPlayingBridge(provider.mo10268get());
    }

    public static VoiceNowPlayingEventBusModule proxyProvideVoiceNowPlayingBridge(AudioPlaybackController audioPlaybackController) {
        return (VoiceNowPlayingEventBusModule) Preconditions.checkNotNull(NowPlayingModule.provideVoiceNowPlayingBridge(audioPlaybackController), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceNowPlayingEventBusModule mo10268get() {
        return provideInstance(this.audioPlaybackControllerProvider);
    }
}
