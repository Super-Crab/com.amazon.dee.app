package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesProvisionerInfoFactory implements Factory<ProvisionerInfo> {
    private final ProvisioningConfigurationModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public ProvisioningConfigurationModule_ProvidesProvisionerInfoFactory(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<ProvisionerClientData> provider) {
        this.module = provisioningConfigurationModule;
        this.provisionerClientDataProvider = provider;
    }

    public static ProvisioningConfigurationModule_ProvidesProvisionerInfoFactory create(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<ProvisionerClientData> provider) {
        return new ProvisioningConfigurationModule_ProvidesProvisionerInfoFactory(provisioningConfigurationModule, provider);
    }

    public static ProvisionerInfo provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<ProvisionerClientData> provider) {
        return proxyProvidesProvisionerInfo(provisioningConfigurationModule, provider.mo10268get());
    }

    public static ProvisionerInfo proxyProvidesProvisionerInfo(ProvisioningConfigurationModule provisioningConfigurationModule, ProvisionerClientData provisionerClientData) {
        return (ProvisionerInfo) Preconditions.checkNotNull(provisioningConfigurationModule.providesProvisionerInfo(provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisionerInfo mo10268get() {
        return provideInstance(this.module, this.provisionerClientDataProvider);
    }
}
