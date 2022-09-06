package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowStateStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.WorkflowUpdateProducer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesControlledSetupWorkflowFactory implements Factory<ControlledSetupWorkflow> {
    private final Provider<DeviceActionCreator> deviceActionCreatorProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final WorkflowModule module;
    private final Provider<TrustProvider.TrustState> trustStateProvider;
    private final Provider<WorkflowConfiguration> workflowConfigurationProvider;
    private final Provider<WorkflowStateStream> workflowStateStreamProvider;
    private final Provider<WorkflowUpdateProducer> workflowUpdateProducerProvider;

    public WorkflowModule_ProvidesControlledSetupWorkflowFactory(WorkflowModule workflowModule, Provider<WorkflowStateStream> provider, Provider<DeviceActionCreator> provider2, Provider<WorkflowUpdateProducer> provider3, Provider<WorkflowConfiguration> provider4, Provider<DSSClient> provider5, Provider<TrustProvider.TrustState> provider6) {
        this.module = workflowModule;
        this.workflowStateStreamProvider = provider;
        this.deviceActionCreatorProvider = provider2;
        this.workflowUpdateProducerProvider = provider3;
        this.workflowConfigurationProvider = provider4;
        this.dssClientProvider = provider5;
        this.trustStateProvider = provider6;
    }

    public static WorkflowModule_ProvidesControlledSetupWorkflowFactory create(WorkflowModule workflowModule, Provider<WorkflowStateStream> provider, Provider<DeviceActionCreator> provider2, Provider<WorkflowUpdateProducer> provider3, Provider<WorkflowConfiguration> provider4, Provider<DSSClient> provider5, Provider<TrustProvider.TrustState> provider6) {
        return new WorkflowModule_ProvidesControlledSetupWorkflowFactory(workflowModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static ControlledSetupWorkflow provideInstance(WorkflowModule workflowModule, Provider<WorkflowStateStream> provider, Provider<DeviceActionCreator> provider2, Provider<WorkflowUpdateProducer> provider3, Provider<WorkflowConfiguration> provider4, Provider<DSSClient> provider5, Provider<TrustProvider.TrustState> provider6) {
        return proxyProvidesControlledSetupWorkflow(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static ControlledSetupWorkflow proxyProvidesControlledSetupWorkflow(WorkflowModule workflowModule, WorkflowStateStream workflowStateStream, DeviceActionCreator deviceActionCreator, WorkflowUpdateProducer workflowUpdateProducer, WorkflowConfiguration workflowConfiguration, DSSClient dSSClient, TrustProvider.TrustState trustState) {
        return (ControlledSetupWorkflow) Preconditions.checkNotNull(workflowModule.providesControlledSetupWorkflow(workflowStateStream, deviceActionCreator, workflowUpdateProducer, workflowConfiguration, dSSClient, trustState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ControlledSetupWorkflow mo10268get() {
        return provideInstance(this.module, this.workflowStateStreamProvider, this.deviceActionCreatorProvider, this.workflowUpdateProducerProvider, this.workflowConfigurationProvider, this.dssClientProvider, this.trustStateProvider);
    }
}
