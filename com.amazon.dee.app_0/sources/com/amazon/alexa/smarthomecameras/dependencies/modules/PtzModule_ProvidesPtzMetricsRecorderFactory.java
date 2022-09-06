package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesPtzMetricsRecorderFactory implements Factory<MetricRecorder> {
    private final Provider<CamerasMobilyticsService> metricsServiceProvider;
    private final PtzModule module;

    public PtzModule_ProvidesPtzMetricsRecorderFactory(PtzModule ptzModule, Provider<CamerasMobilyticsService> provider) {
        this.module = ptzModule;
        this.metricsServiceProvider = provider;
    }

    public static PtzModule_ProvidesPtzMetricsRecorderFactory create(PtzModule ptzModule, Provider<CamerasMobilyticsService> provider) {
        return new PtzModule_ProvidesPtzMetricsRecorderFactory(ptzModule, provider);
    }

    public static MetricRecorder provideInstance(PtzModule ptzModule, Provider<CamerasMobilyticsService> provider) {
        return proxyProvidesPtzMetricsRecorder(ptzModule, provider.mo10268get());
    }

    public static MetricRecorder proxyProvidesPtzMetricsRecorder(PtzModule ptzModule, CamerasMobilyticsService camerasMobilyticsService) {
        return (MetricRecorder) Preconditions.checkNotNull(ptzModule.providesPtzMetricsRecorder(camerasMobilyticsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricRecorder mo10268get() {
        return provideInstance(this.module, this.metricsServiceProvider);
    }
}
