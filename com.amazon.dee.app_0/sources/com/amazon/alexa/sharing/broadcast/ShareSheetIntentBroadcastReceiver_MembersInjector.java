package com.amazon.alexa.sharing.broadcast;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ShareSheetIntentBroadcastReceiver_MembersInjector implements MembersInjector<ShareSheetIntentBroadcastReceiver> {
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;

    public ShareSheetIntentBroadcastReceiver_MembersInjector(Provider<AlexaCommsCoreMetricsService> provider) {
        this.metricsServiceLazyProvider = provider;
    }

    public static MembersInjector<ShareSheetIntentBroadcastReceiver> create(Provider<AlexaCommsCoreMetricsService> provider) {
        return new ShareSheetIntentBroadcastReceiver_MembersInjector(provider);
    }

    public static void injectMetricsServiceLazy(ShareSheetIntentBroadcastReceiver shareSheetIntentBroadcastReceiver, Lazy<AlexaCommsCoreMetricsService> lazy) {
        shareSheetIntentBroadcastReceiver.metricsServiceLazy = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ShareSheetIntentBroadcastReceiver shareSheetIntentBroadcastReceiver) {
        injectMetricsServiceLazy(shareSheetIntentBroadcastReceiver, DoubleCheck.lazy(this.metricsServiceLazyProvider));
    }
}
