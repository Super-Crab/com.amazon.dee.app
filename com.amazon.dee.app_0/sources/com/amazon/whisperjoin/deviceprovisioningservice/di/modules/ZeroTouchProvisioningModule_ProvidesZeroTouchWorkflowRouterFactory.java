package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory implements Factory<ZeroTouchWorkflowRouter> {
    private final Provider<FFSArcusSettings> ffsArcusSettingsProvider;
    private final ZeroTouchProvisioningModule module;
    private final Provider<WJDeviceSetupModeSupportedPredicate> wjDeviceSetupModeSupportedPredicateProvider;
    private final Provider<ZeroTouchWorkflowFactory> zeroTouchWorkflowFactoryProvider;

    public ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<ZeroTouchWorkflowFactory> provider, Provider<FFSArcusSettings> provider2, Provider<WJDeviceSetupModeSupportedPredicate> provider3) {
        this.module = zeroTouchProvisioningModule;
        this.zeroTouchWorkflowFactoryProvider = provider;
        this.ffsArcusSettingsProvider = provider2;
        this.wjDeviceSetupModeSupportedPredicateProvider = provider3;
    }

    public static ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<ZeroTouchWorkflowFactory> provider, Provider<FFSArcusSettings> provider2, Provider<WJDeviceSetupModeSupportedPredicate> provider3) {
        return new ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory(zeroTouchProvisioningModule, provider, provider2, provider3);
    }

    public static ZeroTouchWorkflowRouter provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<ZeroTouchWorkflowFactory> provider, Provider<FFSArcusSettings> provider2, Provider<WJDeviceSetupModeSupportedPredicate> provider3) {
        return proxyProvidesZeroTouchWorkflowRouter(zeroTouchProvisioningModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static ZeroTouchWorkflowRouter proxyProvidesZeroTouchWorkflowRouter(ZeroTouchProvisioningModule zeroTouchProvisioningModule, ZeroTouchWorkflowFactory zeroTouchWorkflowFactory, FFSArcusSettings fFSArcusSettings, WJDeviceSetupModeSupportedPredicate wJDeviceSetupModeSupportedPredicate) {
        return (ZeroTouchWorkflowRouter) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesZeroTouchWorkflowRouter(zeroTouchWorkflowFactory, fFSArcusSettings, wJDeviceSetupModeSupportedPredicate), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ZeroTouchWorkflowRouter mo10268get() {
        return provideInstance(this.module, this.zeroTouchWorkflowFactoryProvider, this.ffsArcusSettingsProvider, this.wjDeviceSetupModeSupportedPredicateProvider);
    }
}
