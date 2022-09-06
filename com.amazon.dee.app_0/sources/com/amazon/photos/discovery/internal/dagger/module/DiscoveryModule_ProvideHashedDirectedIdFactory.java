package com.amazon.photos.discovery.internal.dagger.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideHashedDirectedIdFactory implements Factory<String> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideHashedDirectedIdFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideHashedDirectedIdFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideHashedDirectedIdFactory(discoveryModule);
    }

    public static String provideHashedDirectedId(DiscoveryModule discoveryModule) {
        return (String) Preconditions.checkNotNull(discoveryModule.provideHashedDirectedId(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideHashedDirectedId(this.module);
    }
}
