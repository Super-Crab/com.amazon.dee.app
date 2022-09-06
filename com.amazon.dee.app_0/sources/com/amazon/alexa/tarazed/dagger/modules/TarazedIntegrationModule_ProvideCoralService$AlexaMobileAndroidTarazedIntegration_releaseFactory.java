package com.amazon.alexa.tarazed.dagger.modules;

import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideCoralService$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<CoralService> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideCoralService$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvideCoralService$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvideCoralService$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static CoralService provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvideCoralService$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static CoralService proxyProvideCoralService$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (CoralService) Preconditions.checkNotNull(tarazedIntegrationModule.provideCoralService$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoralService mo10268get() {
        return provideInstance(this.module);
    }
}
