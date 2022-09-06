package com.amazon.alexa.voice.features;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvideVoiceFeatureCheckerFactory implements Factory<VoiceFeatureChecker> {
    private final Provider<FeatureServiceV2> featureServiceProvider;

    public FeaturesModule_ProvideVoiceFeatureCheckerFactory(Provider<FeatureServiceV2> provider) {
        this.featureServiceProvider = provider;
    }

    public static FeaturesModule_ProvideVoiceFeatureCheckerFactory create(Provider<FeatureServiceV2> provider) {
        return new FeaturesModule_ProvideVoiceFeatureCheckerFactory(provider);
    }

    public static VoiceFeatureChecker provideInstance(Provider<FeatureServiceV2> provider) {
        return proxyProvideVoiceFeatureChecker(provider.mo10268get());
    }

    public static VoiceFeatureChecker proxyProvideVoiceFeatureChecker(FeatureServiceV2 featureServiceV2) {
        return (VoiceFeatureChecker) Preconditions.checkNotNull(FeaturesModule.provideVoiceFeatureChecker(featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceFeatureChecker mo10268get() {
        return provideInstance(this.featureServiceProvider);
    }
}
