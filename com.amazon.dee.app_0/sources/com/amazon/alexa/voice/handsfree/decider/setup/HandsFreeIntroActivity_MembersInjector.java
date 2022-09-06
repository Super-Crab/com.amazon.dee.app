package com.amazon.alexa.voice.handsfree.decider.setup;

import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class HandsFreeIntroActivity_MembersInjector implements MembersInjector<HandsFreeIntroActivity> {
    private final Provider<EnrollmentTypeResolver> mEnrollmentTypeResolverLazyProvider;
    private final Provider<MetricsBuilderProvider> mMetricsBuilderProvider;

    public HandsFreeIntroActivity_MembersInjector(Provider<MetricsBuilderProvider> provider, Provider<EnrollmentTypeResolver> provider2) {
        this.mMetricsBuilderProvider = provider;
        this.mEnrollmentTypeResolverLazyProvider = provider2;
    }

    public static MembersInjector<HandsFreeIntroActivity> create(Provider<MetricsBuilderProvider> provider, Provider<EnrollmentTypeResolver> provider2) {
        return new HandsFreeIntroActivity_MembersInjector(provider, provider2);
    }

    public static void injectMEnrollmentTypeResolverLazy(HandsFreeIntroActivity handsFreeIntroActivity, Lazy<EnrollmentTypeResolver> lazy) {
        handsFreeIntroActivity.mEnrollmentTypeResolverLazy = lazy;
    }

    public static void injectMMetricsBuilderProvider(HandsFreeIntroActivity handsFreeIntroActivity, MetricsBuilderProvider metricsBuilderProvider) {
        handsFreeIntroActivity.mMetricsBuilderProvider = metricsBuilderProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HandsFreeIntroActivity handsFreeIntroActivity) {
        injectMMetricsBuilderProvider(handsFreeIntroActivity, this.mMetricsBuilderProvider.mo10268get());
        injectMEnrollmentTypeResolverLazy(handsFreeIntroActivity, DoubleCheck.lazy(this.mEnrollmentTypeResolverLazyProvider));
    }
}
