package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideFeatureServiceV2Factory implements Factory<FeatureServiceV2> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideFeatureServiceV2Factory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideFeatureServiceV2Factory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideFeatureServiceV2Factory(applicationModule);
    }

    public static FeatureServiceV2 provideInstance(ApplicationModule applicationModule) {
        return proxyProvideFeatureServiceV2(applicationModule);
    }

    public static FeatureServiceV2 proxyProvideFeatureServiceV2(ApplicationModule applicationModule) {
        return (FeatureServiceV2) Preconditions.checkNotNull(applicationModule.provideFeatureServiceV2(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module);
    }
}
