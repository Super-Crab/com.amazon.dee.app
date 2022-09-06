package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryConfigurationModule_ProvidesWJDeviceSetupModeSupportedPredicateFactory implements Factory<WJDeviceSetupModeSupportedPredicate> {
    private final DiscoveryConfigurationModule module;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<WorkflowConfiguration> workflowConfigurationProvider;

    public DiscoveryConfigurationModule_ProvidesWJDeviceSetupModeSupportedPredicateFactory(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<ProvisioningMethod> provider, Provider<WorkflowConfiguration> provider2) {
        this.module = discoveryConfigurationModule;
        this.provisioningMethodProvider = provider;
        this.workflowConfigurationProvider = provider2;
    }

    public static DiscoveryConfigurationModule_ProvidesWJDeviceSetupModeSupportedPredicateFactory create(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<ProvisioningMethod> provider, Provider<WorkflowConfiguration> provider2) {
        return new DiscoveryConfigurationModule_ProvidesWJDeviceSetupModeSupportedPredicateFactory(discoveryConfigurationModule, provider, provider2);
    }

    public static WJDeviceSetupModeSupportedPredicate provideInstance(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<ProvisioningMethod> provider, Provider<WorkflowConfiguration> provider2) {
        return proxyProvidesWJDeviceSetupModeSupportedPredicate(discoveryConfigurationModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static WJDeviceSetupModeSupportedPredicate proxyProvidesWJDeviceSetupModeSupportedPredicate(DiscoveryConfigurationModule discoveryConfigurationModule, ProvisioningMethod provisioningMethod, WorkflowConfiguration workflowConfiguration) {
        return (WJDeviceSetupModeSupportedPredicate) Preconditions.checkNotNull(discoveryConfigurationModule.providesWJDeviceSetupModeSupportedPredicate(provisioningMethod, workflowConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJDeviceSetupModeSupportedPredicate mo10268get() {
        return provideInstance(this.module, this.provisioningMethodProvider, this.workflowConfigurationProvider);
    }
}
