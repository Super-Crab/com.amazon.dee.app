package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.voice.features.FeatureChecker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SdkModule_ProvideAttentionSystemManagerFactory implements Factory<AttentionSystemManager> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<FeatureChecker> featureCheckerProvider;

    public SdkModule_ProvideAttentionSystemManagerFactory(Provider<AlexaServicesConnection> provider, Provider<FeatureChecker> provider2) {
        this.alexaServicesConnectionProvider = provider;
        this.featureCheckerProvider = provider2;
    }

    public static SdkModule_ProvideAttentionSystemManagerFactory create(Provider<AlexaServicesConnection> provider, Provider<FeatureChecker> provider2) {
        return new SdkModule_ProvideAttentionSystemManagerFactory(provider, provider2);
    }

    public static AttentionSystemManager provideInstance(Provider<AlexaServicesConnection> provider, Provider<FeatureChecker> provider2) {
        return proxyProvideAttentionSystemManager(provider.mo10268get(), provider2.mo10268get());
    }

    public static AttentionSystemManager proxyProvideAttentionSystemManager(AlexaServicesConnection alexaServicesConnection, FeatureChecker featureChecker) {
        return (AttentionSystemManager) Preconditions.checkNotNull(SdkModule.provideAttentionSystemManager(alexaServicesConnection, featureChecker), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AttentionSystemManager mo10268get() {
        return provideInstance(this.alexaServicesConnectionProvider, this.featureCheckerProvider);
    }
}
