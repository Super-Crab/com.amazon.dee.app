package com.amazon.alexa.voice.app;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class ApplicationModule_ProvideFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private static final ApplicationModule_ProvideFeatureServiceV2Factory INSTANCE = new ApplicationModule_ProvideFeatureServiceV2Factory();

    public static ApplicationModule_ProvideFeatureServiceV2Factory create() {
        return INSTANCE;
    }

    public static FeatureServiceV2 provideInstance() {
        return proxyProvideFeatureServiceV2();
    }

    public static FeatureServiceV2 proxyProvideFeatureServiceV2() {
        return (FeatureServiceV2) Preconditions.checkNotNull(ApplicationModule.provideFeatureServiceV2(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance();
    }
}
