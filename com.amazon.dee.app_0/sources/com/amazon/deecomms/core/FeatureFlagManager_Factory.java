package com.amazon.deecomms.core;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class FeatureFlagManager_Factory implements Factory<FeatureFlagManager> {
    private final Provider<FeatureServiceV2> featureServiceV2LazyProvider;

    public FeatureFlagManager_Factory(Provider<FeatureServiceV2> provider) {
        this.featureServiceV2LazyProvider = provider;
    }

    public static FeatureFlagManager_Factory create(Provider<FeatureServiceV2> provider) {
        return new FeatureFlagManager_Factory(provider);
    }

    public static FeatureFlagManager newFeatureFlagManager(Lazy<FeatureServiceV2> lazy) {
        return new FeatureFlagManager(lazy);
    }

    public static FeatureFlagManager provideInstance(Provider<FeatureServiceV2> provider) {
        return new FeatureFlagManager(DoubleCheck.lazy(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFlagManager mo10268get() {
        return provideInstance(this.featureServiceV2LazyProvider);
    }
}
