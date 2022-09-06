package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesProvisioningServiceConfigurationFactory implements Factory<ProvisioningServiceConfiguration> {
    private final ProvisioningConfigurationModule module;

    public ProvisioningConfigurationModule_ProvidesProvisioningServiceConfigurationFactory(ProvisioningConfigurationModule provisioningConfigurationModule) {
        this.module = provisioningConfigurationModule;
    }

    public static ProvisioningConfigurationModule_ProvidesProvisioningServiceConfigurationFactory create(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return new ProvisioningConfigurationModule_ProvidesProvisioningServiceConfigurationFactory(provisioningConfigurationModule);
    }

    public static ProvisioningServiceConfiguration provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return proxyProvidesProvisioningServiceConfiguration(provisioningConfigurationModule);
    }

    public static ProvisioningServiceConfiguration proxyProvidesProvisioningServiceConfiguration(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return (ProvisioningServiceConfiguration) Preconditions.checkNotNull(provisioningConfigurationModule.providesProvisioningServiceConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisioningServiceConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
