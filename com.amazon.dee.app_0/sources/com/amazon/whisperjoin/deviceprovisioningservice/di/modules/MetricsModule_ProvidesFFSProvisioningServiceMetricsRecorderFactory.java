package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MetricsModule_ProvidesFFSProvisioningServiceMetricsRecorderFactory implements Factory<FFSProvisioningServiceMetricsRecorder> {
    private final Provider<MetricsRecorderProvider> metricsRecorderProvider;
    private final MetricsModule module;

    public MetricsModule_ProvidesFFSProvisioningServiceMetricsRecorderFactory(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        this.module = metricsModule;
        this.metricsRecorderProvider = provider;
    }

    public static MetricsModule_ProvidesFFSProvisioningServiceMetricsRecorderFactory create(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        return new MetricsModule_ProvidesFFSProvisioningServiceMetricsRecorderFactory(metricsModule, provider);
    }

    public static FFSProvisioningServiceMetricsRecorder provideInstance(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        return proxyProvidesFFSProvisioningServiceMetricsRecorder(metricsModule, provider.mo10268get());
    }

    public static FFSProvisioningServiceMetricsRecorder proxyProvidesFFSProvisioningServiceMetricsRecorder(MetricsModule metricsModule, MetricsRecorderProvider metricsRecorderProvider) {
        return (FFSProvisioningServiceMetricsRecorder) Preconditions.checkNotNull(metricsModule.providesFFSProvisioningServiceMetricsRecorder(metricsRecorderProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FFSProvisioningServiceMetricsRecorder mo10268get() {
        return provideInstance(this.module, this.metricsRecorderProvider);
    }
}
