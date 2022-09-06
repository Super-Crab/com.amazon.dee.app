package com.amazon.alexa.featureservice.implementation;

import dagger.internal.Factory;
/* loaded from: classes7.dex */
public final class FeatureRegistryUtil_Factory implements Factory<FeatureRegistryUtil> {
    private static final FeatureRegistryUtil_Factory INSTANCE = new FeatureRegistryUtil_Factory();

    public static FeatureRegistryUtil_Factory create() {
        return INSTANCE;
    }

    public static FeatureRegistryUtil newFeatureRegistryUtil() {
        return new FeatureRegistryUtil();
    }

    public static FeatureRegistryUtil provideInstance() {
        return new FeatureRegistryUtil();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureRegistryUtil mo10268get() {
        return provideInstance();
    }
}
