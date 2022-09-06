package com.amazon.deecomms.messaging.provider;

import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingProviderWrapper_MembersInjector implements MembersInjector<MessagingProviderWrapper> {
    private final Provider<TranscriptLatencyMetricHelper> mTranscriptLatencyMetricHelperProvider;

    public MessagingProviderWrapper_MembersInjector(Provider<TranscriptLatencyMetricHelper> provider) {
        this.mTranscriptLatencyMetricHelperProvider = provider;
    }

    public static MembersInjector<MessagingProviderWrapper> create(Provider<TranscriptLatencyMetricHelper> provider) {
        return new MessagingProviderWrapper_MembersInjector(provider);
    }

    public static void injectMTranscriptLatencyMetricHelper(MessagingProviderWrapper messagingProviderWrapper, TranscriptLatencyMetricHelper transcriptLatencyMetricHelper) {
        messagingProviderWrapper.mTranscriptLatencyMetricHelper = transcriptLatencyMetricHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessagingProviderWrapper messagingProviderWrapper) {
        messagingProviderWrapper.mTranscriptLatencyMetricHelper = this.mTranscriptLatencyMetricHelperProvider.mo10268get();
    }
}
