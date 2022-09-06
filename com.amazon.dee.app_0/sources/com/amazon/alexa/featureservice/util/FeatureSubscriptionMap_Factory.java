package com.amazon.alexa.featureservice.util;

import dagger.internal.Factory;
/* loaded from: classes7.dex */
public final class FeatureSubscriptionMap_Factory implements Factory<FeatureSubscriptionMap> {
    private static final FeatureSubscriptionMap_Factory INSTANCE = new FeatureSubscriptionMap_Factory();

    public static FeatureSubscriptionMap_Factory create() {
        return INSTANCE;
    }

    public static FeatureSubscriptionMap newFeatureSubscriptionMap() {
        return new FeatureSubscriptionMap();
    }

    public static FeatureSubscriptionMap provideInstance() {
        return new FeatureSubscriptionMap();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureSubscriptionMap mo10268get() {
        return provideInstance();
    }
}
