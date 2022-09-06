package com.amazon.alexa.featureservice.repo.mapper;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FeatureCollectionMapper_Factory implements Factory<FeatureCollectionMapper> {
    private final Provider<FeatureDataMapper> featureDataMapperProvider;

    public FeatureCollectionMapper_Factory(Provider<FeatureDataMapper> provider) {
        this.featureDataMapperProvider = provider;
    }

    public static FeatureCollectionMapper_Factory create(Provider<FeatureDataMapper> provider) {
        return new FeatureCollectionMapper_Factory(provider);
    }

    public static FeatureCollectionMapper newFeatureCollectionMapper(FeatureDataMapper featureDataMapper) {
        return new FeatureCollectionMapper(featureDataMapper);
    }

    public static FeatureCollectionMapper provideInstance(Provider<FeatureDataMapper> provider) {
        return new FeatureCollectionMapper(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureCollectionMapper mo10268get() {
        return provideInstance(this.featureDataMapperProvider);
    }
}
