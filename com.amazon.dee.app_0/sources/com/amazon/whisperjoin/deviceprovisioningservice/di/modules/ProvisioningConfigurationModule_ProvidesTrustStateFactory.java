package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesTrustStateFactory implements Factory<TrustProvider.TrustState> {
    private final ProvisioningConfigurationModule module;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<WorkflowConfiguration> workflowConfigurationProvider;

    public ProvisioningConfigurationModule_ProvidesTrustStateFactory(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<WorkflowConfiguration> provider, Provider<ProvisioningMethod> provider2) {
        this.module = provisioningConfigurationModule;
        this.workflowConfigurationProvider = provider;
        this.provisioningMethodProvider = provider2;
    }

    public static ProvisioningConfigurationModule_ProvidesTrustStateFactory create(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<WorkflowConfiguration> provider, Provider<ProvisioningMethod> provider2) {
        return new ProvisioningConfigurationModule_ProvidesTrustStateFactory(provisioningConfigurationModule, provider, provider2);
    }

    public static TrustProvider.TrustState provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule, Provider<WorkflowConfiguration> provider, Provider<ProvisioningMethod> provider2) {
        return proxyProvidesTrustState(provisioningConfigurationModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static TrustProvider.TrustState proxyProvidesTrustState(ProvisioningConfigurationModule provisioningConfigurationModule, WorkflowConfiguration workflowConfiguration, ProvisioningMethod provisioningMethod) {
        return (TrustProvider.TrustState) Preconditions.checkNotNull(provisioningConfigurationModule.providesTrustState(workflowConfiguration, provisioningMethod), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TrustProvider.TrustState mo10268get() {
        return provideInstance(this.module, this.workflowConfigurationProvider, this.provisioningMethodProvider);
    }
}
