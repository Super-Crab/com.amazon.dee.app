package com.amazon.alexa.sharing.comms.dependencies;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.sharing.sharingsdk.ContentSharingService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvidesContentSharingServiceFactory implements Factory<ContentSharingService> {
    private final Provider<Context> contextProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<AlexaCommsCoreIdentityService> identityServiceProvider;
    private final Provider<AlexaCommsCoreMetricsService> metricsServiceLazyProvider;
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvidesContentSharingServiceFactory(AlexaSharingModule alexaSharingModule, Provider<AlexaCommsCoreMetricsService> provider, Provider<FeatureServiceV2> provider2, Provider<AlexaCommsCoreIdentityService> provider3, Provider<Context> provider4) {
        this.module = alexaSharingModule;
        this.metricsServiceLazyProvider = provider;
        this.featureServiceV2Provider = provider2;
        this.identityServiceProvider = provider3;
        this.contextProvider = provider4;
    }

    public static AlexaSharingModule_ProvidesContentSharingServiceFactory create(AlexaSharingModule alexaSharingModule, Provider<AlexaCommsCoreMetricsService> provider, Provider<FeatureServiceV2> provider2, Provider<AlexaCommsCoreIdentityService> provider3, Provider<Context> provider4) {
        return new AlexaSharingModule_ProvidesContentSharingServiceFactory(alexaSharingModule, provider, provider2, provider3, provider4);
    }

    public static ContentSharingService provideInstance(AlexaSharingModule alexaSharingModule, Provider<AlexaCommsCoreMetricsService> provider, Provider<FeatureServiceV2> provider2, Provider<AlexaCommsCoreIdentityService> provider3, Provider<Context> provider4) {
        return proxyProvidesContentSharingService(alexaSharingModule, DoubleCheck.lazy(provider), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static ContentSharingService proxyProvidesContentSharingService(AlexaSharingModule alexaSharingModule, Lazy<AlexaCommsCoreMetricsService> lazy, FeatureServiceV2 featureServiceV2, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, Context context) {
        return (ContentSharingService) Preconditions.checkNotNull(alexaSharingModule.providesContentSharingService(lazy, featureServiceV2, alexaCommsCoreIdentityService, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentSharingService mo10268get() {
        return provideInstance(this.module, this.metricsServiceLazyProvider, this.featureServiceV2Provider, this.identityServiceProvider, this.contextProvider);
    }
}
