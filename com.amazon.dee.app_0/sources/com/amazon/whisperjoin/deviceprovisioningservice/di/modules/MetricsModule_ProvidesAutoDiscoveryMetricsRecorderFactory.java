package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MetricsModule_ProvidesAutoDiscoveryMetricsRecorderFactory implements Factory<AutoDiscoveryMetricsRecorder> {
    private final Provider<MetricsRecorderProvider> metricsRecorderProvider;
    private final MetricsModule module;

    public MetricsModule_ProvidesAutoDiscoveryMetricsRecorderFactory(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        this.module = metricsModule;
        this.metricsRecorderProvider = provider;
    }

    public static MetricsModule_ProvidesAutoDiscoveryMetricsRecorderFactory create(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        return new MetricsModule_ProvidesAutoDiscoveryMetricsRecorderFactory(metricsModule, provider);
    }

    public static AutoDiscoveryMetricsRecorder provideInstance(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        return proxyProvidesAutoDiscoveryMetricsRecorder(metricsModule, provider.mo10268get());
    }

    public static AutoDiscoveryMetricsRecorder proxyProvidesAutoDiscoveryMetricsRecorder(MetricsModule metricsModule, MetricsRecorderProvider metricsRecorderProvider) {
        return (AutoDiscoveryMetricsRecorder) Preconditions.checkNotNull(metricsModule.providesAutoDiscoveryMetricsRecorder(metricsRecorderProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AutoDiscoveryMetricsRecorder mo10268get() {
        return provideInstance(this.module, this.metricsRecorderProvider);
    }
}
