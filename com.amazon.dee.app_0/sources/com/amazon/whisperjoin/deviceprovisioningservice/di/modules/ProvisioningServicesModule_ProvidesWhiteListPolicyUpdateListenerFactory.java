package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningServicesModule_ProvidesWhiteListPolicyUpdateListenerFactory implements Factory<WhiteListPolicyUpdateListener> {
    private final Provider<Context> contextProvider;
    private final ProvisioningServicesModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public ProvisioningServicesModule_ProvidesWhiteListPolicyUpdateListenerFactory(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        this.module = provisioningServicesModule;
        this.contextProvider = provider;
        this.provisionerClientDataProvider = provider2;
    }

    public static ProvisioningServicesModule_ProvidesWhiteListPolicyUpdateListenerFactory create(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        return new ProvisioningServicesModule_ProvidesWhiteListPolicyUpdateListenerFactory(provisioningServicesModule, provider, provider2);
    }

    public static WhiteListPolicyUpdateListener provideInstance(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        return proxyProvidesWhiteListPolicyUpdateListener(provisioningServicesModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WhiteListPolicyUpdateListener proxyProvidesWhiteListPolicyUpdateListener(ProvisioningServicesModule provisioningServicesModule, Context context, ProvisionerClientData provisionerClientData) {
        return (WhiteListPolicyUpdateListener) Preconditions.checkNotNull(provisioningServicesModule.providesWhiteListPolicyUpdateListener(context, provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WhiteListPolicyUpdateListener mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.provisionerClientDataProvider);
    }
}
