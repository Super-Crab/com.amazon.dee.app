package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.internal.util.NodeUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideNodeUtilsFactory implements Factory<NodeUtils> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideNodeUtilsFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideNodeUtilsFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideNodeUtilsFactory(discoveryModule);
    }

    public static NodeUtils provideNodeUtils(DiscoveryModule discoveryModule) {
        return (NodeUtils) Preconditions.checkNotNull(discoveryModule.provideNodeUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NodeUtils mo10268get() {
        return provideNodeUtils(this.module);
    }
}
