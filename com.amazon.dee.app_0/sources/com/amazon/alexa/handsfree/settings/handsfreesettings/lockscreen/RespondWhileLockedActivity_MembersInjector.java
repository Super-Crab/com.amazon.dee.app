package com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen;

import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class RespondWhileLockedActivity_MembersInjector implements MembersInjector<RespondWhileLockedActivity> {
    private final Provider<MetricsBuilderProvider> mMetricsBuilderProvider;

    public RespondWhileLockedActivity_MembersInjector(Provider<MetricsBuilderProvider> provider) {
        this.mMetricsBuilderProvider = provider;
    }

    public static MembersInjector<RespondWhileLockedActivity> create(Provider<MetricsBuilderProvider> provider) {
        return new RespondWhileLockedActivity_MembersInjector(provider);
    }

    public static void injectMMetricsBuilderProvider(RespondWhileLockedActivity respondWhileLockedActivity, MetricsBuilderProvider metricsBuilderProvider) {
        respondWhileLockedActivity.mMetricsBuilderProvider = metricsBuilderProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RespondWhileLockedActivity respondWhileLockedActivity) {
        injectMMetricsBuilderProvider(respondWhileLockedActivity, this.mMetricsBuilderProvider.mo10268get());
    }
}
