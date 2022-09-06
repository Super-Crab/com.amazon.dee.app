package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesDSSClientFactory implements Factory<DSSClient> {
    private final Provider<Context> contextProvider;
    private final ProvisioningModule module;
    private final Provider<ProvisioningServiceConfiguration> serviceConfigurationProvider;

    public ProvisioningModule_ProvidesDSSClientFactory(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisioningServiceConfiguration> provider2) {
        this.module = provisioningModule;
        this.contextProvider = provider;
        this.serviceConfigurationProvider = provider2;
    }

    public static ProvisioningModule_ProvidesDSSClientFactory create(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisioningServiceConfiguration> provider2) {
        return new ProvisioningModule_ProvidesDSSClientFactory(provisioningModule, provider, provider2);
    }

    public static DSSClient provideInstance(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisioningServiceConfiguration> provider2) {
        return proxyProvidesDSSClient(provisioningModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static DSSClient proxyProvidesDSSClient(ProvisioningModule provisioningModule, Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        return (DSSClient) Preconditions.checkNotNull(provisioningModule.providesDSSClient(context, provisioningServiceConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DSSClient mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.serviceConfigurationProvider);
    }
}
