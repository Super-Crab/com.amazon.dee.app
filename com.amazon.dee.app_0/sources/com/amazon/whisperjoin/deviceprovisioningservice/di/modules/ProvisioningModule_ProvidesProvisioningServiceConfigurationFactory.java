package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesProvisioningServiceConfigurationFactory implements Factory<ProvisioningServiceConfiguration> {
    private final ProvisioningModule module;

    public ProvisioningModule_ProvidesProvisioningServiceConfigurationFactory(ProvisioningModule provisioningModule) {
        this.module = provisioningModule;
    }

    public static ProvisioningModule_ProvidesProvisioningServiceConfigurationFactory create(ProvisioningModule provisioningModule) {
        return new ProvisioningModule_ProvidesProvisioningServiceConfigurationFactory(provisioningModule);
    }

    public static ProvisioningServiceConfiguration provideInstance(ProvisioningModule provisioningModule) {
        return proxyProvidesProvisioningServiceConfiguration(provisioningModule);
    }

    public static ProvisioningServiceConfiguration proxyProvidesProvisioningServiceConfiguration(ProvisioningModule provisioningModule) {
        return (ProvisioningServiceConfiguration) Preconditions.checkNotNull(provisioningModule.providesProvisioningServiceConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisioningServiceConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
