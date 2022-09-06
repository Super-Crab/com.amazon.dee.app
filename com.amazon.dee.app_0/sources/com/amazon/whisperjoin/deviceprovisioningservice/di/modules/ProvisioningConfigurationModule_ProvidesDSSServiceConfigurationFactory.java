package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesDSSServiceConfigurationFactory implements Factory<DSSServiceConfiguration> {
    private final ProvisioningConfigurationModule module;

    public ProvisioningConfigurationModule_ProvidesDSSServiceConfigurationFactory(ProvisioningConfigurationModule provisioningConfigurationModule) {
        this.module = provisioningConfigurationModule;
    }

    public static ProvisioningConfigurationModule_ProvidesDSSServiceConfigurationFactory create(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return new ProvisioningConfigurationModule_ProvidesDSSServiceConfigurationFactory(provisioningConfigurationModule);
    }

    public static DSSServiceConfiguration provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return proxyProvidesDSSServiceConfiguration(provisioningConfigurationModule);
    }

    public static DSSServiceConfiguration proxyProvidesDSSServiceConfiguration(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return (DSSServiceConfiguration) Preconditions.checkNotNull(provisioningConfigurationModule.providesDSSServiceConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DSSServiceConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
