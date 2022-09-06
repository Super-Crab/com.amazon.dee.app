package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesWhiteListPolicyUpdateListenerFactory implements Factory<WhiteListPolicyUpdateListener> {
    private final Provider<Context> contextProvider;
    private final ProvisioningModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public ProvisioningModule_ProvidesWhiteListPolicyUpdateListenerFactory(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        this.module = provisioningModule;
        this.contextProvider = provider;
        this.provisionerClientDataProvider = provider2;
    }

    public static ProvisioningModule_ProvidesWhiteListPolicyUpdateListenerFactory create(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        return new ProvisioningModule_ProvidesWhiteListPolicyUpdateListenerFactory(provisioningModule, provider, provider2);
    }

    public static WhiteListPolicyUpdateListener provideInstance(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisionerClientData> provider2) {
        return proxyProvidesWhiteListPolicyUpdateListener(provisioningModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WhiteListPolicyUpdateListener proxyProvidesWhiteListPolicyUpdateListener(ProvisioningModule provisioningModule, Context context, ProvisionerClientData provisionerClientData) {
        return (WhiteListPolicyUpdateListener) Preconditions.checkNotNull(provisioningModule.providesWhiteListPolicyUpdateListener(context, provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WhiteListPolicyUpdateListener mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.provisionerClientDataProvider);
    }
}
