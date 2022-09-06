package com.amazon.alexa.sharing.sharingsdk;

import android.content.Context;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ContentSharingService_Factory implements Factory<ContentSharingService> {
    private final Provider<Context> contextProvider;
    private final Provider<FeatureServiceUtil> featureServiceUtilProvider;
    private final Provider<AlexaCommsCoreIdentityService> identityServiceProvider;
    private final Provider<CommsMetricsEmitter> metricsEmitterProvider;

    public ContentSharingService_Factory(Provider<CommsMetricsEmitter> provider, Provider<AlexaCommsCoreIdentityService> provider2, Provider<FeatureServiceUtil> provider3, Provider<Context> provider4) {
        this.metricsEmitterProvider = provider;
        this.identityServiceProvider = provider2;
        this.featureServiceUtilProvider = provider3;
        this.contextProvider = provider4;
    }

    public static ContentSharingService_Factory create(Provider<CommsMetricsEmitter> provider, Provider<AlexaCommsCoreIdentityService> provider2, Provider<FeatureServiceUtil> provider3, Provider<Context> provider4) {
        return new ContentSharingService_Factory(provider, provider2, provider3, provider4);
    }

    public static ContentSharingService newContentSharingService(CommsMetricsEmitter commsMetricsEmitter, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, FeatureServiceUtil featureServiceUtil, Context context) {
        return new ContentSharingService(commsMetricsEmitter, alexaCommsCoreIdentityService, featureServiceUtil, context);
    }

    public static ContentSharingService provideInstance(Provider<CommsMetricsEmitter> provider, Provider<AlexaCommsCoreIdentityService> provider2, Provider<FeatureServiceUtil> provider3, Provider<Context> provider4) {
        return new ContentSharingService(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentSharingService mo10268get() {
        return provideInstance(this.metricsEmitterProvider, this.identityServiceProvider, this.featureServiceUtilProvider, this.contextProvider);
    }
}
