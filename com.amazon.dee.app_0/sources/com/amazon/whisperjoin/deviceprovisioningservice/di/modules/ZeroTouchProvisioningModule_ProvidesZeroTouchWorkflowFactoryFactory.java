package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory implements Factory<ZeroTouchWorkflowFactory> {
    private final ZeroTouchProvisioningModule module;

    public ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        this.module = zeroTouchProvisioningModule;
    }

    public static ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return new ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory(zeroTouchProvisioningModule);
    }

    public static ZeroTouchWorkflowFactory provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return proxyProvidesZeroTouchWorkflowFactory(zeroTouchProvisioningModule);
    }

    public static ZeroTouchWorkflowFactory proxyProvidesZeroTouchWorkflowFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
        return (ZeroTouchWorkflowFactory) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesZeroTouchWorkflowFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ZeroTouchWorkflowFactory mo10268get() {
        return provideInstance(this.module);
    }
}
