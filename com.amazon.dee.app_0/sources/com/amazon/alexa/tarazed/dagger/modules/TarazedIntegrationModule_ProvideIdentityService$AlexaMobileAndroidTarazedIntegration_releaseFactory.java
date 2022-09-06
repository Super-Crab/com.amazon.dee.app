package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<IdentityService> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvideIdentityService$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static IdentityService provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvideIdentityService$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static IdentityService proxyProvideIdentityService$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (IdentityService) Preconditions.checkNotNull(tarazedIntegrationModule.provideIdentityService$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
