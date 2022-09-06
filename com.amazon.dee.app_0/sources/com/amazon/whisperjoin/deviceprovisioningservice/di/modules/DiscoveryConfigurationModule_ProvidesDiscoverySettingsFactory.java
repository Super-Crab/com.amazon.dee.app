package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryConfigurationModule_ProvidesDiscoverySettingsFactory implements Factory<DiscoverySettings> {
    private final DiscoveryConfigurationModule module;
    private final Provider<ProvisioningMethod> provisioningMethodProvider;
    private final Provider<ScanningMode> scanningModeProvider;
    private final Provider<WorkflowConfiguration> workflowConfigurationProvider;

    public DiscoveryConfigurationModule_ProvidesDiscoverySettingsFactory(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<WorkflowConfiguration> provider, Provider<ScanningMode> provider2, Provider<ProvisioningMethod> provider3) {
        this.module = discoveryConfigurationModule;
        this.workflowConfigurationProvider = provider;
        this.scanningModeProvider = provider2;
        this.provisioningMethodProvider = provider3;
    }

    public static DiscoveryConfigurationModule_ProvidesDiscoverySettingsFactory create(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<WorkflowConfiguration> provider, Provider<ScanningMode> provider2, Provider<ProvisioningMethod> provider3) {
        return new DiscoveryConfigurationModule_ProvidesDiscoverySettingsFactory(discoveryConfigurationModule, provider, provider2, provider3);
    }

    public static DiscoverySettings provideInstance(DiscoveryConfigurationModule discoveryConfigurationModule, Provider<WorkflowConfiguration> provider, Provider<ScanningMode> provider2, Provider<ProvisioningMethod> provider3) {
        return proxyProvidesDiscoverySettings(discoveryConfigurationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static DiscoverySettings proxyProvidesDiscoverySettings(DiscoveryConfigurationModule discoveryConfigurationModule, WorkflowConfiguration workflowConfiguration, ScanningMode scanningMode, ProvisioningMethod provisioningMethod) {
        return (DiscoverySettings) Preconditions.checkNotNull(discoveryConfigurationModule.providesDiscoverySettings(workflowConfiguration, scanningMode, provisioningMethod), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DiscoverySettings mo10268get() {
        return provideInstance(this.module, this.workflowConfigurationProvider, this.scanningModeProvider, this.provisioningMethodProvider);
    }
}
