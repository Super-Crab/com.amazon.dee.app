package com.amazon.dee.app.dependencies;

import com.amazon.alexa.feature.provider.api.FeatureStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class FeaturesModule_ProvideFeatureStoreFactory implements Factory<FeatureStore> {
    private final FeaturesModule module;

    public FeaturesModule_ProvideFeatureStoreFactory(FeaturesModule featuresModule) {
        this.module = featuresModule;
    }

    public static FeaturesModule_ProvideFeatureStoreFactory create(FeaturesModule featuresModule) {
        return new FeaturesModule_ProvideFeatureStoreFactory(featuresModule);
    }

    public static FeatureStore provideInstance(FeaturesModule featuresModule) {
        return proxyProvideFeatureStore(featuresModule);
    }

    public static FeatureStore proxyProvideFeatureStore(FeaturesModule featuresModule) {
        return (FeatureStore) Preconditions.checkNotNull(featuresModule.provideFeatureStore(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureStore mo10268get() {
        return provideInstance(this.module);
    }
}
