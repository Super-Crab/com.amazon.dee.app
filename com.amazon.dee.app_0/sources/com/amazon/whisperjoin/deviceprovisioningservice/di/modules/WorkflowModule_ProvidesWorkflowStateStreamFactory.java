package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowStateStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionsExecutor;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowResultLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesWorkflowStateStreamFactory implements Factory<WorkflowStateStream> {
    private final Provider<DeviceActionCreator> actionControllerProvider;
    private final Provider<DeviceActionsExecutor> actionsExecutorProvider;
    private final Provider<DeviceEventStream> eventStreamProvider;
    private final WorkflowModule module;
    private final Provider<WorkflowEventReporter> workflowEventReporterProvider;
    private final Provider<WorkflowResultLogger> workflowResultLoggerProvider;

    public WorkflowModule_ProvidesWorkflowStateStreamFactory(WorkflowModule workflowModule, Provider<DeviceActionCreator> provider, Provider<DeviceActionsExecutor> provider2, Provider<DeviceEventStream> provider3, Provider<WorkflowEventReporter> provider4, Provider<WorkflowResultLogger> provider5) {
        this.module = workflowModule;
        this.actionControllerProvider = provider;
        this.actionsExecutorProvider = provider2;
        this.eventStreamProvider = provider3;
        this.workflowEventReporterProvider = provider4;
        this.workflowResultLoggerProvider = provider5;
    }

    public static WorkflowModule_ProvidesWorkflowStateStreamFactory create(WorkflowModule workflowModule, Provider<DeviceActionCreator> provider, Provider<DeviceActionsExecutor> provider2, Provider<DeviceEventStream> provider3, Provider<WorkflowEventReporter> provider4, Provider<WorkflowResultLogger> provider5) {
        return new WorkflowModule_ProvidesWorkflowStateStreamFactory(workflowModule, provider, provider2, provider3, provider4, provider5);
    }

    public static WorkflowStateStream provideInstance(WorkflowModule workflowModule, Provider<DeviceActionCreator> provider, Provider<DeviceActionsExecutor> provider2, Provider<DeviceEventStream> provider3, Provider<WorkflowEventReporter> provider4, Provider<WorkflowResultLogger> provider5) {
        return proxyProvidesWorkflowStateStream(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static WorkflowStateStream proxyProvidesWorkflowStateStream(WorkflowModule workflowModule, DeviceActionCreator deviceActionCreator, DeviceActionsExecutor deviceActionsExecutor, DeviceEventStream deviceEventStream, WorkflowEventReporter workflowEventReporter, WorkflowResultLogger workflowResultLogger) {
        return (WorkflowStateStream) Preconditions.checkNotNull(workflowModule.providesWorkflowStateStream(deviceActionCreator, deviceActionsExecutor, deviceEventStream, workflowEventReporter, workflowResultLogger), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowStateStream mo10268get() {
        return provideInstance(this.module, this.actionControllerProvider, this.actionsExecutorProvider, this.eventStreamProvider, this.workflowEventReporterProvider, this.workflowResultLoggerProvider);
    }
}
