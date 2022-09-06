package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DigestBreakUpStage_MembersInjector implements MembersInjector<DigestBreakUpStage> {
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;

    public DigestBreakUpStage_MembersInjector(Provider<Logger> provider, Provider<Metrics> provider2) {
        this.loggerProvider = provider;
        this.metricsProvider = provider2;
    }

    public static MembersInjector<DigestBreakUpStage> create(Provider<Logger> provider, Provider<Metrics> provider2) {
        return new DigestBreakUpStage_MembersInjector(provider, provider2);
    }

    public static void injectLogger(DigestBreakUpStage digestBreakUpStage, Logger logger) {
        digestBreakUpStage.logger = logger;
    }

    public static void injectMetrics(DigestBreakUpStage digestBreakUpStage, Metrics metrics) {
        digestBreakUpStage.metrics = metrics;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DigestBreakUpStage digestBreakUpStage) {
        injectLogger(digestBreakUpStage, this.loggerProvider.mo10268get());
        injectMetrics(digestBreakUpStage, this.metricsProvider.mo10268get());
    }
}
