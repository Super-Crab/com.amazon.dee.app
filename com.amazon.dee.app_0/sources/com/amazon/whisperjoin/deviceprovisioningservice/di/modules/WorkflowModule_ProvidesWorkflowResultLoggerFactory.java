package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowResultLogger;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesWorkflowResultLoggerFactory implements Factory<WorkflowResultLogger> {
    private final WorkflowModule module;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<WJErrorMapper<Throwable>> wjErrorMapperProvider;

    public WorkflowModule_ProvidesWorkflowResultLoggerFactory(WorkflowModule workflowModule, Provider<ProvisioningMethod> provider, Provider<WJErrorMapper<Throwable>> provider2) {
        this.module = workflowModule;
        this.provisioningMethodProvider = provider;
        this.wjErrorMapperProvider = provider2;
    }

    public static WorkflowModule_ProvidesWorkflowResultLoggerFactory create(WorkflowModule workflowModule, Provider<ProvisioningMethod> provider, Provider<WJErrorMapper<Throwable>> provider2) {
        return new WorkflowModule_ProvidesWorkflowResultLoggerFactory(workflowModule, provider, provider2);
    }

    public static WorkflowResultLogger provideInstance(WorkflowModule workflowModule, Provider<ProvisioningMethod> provider, Provider<WJErrorMapper<Throwable>> provider2) {
        return proxyProvidesWorkflowResultLogger(workflowModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WorkflowResultLogger proxyProvidesWorkflowResultLogger(WorkflowModule workflowModule, ProvisioningMethod provisioningMethod, WJErrorMapper<Throwable> wJErrorMapper) {
        return (WorkflowResultLogger) Preconditions.checkNotNull(workflowModule.providesWorkflowResultLogger(provisioningMethod, wJErrorMapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowResultLogger mo10268get() {
        return provideInstance(this.module, this.provisioningMethodProvider, this.wjErrorMapperProvider);
    }
}
