package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryConfigurationModule_ProvidesScanningModeFactory implements Factory<ScanningMode> {
    private final DiscoveryConfigurationModule module;

    public DiscoveryConfigurationModule_ProvidesScanningModeFactory(DiscoveryConfigurationModule discoveryConfigurationModule) {
        this.module = discoveryConfigurationModule;
    }

    public static DiscoveryConfigurationModule_ProvidesScanningModeFactory create(DiscoveryConfigurationModule discoveryConfigurationModule) {
        return new DiscoveryConfigurationModule_ProvidesScanningModeFactory(discoveryConfigurationModule);
    }

    public static ScanningMode provideInstance(DiscoveryConfigurationModule discoveryConfigurationModule) {
        return proxyProvidesScanningMode(discoveryConfigurationModule);
    }

    public static ScanningMode proxyProvidesScanningMode(DiscoveryConfigurationModule discoveryConfigurationModule) {
        return (ScanningMode) Preconditions.checkNotNull(discoveryConfigurationModule.providesScanningMode(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ScanningMode mo10268get() {
        return provideInstance(this.module);
    }
}
