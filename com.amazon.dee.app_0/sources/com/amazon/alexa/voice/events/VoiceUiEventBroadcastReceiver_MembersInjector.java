package com.amazon.alexa.voice.events;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceUiEventBroadcastReceiver_MembersInjector implements MembersInjector<VoiceUiEventBroadcastReceiver> {
    private final Provider<VoxUiEventProcessingService> eventProcessingServiceProvider;

    public VoiceUiEventBroadcastReceiver_MembersInjector(Provider<VoxUiEventProcessingService> provider) {
        this.eventProcessingServiceProvider = provider;
    }

    public static MembersInjector<VoiceUiEventBroadcastReceiver> create(Provider<VoxUiEventProcessingService> provider) {
        return new VoiceUiEventBroadcastReceiver_MembersInjector(provider);
    }

    public static void injectEventProcessingService(VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver, VoxUiEventProcessingService voxUiEventProcessingService) {
        voiceUiEventBroadcastReceiver.eventProcessingService = voxUiEventProcessingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceUiEventBroadcastReceiver voiceUiEventBroadcastReceiver) {
        injectEventProcessingService(voiceUiEventBroadcastReceiver, this.eventProcessingServiceProvider.mo10268get());
    }
}
