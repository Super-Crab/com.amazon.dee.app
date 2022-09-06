package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideMetricsFactory implements Factory<Metrics> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideMetricsFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideMetricsFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideMetricsFactory(discoveryModule);
    }

    public static Metrics provideMetrics(DiscoveryModule discoveryModule) {
        return (Metrics) Preconditions.checkNotNull(discoveryModule.provideMetrics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Metrics mo10268get() {
        return provideMetrics(this.module);
    }
}
