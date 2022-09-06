package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.WorkflowUpdateProducer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesWorkflowUpdateProducerFactory implements Factory<WorkflowUpdateProducer> {
    private final Provider<DeviceActionCreator> actionCreatorProvider;
    private final WorkflowModule module;
    private final Provider<WJErrorMapper<Throwable>> wjErrorMapperProvider;

    public WorkflowModule_ProvidesWorkflowUpdateProducerFactory(WorkflowModule workflowModule, Provider<DeviceActionCreator> provider, Provider<WJErrorMapper<Throwable>> provider2) {
        this.module = workflowModule;
        this.actionCreatorProvider = provider;
        this.wjErrorMapperProvider = provider2;
    }

    public static WorkflowModule_ProvidesWorkflowUpdateProducerFactory create(WorkflowModule workflowModule, Provider<DeviceActionCreator> provider, Provider<WJErrorMapper<Throwable>> provider2) {
        return new WorkflowModule_ProvidesWorkflowUpdateProducerFactory(workflowModule, provider, provider2);
    }

    public static WorkflowUpdateProducer provideInstance(WorkflowModule workflowModule, Provider<DeviceActionCreator> provider, Provider<WJErrorMapper<Throwable>> provider2) {
        return proxyProvidesWorkflowUpdateProducer(workflowModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WorkflowUpdateProducer proxyProvidesWorkflowUpdateProducer(WorkflowModule workflowModule, DeviceActionCreator deviceActionCreator, WJErrorMapper<Throwable> wJErrorMapper) {
        return (WorkflowUpdateProducer) Preconditions.checkNotNull(workflowModule.providesWorkflowUpdateProducer(deviceActionCreator, wJErrorMapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowUpdateProducer mo10268get() {
        return provideInstance(this.module, this.actionCreatorProvider, this.wjErrorMapperProvider);
    }
}
