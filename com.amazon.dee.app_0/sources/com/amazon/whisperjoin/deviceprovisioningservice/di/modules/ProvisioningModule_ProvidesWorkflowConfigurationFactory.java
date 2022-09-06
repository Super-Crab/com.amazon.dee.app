package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesWorkflowConfigurationFactory implements Factory<WorkflowConfiguration> {
    private final ProvisioningModule module;

    public ProvisioningModule_ProvidesWorkflowConfigurationFactory(ProvisioningModule provisioningModule) {
        this.module = provisioningModule;
    }

    public static ProvisioningModule_ProvidesWorkflowConfigurationFactory create(ProvisioningModule provisioningModule) {
        return new ProvisioningModule_ProvidesWorkflowConfigurationFactory(provisioningModule);
    }

    public static WorkflowConfiguration provideInstance(ProvisioningModule provisioningModule) {
        return proxyProvidesWorkflowConfiguration(provisioningModule);
    }

    public static WorkflowConfiguration proxyProvidesWorkflowConfiguration(ProvisioningModule provisioningModule) {
        return (WorkflowConfiguration) Preconditions.checkNotNull(provisioningModule.providesWorkflowConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
