package com.amazon.alexa.voice.features;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvideFeatureAvailabilityFactory implements Factory<FeatureAvailability> {
    private final Provider<FeatureServiceV2> featureServiceProvider;

    public FeaturesModule_ProvideFeatureAvailabilityFactory(Provider<FeatureServiceV2> provider) {
        this.featureServiceProvider = provider;
    }

    public static FeaturesModule_ProvideFeatureAvailabilityFactory create(Provider<FeatureServiceV2> provider) {
        return new FeaturesModule_ProvideFeatureAvailabilityFactory(provider);
    }

    public static FeatureAvailability provideInstance(Provider<FeatureServiceV2> provider) {
        return proxyProvideFeatureAvailability(provider.mo10268get());
    }

    public static FeatureAvailability proxyProvideFeatureAvailability(FeatureServiceV2 featureServiceV2) {
        return (FeatureAvailability) Preconditions.checkNotNull(FeaturesModule.provideFeatureAvailability(featureServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureAvailability mo10268get() {
        return provideInstance(this.featureServiceProvider);
    }
}
