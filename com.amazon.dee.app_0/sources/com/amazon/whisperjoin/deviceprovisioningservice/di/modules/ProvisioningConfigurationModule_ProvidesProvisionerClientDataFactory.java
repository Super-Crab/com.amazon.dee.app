package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesProvisionerClientDataFactory implements Factory<ProvisionerClientData> {
    private final Provider<MapAuthenticationProvider> mapAuthenticationProvider;
    private final ProvisioningConfigurationModule module;
    private final Provider<ProvisioningServiceConfiguration> provisioningServiceConfigurationProvider;

    public ProvisioningConfigurationModule_ProvidesProvisionerClientDataFactory(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<ProvisioningServiceConfiguration> provider, Provider<MapAuthenticationProvider> provider2) {
        this.module = provisioningConfigurationModule;
        this.provisioningServiceConfigurationProvider = provider;
        this.mapAuthenticationProvider = provider2;
    }

    public static ProvisioningConfigurationModule_ProvidesProvisionerClientDataFactory create(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<ProvisioningServiceConfiguration> provider, Provider<MapAuthenticationProvider> provider2) {
        return new ProvisioningConfigurationModule_ProvidesProvisionerClientDataFactory(provisioningConfigurationModule, provider, provider2);
    }

    public static ProvisionerClientData provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<ProvisioningServiceConfiguration> provider, Provider<MapAuthenticationProvider> provider2) {
        return proxyProvidesProvisionerClientData(provisioningConfigurationModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ProvisionerClientData proxyProvidesProvisionerClientData(ProvisioningConfigurationModule provisioningConfigurationModule, ProvisioningServiceConfiguration provisioningServiceConfiguration, MapAuthenticationProvider mapAuthenticationProvider) {
        return (ProvisionerClientData) Preconditions.checkNotNull(provisioningConfigurationModule.providesProvisionerClientData(provisioningServiceConfiguration, mapAuthenticationProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisionerClientData mo10268get() {
        return provideInstance(this.module, this.provisioningServiceConfigurationProvider, this.mapAuthenticationProvider);
    }
}
