package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesProvisioningMethodFactory implements Factory<ProvisioningMethod> {
    private final ProvisioningModule module;

    public ProvisioningModule_ProvidesProvisioningMethodFactory(ProvisioningModule provisioningModule) {
        this.module = provisioningModule;
    }

    public static ProvisioningModule_ProvidesProvisioningMethodFactory create(ProvisioningModule provisioningModule) {
        return new ProvisioningModule_ProvidesProvisioningMethodFactory(provisioningModule);
    }

    public static ProvisioningMethod provideInstance(ProvisioningModule provisioningModule) {
        return proxyProvidesProvisioningMethod(provisioningModule);
    }

    public static ProvisioningMethod proxyProvidesProvisioningMethod(ProvisioningModule provisioningModule) {
        return (ProvisioningMethod) Preconditions.checkNotNull(provisioningModule.providesProvisioningMethod(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisioningMethod mo10268get() {
        return provideInstance(this.module);
    }
}
