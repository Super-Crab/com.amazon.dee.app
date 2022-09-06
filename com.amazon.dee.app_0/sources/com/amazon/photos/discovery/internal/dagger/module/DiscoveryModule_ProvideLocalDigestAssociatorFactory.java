package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dedupe.digest.LocalDigestAssociator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideLocalDigestAssociatorFactory implements Factory<LocalDigestAssociator> {
    private final Provider<Metrics> metricsProvider;
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideLocalDigestAssociatorFactory(DiscoveryModule discoveryModule, Provider<Metrics> provider) {
        this.module = discoveryModule;
        this.metricsProvider = provider;
    }

    public static DiscoveryModule_ProvideLocalDigestAssociatorFactory create(DiscoveryModule discoveryModule, Provider<Metrics> provider) {
        return new DiscoveryModule_ProvideLocalDigestAssociatorFactory(discoveryModule, provider);
    }

    public static LocalDigestAssociator provideLocalDigestAssociator(DiscoveryModule discoveryModule, Metrics metrics) {
        return (LocalDigestAssociator) Preconditions.checkNotNull(discoveryModule.provideLocalDigestAssociator(metrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocalDigestAssociator mo10268get() {
        return provideLocalDigestAssociator(this.module, this.metricsProvider.mo10268get());
    }
}
