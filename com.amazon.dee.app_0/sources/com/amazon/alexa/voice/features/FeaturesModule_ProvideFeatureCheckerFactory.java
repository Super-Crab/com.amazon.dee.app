package com.amazon.alexa.voice.features;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvideFeatureCheckerFactory implements Factory<FeatureChecker> {
    private final Provider<FeatureServiceV2> featureServiceProvider;

    public FeaturesModule_ProvideFeatureCheckerFactory(Provider<FeatureServiceV2> provider) {
        this.featureServiceProvider = provider;
    }

    public static FeaturesModule_ProvideFeatureCheckerFactory create(Provider<FeatureServiceV2> provider) {
        return new FeaturesModule_ProvideFeatureCheckerFactory(provider);
    }

    public static FeatureChecker provideInstance(Provider<FeatureServiceV2> provider) {
        return proxyProvideFeatureChecker(provider.mo10268get());
    }

    public static FeatureChecker proxyProvideFeatureChecker(FeatureServiceV2 featureServiceV2) {
        return (FeatureChecker) Preconditions.checkNotNull(FeaturesModule.provideFeatureChecker(featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureChecker mo10268get() {
        return provideInstance(this.featureServiceProvider);
    }
}
