package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningServicesModule_ProvidesDSSClientFactory implements Factory<DSSClient> {
    private final Provider<Context> contextProvider;
    private final Provider<DSSServiceConfiguration> dssServiceConfigurationProvider;
    private final ProvisioningServicesModule module;

    public ProvisioningServicesModule_ProvidesDSSClientFactory(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<DSSServiceConfiguration> provider2) {
        this.module = provisioningServicesModule;
        this.contextProvider = provider;
        this.dssServiceConfigurationProvider = provider2;
    }

    public static ProvisioningServicesModule_ProvidesDSSClientFactory create(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<DSSServiceConfiguration> provider2) {
        return new ProvisioningServicesModule_ProvidesDSSClientFactory(provisioningServicesModule, provider, provider2);
    }

    public static DSSClient provideInstance(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<DSSServiceConfiguration> provider2) {
        return proxyProvidesDSSClient(provisioningServicesModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static DSSClient proxyProvidesDSSClient(ProvisioningServicesModule provisioningServicesModule, Context context, DSSServiceConfiguration dSSServiceConfiguration) {
        return (DSSClient) Preconditions.checkNotNull(provisioningServicesModule.providesDSSClient(context, dSSServiceConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DSSClient mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.dssServiceConfigurationProvider);
    }
}
