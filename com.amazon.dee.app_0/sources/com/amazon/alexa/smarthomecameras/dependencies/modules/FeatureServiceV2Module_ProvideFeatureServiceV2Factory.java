package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class FeatureServiceV2Module_ProvideFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private static final FeatureServiceV2Module_ProvideFeatureServiceV2Factory INSTANCE = new FeatureServiceV2Module_ProvideFeatureServiceV2Factory();

    public static FeatureServiceV2Module_ProvideFeatureServiceV2Factory create() {
        return INSTANCE;
    }

    public static FeatureServiceV2 provideInstance() {
        return proxyProvideFeatureServiceV2();
    }

    public static FeatureServiceV2 proxyProvideFeatureServiceV2() {
        return (FeatureServiceV2) Preconditions.checkNotNull(FeatureServiceV2Module.provideFeatureServiceV2(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance();
    }
}
