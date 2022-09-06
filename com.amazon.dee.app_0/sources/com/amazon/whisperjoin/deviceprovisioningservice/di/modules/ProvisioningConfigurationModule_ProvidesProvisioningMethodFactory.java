package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningConfigurationModule_ProvidesProvisioningMethodFactory implements Factory<ProvisioningMethod> {
    private final ProvisioningConfigurationModule module;

    public ProvisioningConfigurationModule_ProvidesProvisioningMethodFactory(ProvisioningConfigurationModule provisioningConfigurationModule) {
        this.module = provisioningConfigurationModule;
    }

    public static ProvisioningConfigurationModule_ProvidesProvisioningMethodFactory create(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return new ProvisioningConfigurationModule_ProvidesProvisioningMethodFactory(provisioningConfigurationModule);
    }

    public static ProvisioningMethod provideInstance(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return proxyProvidesProvisioningMethod(provisioningConfigurationModule);
    }

    public static ProvisioningMethod proxyProvidesProvisioningMethod(ProvisioningConfigurationModule provisioningConfigurationModule) {
        return (ProvisioningMethod) Preconditions.checkNotNull(provisioningConfigurationModule.providesProvisioningMethod(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisioningMethod mo10268get() {
        return provideInstance(this.module);
    }
}
