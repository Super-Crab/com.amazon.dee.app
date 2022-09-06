package com.amazon.alexa.featureservice.service;

import com.amazon.alexa.featureservice.util.FeatureSubscriptionMap;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FeatureSubscriptionManager_Factory implements Factory<FeatureSubscriptionManager> {
    private final Provider<FeatureSubscriptionMap> featureSubscriptionMapProvider;

    public FeatureSubscriptionManager_Factory(Provider<FeatureSubscriptionMap> provider) {
        this.featureSubscriptionMapProvider = provider;
    }

    public static FeatureSubscriptionManager_Factory create(Provider<FeatureSubscriptionMap> provider) {
        return new FeatureSubscriptionManager_Factory(provider);
    }

    public static FeatureSubscriptionManager newFeatureSubscriptionManager(FeatureSubscriptionMap featureSubscriptionMap) {
        return new FeatureSubscriptionManager(featureSubscriptionMap);
    }

    public static FeatureSubscriptionManager provideInstance(Provider<FeatureSubscriptionMap> provider) {
        return new FeatureSubscriptionManager(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureSubscriptionManager mo10268get() {
        return provideInstance(this.featureSubscriptionMapProvider);
    }
}
