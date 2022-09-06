package com.amazon.alexa.featureservice.repo.mapper;

import dagger.internal.Factory;
/* loaded from: classes7.dex */
public final class FeatureDataMapper_Factory implements Factory<FeatureDataMapper> {
    private static final FeatureDataMapper_Factory INSTANCE = new FeatureDataMapper_Factory();

    public static FeatureDataMapper_Factory create() {
        return INSTANCE;
    }

    public static FeatureDataMapper newFeatureDataMapper() {
        return new FeatureDataMapper();
    }

    public static FeatureDataMapper provideInstance() {
        return new FeatureDataMapper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureDataMapper mo10268get() {
        return provideInstance();
    }
}
