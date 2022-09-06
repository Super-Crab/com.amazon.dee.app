package com.amazon.alexa.featureservice.database.dao;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RxFeatureDao_Factory implements Factory<RxFeatureDao> {
    private final Provider<FeatureFlagDao> daoProvider;

    public RxFeatureDao_Factory(Provider<FeatureFlagDao> provider) {
        this.daoProvider = provider;
    }

    public static RxFeatureDao_Factory create(Provider<FeatureFlagDao> provider) {
        return new RxFeatureDao_Factory(provider);
    }

    public static RxFeatureDao newRxFeatureDao(FeatureFlagDao featureFlagDao) {
        return new RxFeatureDao(featureFlagDao);
    }

    public static RxFeatureDao provideInstance(Provider<FeatureFlagDao> provider) {
        return new RxFeatureDao(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RxFeatureDao mo10268get() {
        return provideInstance(this.daoProvider);
    }
}
