package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MetricsModule_ProvidesCredentialSyncMetricsRecorderFactory implements Factory<CredentialSyncMetricsRecorder> {
    private final Provider<MetricsRecorderProvider> metricsRecorderProvider;
    private final MetricsModule module;

    public MetricsModule_ProvidesCredentialSyncMetricsRecorderFactory(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        this.module = metricsModule;
        this.metricsRecorderProvider = provider;
    }

    public static MetricsModule_ProvidesCredentialSyncMetricsRecorderFactory create(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        return new MetricsModule_ProvidesCredentialSyncMetricsRecorderFactory(metricsModule, provider);
    }

    public static CredentialSyncMetricsRecorder provideInstance(MetricsModule metricsModule, Provider<MetricsRecorderProvider> provider) {
        return proxyProvidesCredentialSyncMetricsRecorder(metricsModule, provider.mo10268get());
    }

    public static CredentialSyncMetricsRecorder proxyProvidesCredentialSyncMetricsRecorder(MetricsModule metricsModule, MetricsRecorderProvider metricsRecorderProvider) {
        return (CredentialSyncMetricsRecorder) Preconditions.checkNotNull(metricsModule.providesCredentialSyncMetricsRecorder(metricsRecorderProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CredentialSyncMetricsRecorder mo10268get() {
        return provideInstance(this.module, this.metricsRecorderProvider);
    }
}
