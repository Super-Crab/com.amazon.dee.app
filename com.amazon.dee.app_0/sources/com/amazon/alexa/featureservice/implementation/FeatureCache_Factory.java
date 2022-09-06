package com.amazon.alexa.featureservice.implementation;

import com.google.gson.Gson;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FeatureCache_Factory implements Factory<FeatureCache> {
    private final Provider<Gson> gsonProvider;

    public FeatureCache_Factory(Provider<Gson> provider) {
        this.gsonProvider = provider;
    }

    public static FeatureCache_Factory create(Provider<Gson> provider) {
        return new FeatureCache_Factory(provider);
    }

    public static FeatureCache newFeatureCache(Gson gson) {
        return new FeatureCache(gson);
    }

    public static FeatureCache provideInstance(Provider<Gson> provider) {
        return new FeatureCache(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureCache mo10268get() {
        return provideInstance(this.gsonProvider);
    }
}
