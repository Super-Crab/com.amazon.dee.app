package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesProvisionerInfoFactory implements Factory<ProvisionerInfo> {
    private final ProvisioningModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public ProvisioningModule_ProvidesProvisionerInfoFactory(ProvisioningModule provisioningModule, Provider<ProvisionerClientData> provider) {
        this.module = provisioningModule;
        this.provisionerClientDataProvider = provider;
    }

    public static ProvisioningModule_ProvidesProvisionerInfoFactory create(ProvisioningModule provisioningModule, Provider<ProvisionerClientData> provider) {
        return new ProvisioningModule_ProvidesProvisionerInfoFactory(provisioningModule, provider);
    }

    public static ProvisionerInfo provideInstance(ProvisioningModule provisioningModule, Provider<ProvisionerClientData> provider) {
        return proxyProvidesProvisionerInfo(provisioningModule, provider.mo10268get());
    }

    public static ProvisionerInfo proxyProvidesProvisionerInfo(ProvisioningModule provisioningModule, ProvisionerClientData provisionerClientData) {
        return (ProvisionerInfo) Preconditions.checkNotNull(provisioningModule.providesProvisionerInfo(provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisionerInfo mo10268get() {
        return provideInstance(this.module, this.provisionerClientDataProvider);
    }
}
