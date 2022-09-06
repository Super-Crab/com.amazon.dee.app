package com.amazon.alexa.voice.nowplaying;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class NowPlayingModule_ProvideDefaultNowPlayingCardManagerFactory implements Factory<DefaultNowPlayingCardManager> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<Context> contextProvider;
    private final Provider<VoiceNowPlayingEventBusModule> voiceNowPlayingEventBusModuleProvider;

    public NowPlayingModule_ProvideDefaultNowPlayingCardManagerFactory(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<VoiceNowPlayingEventBusModule> provider3) {
        this.contextProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
        this.voiceNowPlayingEventBusModuleProvider = provider3;
    }

    public static NowPlayingModule_ProvideDefaultNowPlayingCardManagerFactory create(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<VoiceNowPlayingEventBusModule> provider3) {
        return new NowPlayingModule_ProvideDefaultNowPlayingCardManagerFactory(provider, provider2, provider3);
    }

    public static DefaultNowPlayingCardManager provideInstance(Provider<Context> provider, Provider<AlexaServicesConnection> provider2, Provider<VoiceNowPlayingEventBusModule> provider3) {
        return proxyProvideDefaultNowPlayingCardManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static DefaultNowPlayingCardManager proxyProvideDefaultNowPlayingCardManager(Context context, AlexaServicesConnection alexaServicesConnection, VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        return (DefaultNowPlayingCardManager) Preconditions.checkNotNull(NowPlayingModule.provideDefaultNowPlayingCardManager(context, alexaServicesConnection, voiceNowPlayingEventBusModule), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultNowPlayingCardManager mo10268get() {
        return provideInstance(this.contextProvider, this.alexaServicesConnectionProvider, this.voiceNowPlayingEventBusModuleProvider);
    }
}
