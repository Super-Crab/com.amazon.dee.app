package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesIsDebugEnabledFactory implements Factory<Boolean> {
    private final ProvisioningConfigurationModule module;

    public ProvisioningConfigurationModule_ProvidesIsDebugEnabledFactory(ProvisioningConfigurationModule provisioningConfigurationModule) {
        this.module = provisioningConfigurationModule;
    }

    public static ProvisioningConfigurationModule_ProvidesIsDebugEnabledFactory create(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return new ProvisioningConfigurationModule_ProvidesIsDebugEnabledFactory(provisioningConfigurationModule);
    }

    public static Boolean provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return proxyProvidesIsDebugEnabled(provisioningConfigurationModule);
    }

    public static Boolean proxyProvidesIsDebugEnabled(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return (Boolean) Preconditions.checkNotNull(provisioningConfigurationModule.providesIsDebugEnabled(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Boolean mo10268get() {
        return provideInstance(this.module);
    }
}
