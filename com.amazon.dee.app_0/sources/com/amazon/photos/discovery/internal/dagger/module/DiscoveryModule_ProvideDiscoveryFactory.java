package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.Discovery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideDiscoveryFactory implements Factory<Discovery> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideDiscoveryFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideDiscoveryFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideDiscoveryFactory(discoveryModule);
    }

    public static Discovery provideDiscovery(DiscoveryModule discoveryModule) {
        return (Discovery) Preconditions.checkNotNull(discoveryModule.provideDiscovery(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Discovery mo10268get() {
        return provideDiscovery(this.module);
    }
}
