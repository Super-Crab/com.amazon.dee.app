package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideMediaStoreUtilFactory implements Factory<MediaStoreUtil> {
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideMediaStoreUtilFactory(DiscoveryModule discoveryModule, Provider<Logger> provider, Provider<Metrics> provider2) {
        this.module = discoveryModule;
        this.loggerProvider = provider;
        this.metricsProvider = provider2;
    }

    public static DiscoveryModule_ProvideMediaStoreUtilFactory create(DiscoveryModule discoveryModule, Provider<Logger> provider, Provider<Metrics> provider2) {
        return new DiscoveryModule_ProvideMediaStoreUtilFactory(discoveryModule, provider, provider2);
    }

    public static MediaStoreUtil provideMediaStoreUtil(DiscoveryModule discoveryModule, Logger logger, Metrics metrics) {
        return (MediaStoreUtil) Preconditions.checkNotNull(discoveryModule.provideMediaStoreUtil(logger, metrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MediaStoreUtil mo10268get() {
        return provideMediaStoreUtil(this.module, this.loggerProvider.mo10268get(), this.metricsProvider.mo10268get());
    }
}
