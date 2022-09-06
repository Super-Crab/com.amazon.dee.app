package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.SmartHomeProvisioningEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesSmartHomeProvisioningEventReporterFactory implements Factory<SmartHomeProvisioningEventReporter> {
    private final Provider<DSSClient> dssClientProvider;
    private final WorkflowModule module;
    private final Provider<ProvisionerInfo> provisionerInfoProvider;
    private final Provider<WJErrorMapper<Throwable>> wjErrorMapperProvider;

    public WorkflowModule_ProvidesSmartHomeProvisioningEventReporterFactory(WorkflowModule workflowModule, Provider<ProvisionerInfo> provider, Provider<WJErrorMapper<Throwable>> provider2, Provider<DSSClient> provider3) {
        this.module = workflowModule;
        this.provisionerInfoProvider = provider;
        this.wjErrorMapperProvider = provider2;
        this.dssClientProvider = provider3;
    }

    public static WorkflowModule_ProvidesSmartHomeProvisioningEventReporterFactory create(WorkflowModule workflowModule, Provider<ProvisionerInfo> provider, Provider<WJErrorMapper<Throwable>> provider2, Provider<DSSClient> provider3) {
        return new WorkflowModule_ProvidesSmartHomeProvisioningEventReporterFactory(workflowModule, provider, provider2, provider3);
    }

    public static SmartHomeProvisioningEventReporter provideInstance(WorkflowModule workflowModule, Provider<ProvisionerInfo> provider, Provider<WJErrorMapper<Throwable>> provider2, Provider<DSSClient> provider3) {
        return proxyProvidesSmartHomeProvisioningEventReporter(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static SmartHomeProvisioningEventReporter proxyProvidesSmartHomeProvisioningEventReporter(WorkflowModule workflowModule, ProvisionerInfo provisionerInfo, WJErrorMapper<Throwable> wJErrorMapper, DSSClient dSSClient) {
        return (SmartHomeProvisioningEventReporter) Preconditions.checkNotNull(workflowModule.providesSmartHomeProvisioningEventReporter(provisionerInfo, wJErrorMapper, dSSClient), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SmartHomeProvisioningEventReporter mo10268get() {
        return provideInstance(this.module, this.provisionerInfoProvider, this.wjErrorMapperProvider, this.dssClientProvider);
    }
}
