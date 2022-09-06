package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<EnvironmentService> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static EnvironmentService provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static EnvironmentService proxyProvideEnvironmentService$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (EnvironmentService) Preconditions.checkNotNull(tarazedIntegrationModule.provideEnvironmentService$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module);
    }
}
