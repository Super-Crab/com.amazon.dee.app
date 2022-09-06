package com.amazon.alexa.voice.metrics;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceMetricsBroadcastReceiver_MembersInjector implements MembersInjector<VoiceMetricsBroadcastReceiver> {
    private final Provider<VoxMetricEventProcessingService> eventProcessingServiceProvider;

    public VoiceMetricsBroadcastReceiver_MembersInjector(Provider<VoxMetricEventProcessingService> provider) {
        this.eventProcessingServiceProvider = provider;
    }

    public static MembersInjector<VoiceMetricsBroadcastReceiver> create(Provider<VoxMetricEventProcessingService> provider) {
        return new VoiceMetricsBroadcastReceiver_MembersInjector(provider);
    }

    public static void injectEventProcessingService(VoiceMetricsBroadcastReceiver voiceMetricsBroadcastReceiver, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        voiceMetricsBroadcastReceiver.eventProcessingService = voxMetricEventProcessingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceMetricsBroadcastReceiver voiceMetricsBroadcastReceiver) {
        injectEventProcessingService(voiceMetricsBroadcastReceiver, this.eventProcessingServiceProvider.mo10268get());
    }
}
