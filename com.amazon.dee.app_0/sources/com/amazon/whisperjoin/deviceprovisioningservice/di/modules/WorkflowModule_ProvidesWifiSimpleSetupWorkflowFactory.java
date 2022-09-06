package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowStateStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesWifiSimpleSetupWorkflowFactory implements Factory<WifiSimpleSetupZeroTouchWorkflow> {
    private final Provider<DeviceActionCreator> deviceActionCreatorProvider;
    private final WorkflowModule module;
    private final Provider<TrustProvider.TrustState> trustStateProvider;
    private final Provider<WorkflowIdProvider> workflowIdProvider;
    private final Provider<WorkflowStateStream> workflowStateStreamProvider;

    public WorkflowModule_ProvidesWifiSimpleSetupWorkflowFactory(WorkflowModule workflowModule, Provider<WorkflowStateStream> provider, Provider<DeviceActionCreator> provider2, Provider<TrustProvider.TrustState> provider3, Provider<WorkflowIdProvider> provider4) {
        this.module = workflowModule;
        this.workflowStateStreamProvider = provider;
        this.deviceActionCreatorProvider = provider2;
        this.trustStateProvider = provider3;
        this.workflowIdProvider = provider4;
    }

    public static WorkflowModule_ProvidesWifiSimpleSetupWorkflowFactory create(WorkflowModule workflowModule, Provider<WorkflowStateStream> provider, Provider<DeviceActionCreator> provider2, Provider<TrustProvider.TrustState> provider3, Provider<WorkflowIdProvider> provider4) {
        return new WorkflowModule_ProvidesWifiSimpleSetupWorkflowFactory(workflowModule, provider, provider2, provider3, provider4);
    }

    public static WifiSimpleSetupZeroTouchWorkflow provideInstance(WorkflowModule workflowModule, Provider<WorkflowStateStream> provider, Provider<DeviceActionCreator> provider2, Provider<TrustProvider.TrustState> provider3, Provider<WorkflowIdProvider> provider4) {
        return proxyProvidesWifiSimpleSetupWorkflow(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static WifiSimpleSetupZeroTouchWorkflow proxyProvidesWifiSimpleSetupWorkflow(WorkflowModule workflowModule, WorkflowStateStream workflowStateStream, DeviceActionCreator deviceActionCreator, TrustProvider.TrustState trustState, WorkflowIdProvider workflowIdProvider) {
        return (WifiSimpleSetupZeroTouchWorkflow) Preconditions.checkNotNull(workflowModule.providesWifiSimpleSetupWorkflow(workflowStateStream, deviceActionCreator, trustState, workflowIdProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WifiSimpleSetupZeroTouchWorkflow mo10268get() {
        return provideInstance(this.module, this.workflowStateStreamProvider, this.deviceActionCreatorProvider, this.trustStateProvider, this.workflowIdProvider);
    }
}
