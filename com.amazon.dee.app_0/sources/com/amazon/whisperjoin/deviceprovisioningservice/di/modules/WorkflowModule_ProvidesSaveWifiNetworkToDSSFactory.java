package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.SaveWifiNetworkThroughDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory implements Factory<SaveWifiNetworkThroughDSS> {
    private final Provider<DSSClient> dssClientProvider;
    private final WorkflowModule module;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<TrustProvider.TrustState> trustStateProvider;

    public WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisioningMethod> provider2, Provider<TrustProvider.TrustState> provider3) {
        this.module = workflowModule;
        this.dssClientProvider = provider;
        this.provisioningMethodProvider = provider2;
        this.trustStateProvider = provider3;
    }

    public static WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory create(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisioningMethod> provider2, Provider<TrustProvider.TrustState> provider3) {
        return new WorkflowModule_ProvidesSaveWifiNetworkToDSSFactory(workflowModule, provider, provider2, provider3);
    }

    public static SaveWifiNetworkThroughDSS provideInstance(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisioningMethod> provider2, Provider<TrustProvider.TrustState> provider3) {
        return proxyProvidesSaveWifiNetworkToDSS(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static SaveWifiNetworkThroughDSS proxyProvidesSaveWifiNetworkToDSS(WorkflowModule workflowModule, DSSClient dSSClient, ProvisioningMethod provisioningMethod, TrustProvider.TrustState trustState) {
        return (SaveWifiNetworkThroughDSS) Preconditions.checkNotNull(workflowModule.providesSaveWifiNetworkToDSS(dSSClient, provisioningMethod, trustState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SaveWifiNetworkThroughDSS mo10268get() {
        return provideInstance(this.module, this.dssClientProvider, this.provisioningMethodProvider, this.trustStateProvider);
    }
}
