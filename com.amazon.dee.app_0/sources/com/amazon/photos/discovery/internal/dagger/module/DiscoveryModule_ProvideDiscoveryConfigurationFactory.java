package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.DiscoveryConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideDiscoveryConfigurationFactory implements Factory<DiscoveryConfiguration> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideDiscoveryConfigurationFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideDiscoveryConfigurationFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideDiscoveryConfigurationFactory(discoveryModule);
    }

    public static DiscoveryConfiguration provideDiscoveryConfiguration(DiscoveryModule discoveryModule) {
        return (DiscoveryConfiguration) Preconditions.checkNotNull(discoveryModule.provideDiscoveryConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DiscoveryConfiguration mo10268get() {
        return provideDiscoveryConfiguration(this.module);
    }
}
