package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesWorkflowEventReporterFactory implements Factory<WorkflowEventReporter> {
    private final Provider<DSSClient> dssClientProvider;
    private final WorkflowModule module;
    private final Provider<ProvisionerInfo> provisionerInfoProvider;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<WJErrorMapper<Throwable>> wjErrorMapperProvider;

    public WorkflowModule_ProvidesWorkflowEventReporterFactory(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisionerInfo> provider2, Provider<ProvisioningMethod> provider3, Provider<WJErrorMapper<Throwable>> provider4) {
        this.module = workflowModule;
        this.dssClientProvider = provider;
        this.provisionerInfoProvider = provider2;
        this.provisioningMethodProvider = provider3;
        this.wjErrorMapperProvider = provider4;
    }

    public static WorkflowModule_ProvidesWorkflowEventReporterFactory create(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisionerInfo> provider2, Provider<ProvisioningMethod> provider3, Provider<WJErrorMapper<Throwable>> provider4) {
        return new WorkflowModule_ProvidesWorkflowEventReporterFactory(workflowModule, provider, provider2, provider3, provider4);
    }

    public static WorkflowEventReporter provideInstance(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisionerInfo> provider2, Provider<ProvisioningMethod> provider3, Provider<WJErrorMapper<Throwable>> provider4) {
        return proxyProvidesWorkflowEventReporter(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static WorkflowEventReporter proxyProvidesWorkflowEventReporter(WorkflowModule workflowModule, DSSClient dSSClient, ProvisionerInfo provisionerInfo, ProvisioningMethod provisioningMethod, WJErrorMapper<Throwable> wJErrorMapper) {
        return (WorkflowEventReporter) Preconditions.checkNotNull(workflowModule.providesWorkflowEventReporter(dSSClient, provisionerInfo, provisioningMethod, wJErrorMapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowEventReporter mo10268get() {
        return provideInstance(this.module, this.dssClientProvider, this.provisionerInfoProvider, this.provisioningMethodProvider, this.wjErrorMapperProvider);
    }
}
