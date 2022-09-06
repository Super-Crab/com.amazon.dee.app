package com.amazon.deecomms.messaging.sync;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TranscriptionUpdateService_MembersInjector implements MembersInjector<TranscriptionUpdateService> {
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<TranscriptLatencyMetricHelper> mTranscriptLatencyMetricHelperProvider;

    public TranscriptionUpdateService_MembersInjector(Provider<TranscriptLatencyMetricHelper> provider, Provider<CommsIdentityManager> provider2) {
        this.mTranscriptLatencyMetricHelperProvider = provider;
        this.commsIdentityManagerProvider = provider2;
    }

    public static MembersInjector<TranscriptionUpdateService> create(Provider<TranscriptLatencyMetricHelper> provider, Provider<CommsIdentityManager> provider2) {
        return new TranscriptionUpdateService_MembersInjector(provider, provider2);
    }

    public static void injectCommsIdentityManager(TranscriptionUpdateService transcriptionUpdateService, CommsIdentityManager commsIdentityManager) {
        transcriptionUpdateService.commsIdentityManager = commsIdentityManager;
    }

    public static void injectMTranscriptLatencyMetricHelper(TranscriptionUpdateService transcriptionUpdateService, TranscriptLatencyMetricHelper transcriptLatencyMetricHelper) {
        transcriptionUpdateService.mTranscriptLatencyMetricHelper = transcriptLatencyMetricHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TranscriptionUpdateService transcriptionUpdateService) {
        transcriptionUpdateService.mTranscriptLatencyMetricHelper = this.mTranscriptLatencyMetricHelperProvider.mo10268get();
        transcriptionUpdateService.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
    }
}
