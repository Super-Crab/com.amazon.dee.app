package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory implements Factory<OveractiveConfiguration> {
    private final DiscoveryConfigurationModule module;

    public DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory(DiscoveryConfigurationModule discoveryConfigurationModule) {
        this.module = discoveryConfigurationModule;
    }

    public static DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory create(DiscoveryConfigurationModule discoveryConfigurationModule) {
        return new DiscoveryConfigurationModule_ProvidesOveractiveConfigurationFactory(discoveryConfigurationModule);
    }

    public static OveractiveConfiguration provideInstance(DiscoveryConfigurationModule discoveryConfigurationModule) {
        return proxyProvidesOveractiveConfiguration(discoveryConfigurationModule);
    }

    public static OveractiveConfiguration proxyProvidesOveractiveConfiguration(DiscoveryConfigurationModule discoveryConfigurationModule) {
        return (OveractiveConfiguration) Preconditions.checkNotNull(discoveryConfigurationModule.providesOveractiveConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OveractiveConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}
