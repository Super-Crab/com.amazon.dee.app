package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dedupe.digest.CloudDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.server.ServiceApi;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideCloudDigestAssociatorFactory implements Factory<CloudDigestAssociator> {
    private final Provider<DedupeFilter> dedupeFilterProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final DiscoveryModule module;
    private final Provider<NodeUtils> nodeUtilsProvider;
    private final Provider<ServiceApi> serviceApiProvider;

    public DiscoveryModule_ProvideCloudDigestAssociatorFactory(DiscoveryModule discoveryModule, Provider<ServiceApi> provider, Provider<Logger> provider2, Provider<Metrics> provider3, Provider<NodeUtils> provider4, Provider<DedupeFilter> provider5) {
        this.module = discoveryModule;
        this.serviceApiProvider = provider;
        this.loggerProvider = provider2;
        this.metricsProvider = provider3;
        this.nodeUtilsProvider = provider4;
        this.dedupeFilterProvider = provider5;
    }

    public static DiscoveryModule_ProvideCloudDigestAssociatorFactory create(DiscoveryModule discoveryModule, Provider<ServiceApi> provider, Provider<Logger> provider2, Provider<Metrics> provider3, Provider<NodeUtils> provider4, Provider<DedupeFilter> provider5) {
        return new DiscoveryModule_ProvideCloudDigestAssociatorFactory(discoveryModule, provider, provider2, provider3, provider4, provider5);
    }

    public static CloudDigestAssociator provideCloudDigestAssociator(DiscoveryModule discoveryModule, ServiceApi serviceApi, Logger logger, Metrics metrics, NodeUtils nodeUtils, DedupeFilter dedupeFilter) {
        return (CloudDigestAssociator) Preconditions.checkNotNull(discoveryModule.provideCloudDigestAssociator(serviceApi, logger, metrics, nodeUtils, dedupeFilter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CloudDigestAssociator mo10268get() {
        return provideCloudDigestAssociator(this.module, this.serviceApiProvider.mo10268get(), this.loggerProvider.mo10268get(), this.metricsProvider.mo10268get(), this.nodeUtilsProvider.mo10268get(), this.dedupeFilterProvider.mo10268get());
    }
}
