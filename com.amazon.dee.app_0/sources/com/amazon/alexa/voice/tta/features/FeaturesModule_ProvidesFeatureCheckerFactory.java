package com.amazon.alexa.voice.tta.features;

import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvidesFeatureCheckerFactory implements Factory<FeatureChecker> {
    private final Provider<FeatureQuery> featureQueryProvider;
    private final FeaturesModule module;

    public FeaturesModule_ProvidesFeatureCheckerFactory(FeaturesModule featuresModule, Provider<FeatureQuery> provider) {
        this.module = featuresModule;
        this.featureQueryProvider = provider;
    }

    public static FeaturesModule_ProvidesFeatureCheckerFactory create(FeaturesModule featuresModule, Provider<FeatureQuery> provider) {
        return new FeaturesModule_ProvidesFeatureCheckerFactory(featuresModule, provider);
    }

    public static FeatureChecker provideInstance(FeaturesModule featuresModule, Provider<FeatureQuery> provider) {
        return proxyProvidesFeatureChecker(featuresModule, provider.mo10268get());
    }

    public static FeatureChecker proxyProvidesFeatureChecker(FeaturesModule featuresModule, FeatureQuery featureQuery) {
        return (FeatureChecker) Preconditions.checkNotNull(featuresModule.providesFeatureChecker(featureQuery), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureChecker mo10268get() {
        return provideInstance(this.module, this.featureQueryProvider);
    }
}
