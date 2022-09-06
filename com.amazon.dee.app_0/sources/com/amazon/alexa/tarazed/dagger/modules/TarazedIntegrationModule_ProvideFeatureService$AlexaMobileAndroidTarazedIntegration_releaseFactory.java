package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<FeatureServiceV2> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvideFeatureService$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static FeatureServiceV2 provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvideFeatureService$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static FeatureServiceV2 proxyProvideFeatureService$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (FeatureServiceV2) Preconditions.checkNotNull(tarazedIntegrationModule.provideFeatureService$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceV2 mo10268get() {
        return provideInstance(this.module);
    }
}
