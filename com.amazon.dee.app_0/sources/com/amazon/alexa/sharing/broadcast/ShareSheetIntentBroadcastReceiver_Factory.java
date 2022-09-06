package com.amazon.alexa.sharing.broadcast;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ShareSheetIntentBroadcastReceiver_Factory implements Factory<ShareSheetIntentBroadcastReceiver> {
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;

    public ShareSheetIntentBroadcastReceiver_Factory(Provider<AlexaCommsCoreMetricsService> provider) {
        this.metricsServiceLazyProvider = provider;
    }

    public static ShareSheetIntentBroadcastReceiver_Factory create(Provider<AlexaCommsCoreMetricsService> provider) {
        return new ShareSheetIntentBroadcastReceiver_Factory(provider);
    }

    public static ShareSheetIntentBroadcastReceiver newShareSheetIntentBroadcastReceiver() {
        return new ShareSheetIntentBroadcastReceiver();
    }

    public static ShareSheetIntentBroadcastReceiver provideInstance(Provider<AlexaCommsCoreMetricsService> provider) {
        ShareSheetIntentBroadcastReceiver shareSheetIntentBroadcastReceiver = new ShareSheetIntentBroadcastReceiver();
        ShareSheetIntentBroadcastReceiver_MembersInjector.injectMetricsServiceLazy(shareSheetIntentBroadcastReceiver, DoubleCheck.lazy(provider));
        return shareSheetIntentBroadcastReceiver;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ShareSheetIntentBroadcastReceiver mo10268get() {
        return provideInstance(this.metricsServiceLazyProvider);
    }
}
