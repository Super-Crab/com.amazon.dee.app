package com.amazon.alexa.voice.tta.features;

import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FeaturesModule_ProvidesFeatureQueryFactory implements Factory<FeatureQuery> {
    private final Provider<FeatureFlagConsumer> featureFlagConsumerProvider;
    private final FeaturesModule module;

    public FeaturesModule_ProvidesFeatureQueryFactory(FeaturesModule featuresModule, Provider<FeatureFlagConsumer> provider) {
        this.module = featuresModule;
        this.featureFlagConsumerProvider = provider;
    }

    public static FeaturesModule_ProvidesFeatureQueryFactory create(FeaturesModule featuresModule, Provider<FeatureFlagConsumer> provider) {
        return new FeaturesModule_ProvidesFeatureQueryFactory(featuresModule, provider);
    }

    public static FeatureQuery provideInstance(FeaturesModule featuresModule, Provider<FeatureFlagConsumer> provider) {
        return proxyProvidesFeatureQuery(featuresModule, provider.mo10268get());
    }

    public static FeatureQuery proxyProvidesFeatureQuery(FeaturesModule featuresModule, FeatureFlagConsumer featureFlagConsumer) {
        return (FeatureQuery) Preconditions.checkNotNull(featuresModule.providesFeatureQuery(featureFlagConsumer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureQuery mo10268get() {
        return provideInstance(this.module, this.featureFlagConsumerProvider);
    }
}
