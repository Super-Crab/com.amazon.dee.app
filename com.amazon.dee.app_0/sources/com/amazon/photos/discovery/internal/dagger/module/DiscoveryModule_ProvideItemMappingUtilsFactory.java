package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMappingUtils;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideItemMappingUtilsFactory implements Factory<ItemMappingUtils> {
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final DiscoveryModule module;
    private final Provider<NodeUtils> nodeUtilsProvider;

    public DiscoveryModule_ProvideItemMappingUtilsFactory(DiscoveryModule discoveryModule, Provider<Metrics> provider, Provider<Logger> provider2, Provider<NodeUtils> provider3) {
        this.module = discoveryModule;
        this.metricsProvider = provider;
        this.loggerProvider = provider2;
        this.nodeUtilsProvider = provider3;
    }

    public static DiscoveryModule_ProvideItemMappingUtilsFactory create(DiscoveryModule discoveryModule, Provider<Metrics> provider, Provider<Logger> provider2, Provider<NodeUtils> provider3) {
        return new DiscoveryModule_ProvideItemMappingUtilsFactory(discoveryModule, provider, provider2, provider3);
    }

    public static ItemMappingUtils provideItemMappingUtils(DiscoveryModule discoveryModule, Metrics metrics, Logger logger, NodeUtils nodeUtils) {
        return (ItemMappingUtils) Preconditions.checkNotNull(discoveryModule.provideItemMappingUtils(metrics, logger, nodeUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ItemMappingUtils mo10268get() {
        return provideItemMappingUtils(this.module, this.metricsProvider.mo10268get(), this.loggerProvider.mo10268get(), this.nodeUtilsProvider.mo10268get());
    }
}
