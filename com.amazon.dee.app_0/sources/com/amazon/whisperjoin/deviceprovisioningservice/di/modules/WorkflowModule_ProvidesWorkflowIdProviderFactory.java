package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesWorkflowIdProviderFactory implements Factory<WorkflowIdProvider> {
    private final WorkflowModule module;

    public WorkflowModule_ProvidesWorkflowIdProviderFactory(WorkflowModule workflowModule) {
        this.module = workflowModule;
    }

    public static WorkflowModule_ProvidesWorkflowIdProviderFactory create(WorkflowModule workflowModule) {
        return new WorkflowModule_ProvidesWorkflowIdProviderFactory(workflowModule);
    }

    public static WorkflowIdProvider provideInstance(WorkflowModule workflowModule) {
        return proxyProvidesWorkflowIdProvider(workflowModule);
    }

    public static WorkflowIdProvider proxyProvidesWorkflowIdProvider(WorkflowModule workflowModule) {
        return (WorkflowIdProvider) Preconditions.checkNotNull(workflowModule.providesWorkflowIdProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowIdProvider mo10268get() {
        return provideInstance(this.module);
    }
}
