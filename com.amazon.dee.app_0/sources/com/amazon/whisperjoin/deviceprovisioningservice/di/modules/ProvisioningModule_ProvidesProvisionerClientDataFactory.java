package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesProvisionerClientDataFactory implements Factory<ProvisionerClientData> {
    private final Provider<MapAuthenticationProvider> mapAuthenticationProvider;
    private final ProvisioningModule module;
    private final Provider<ProvisioningServiceConfiguration> provisioningServiceConfigurationProvider;

    public ProvisioningModule_ProvidesProvisionerClientDataFactory(ProvisioningModule provisioningModule, Provider<ProvisioningServiceConfiguration> provider, Provider<MapAuthenticationProvider> provider2) {
        this.module = provisioningModule;
        this.provisioningServiceConfigurationProvider = provider;
        this.mapAuthenticationProvider = provider2;
    }

    public static ProvisioningModule_ProvidesProvisionerClientDataFactory create(ProvisioningModule provisioningModule, Provider<ProvisioningServiceConfiguration> provider, Provider<MapAuthenticationProvider> provider2) {
        return new ProvisioningModule_ProvidesProvisionerClientDataFactory(provisioningModule, provider, provider2);
    }

    public static ProvisionerClientData provideInstance(ProvisioningModule provisioningModule, Provider<ProvisioningServiceConfiguration> provider, Provider<MapAuthenticationProvider> provider2) {
        return proxyProvidesProvisionerClientData(provisioningModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ProvisionerClientData proxyProvidesProvisionerClientData(ProvisioningModule provisioningModule, ProvisioningServiceConfiguration provisioningServiceConfiguration, MapAuthenticationProvider mapAuthenticationProvider) {
        return (ProvisionerClientData) Preconditions.checkNotNull(provisioningModule.providesProvisionerClientData(provisioningServiceConfiguration, mapAuthenticationProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisionerClientData mo10268get() {
        return provideInstance(this.module, this.provisioningServiceConfigurationProvider, this.mapAuthenticationProvider);
    }
}
