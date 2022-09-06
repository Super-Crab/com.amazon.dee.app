package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksFromDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory implements Factory<GetAvailableWifiNetworksFromDSS> {
    private final Provider<CurrentWifiNetworkProvider> currentWifiNetworkProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final WorkflowModule module;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<TrustProvider.TrustState> trustStateProvider;

    public WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<CurrentWifiNetworkProvider> provider2, Provider<ProvisioningMethod> provider3, Provider<TrustProvider.TrustState> provider4) {
        this.module = workflowModule;
        this.dssClientProvider = provider;
        this.currentWifiNetworkProvider = provider2;
        this.provisioningMethodProvider = provider3;
        this.trustStateProvider = provider4;
    }

    public static WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory create(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<CurrentWifiNetworkProvider> provider2, Provider<ProvisioningMethod> provider3, Provider<TrustProvider.TrustState> provider4) {
        return new WorkflowModule_ProvidesGetAvailableWifiNetworksFromDSSFactory(workflowModule, provider, provider2, provider3, provider4);
    }

    public static GetAvailableWifiNetworksFromDSS provideInstance(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<CurrentWifiNetworkProvider> provider2, Provider<ProvisioningMethod> provider3, Provider<TrustProvider.TrustState> provider4) {
        return proxyProvidesGetAvailableWifiNetworksFromDSS(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static GetAvailableWifiNetworksFromDSS proxyProvidesGetAvailableWifiNetworksFromDSS(WorkflowModule workflowModule, DSSClient dSSClient, CurrentWifiNetworkProvider currentWifiNetworkProvider, ProvisioningMethod provisioningMethod, TrustProvider.TrustState trustState) {
        return (GetAvailableWifiNetworksFromDSS) Preconditions.checkNotNull(workflowModule.providesGetAvailableWifiNetworksFromDSS(dSSClient, currentWifiNetworkProvider, provisioningMethod, trustState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GetAvailableWifiNetworksFromDSS mo10268get() {
        return provideInstance(this.module, this.dssClientProvider, this.currentWifiNetworkProvider, this.provisioningMethodProvider, this.trustStateProvider);
    }
}
