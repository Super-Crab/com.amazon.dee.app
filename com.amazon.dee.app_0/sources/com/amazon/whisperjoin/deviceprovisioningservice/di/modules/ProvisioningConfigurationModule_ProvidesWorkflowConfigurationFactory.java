package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesWorkflowConfigurationFactory implements Factory<WorkflowConfiguration> {
    private final ProvisioningConfigurationModule module;

    public ProvisioningConfigurationModule_ProvidesWorkflowConfigurationFactory(ProvisioningConfigurationModule provisioningConfigurationModule) {
        this.module = provisioningConfigurationModule;
    }

    public static ProvisioningConfigurationModule_ProvidesWorkflowConfigurationFactory create(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return new ProvisioningConfigurationModule_ProvidesWorkflowConfigurationFactory(provisioningConfigurationModule);
    }

    public static WorkflowConfiguration provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return proxyProvidesWorkflowConfiguration(provisioningConfigurationModule);
    }

    public static WorkflowConfiguration proxyProvidesWorkflowConfiguration(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return (WorkflowConfiguration) Preconditions.checkNotNull(provisioningConfigurationModule.providesWorkflowConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkflowConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
