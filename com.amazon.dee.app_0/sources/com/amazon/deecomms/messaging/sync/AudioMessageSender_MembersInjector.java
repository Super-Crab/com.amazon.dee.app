package com.amazon.deecomms.messaging.sync;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AudioMessageSender_MembersInjector implements MembersInjector<AudioMessageSender> {
    private final Provider<AudioContentManager> audioContentManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<TranscriptLatencyMetricHelper> mTranscriptLatencyMetricHelperProvider;

    public AudioMessageSender_MembersInjector(Provider<TranscriptLatencyMetricHelper> provider, Provider<AudioContentManager> provider2, Provider<CommsIdentityManager> provider3) {
        this.mTranscriptLatencyMetricHelperProvider = provider;
        this.audioContentManagerProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
    }

    public static MembersInjector<AudioMessageSender> create(Provider<TranscriptLatencyMetricHelper> provider, Provider<AudioContentManager> provider2, Provider<CommsIdentityManager> provider3) {
        return new AudioMessageSender_MembersInjector(provider, provider2, provider3);
    }

    public static void injectAudioContentManager(AudioMessageSender audioMessageSender, AudioContentManager audioContentManager) {
        audioMessageSender.audioContentManager = audioContentManager;
    }

    public static void injectCommsIdentityManager(AudioMessageSender audioMessageSender, CommsIdentityManager commsIdentityManager) {
        audioMessageSender.commsIdentityManager = commsIdentityManager;
    }

    public static void injectMTranscriptLatencyMetricHelper(AudioMessageSender audioMessageSender, TranscriptLatencyMetricHelper transcriptLatencyMetricHelper) {
        audioMessageSender.mTranscriptLatencyMetricHelper = transcriptLatencyMetricHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AudioMessageSender audioMessageSender) {
        audioMessageSender.mTranscriptLatencyMetricHelper = this.mTranscriptLatencyMetricHelperProvider.mo10268get();
        audioMessageSender.audioContentManager = this.audioContentManagerProvider.mo10268get();
        audioMessageSender.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
    }
}
