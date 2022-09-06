package com.amazon.alexa.voice.nowplaying.bridge;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceNowPlayingEventBusModule_MembersInjector implements MembersInjector<VoiceNowPlayingEventBusModule> {
    private final Provider<EventBus> eventBusProvider;

    public VoiceNowPlayingEventBusModule_MembersInjector(Provider<EventBus> provider) {
        this.eventBusProvider = provider;
    }

    public static MembersInjector<VoiceNowPlayingEventBusModule> create(Provider<EventBus> provider) {
        return new VoiceNowPlayingEventBusModule_MembersInjector(provider);
    }

    public static void injectEventBus(VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule, EventBus eventBus) {
        voiceNowPlayingEventBusModule.eventBus = eventBus;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        injectEventBus(voiceNowPlayingEventBusModule, this.eventBusProvider.mo10268get());
    }
}
