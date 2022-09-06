package com.amazon.alexa.fitness.service;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FeatureServiceImpl_Factory implements Factory<FeatureServiceImpl> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final Provider<FeatureServiceV2> featureServiceProvider;

    public FeatureServiceImpl_Factory(Provider<ComponentRegistry> provider, Provider<FeatureServiceV2> provider2) {
        this.componentRegistryProvider = provider;
        this.featureServiceProvider = provider2;
    }

    public static FeatureServiceImpl_Factory create(Provider<ComponentRegistry> provider, Provider<FeatureServiceV2> provider2) {
        return new FeatureServiceImpl_Factory(provider, provider2);
    }

    public static FeatureServiceImpl newFeatureServiceImpl(ComponentRegistry componentRegistry, FeatureServiceV2 featureServiceV2) {
        return new FeatureServiceImpl(componentRegistry, featureServiceV2);
    }

    public static FeatureServiceImpl provideInstance(Provider<ComponentRegistry> provider, Provider<FeatureServiceV2> provider2) {
        return new FeatureServiceImpl(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceImpl mo10268get() {
        return provideInstance(this.componentRegistryProvider, this.featureServiceProvider);
    }
}
