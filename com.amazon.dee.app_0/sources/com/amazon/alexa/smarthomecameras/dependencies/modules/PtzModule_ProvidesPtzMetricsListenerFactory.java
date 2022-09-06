package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.metrics.PtzMetricsListener;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesPtzMetricsListenerFactory implements Factory<PtzMetricsListener> {
    private final Provider<CamerasMobilyticsService> metricsServiceProvider;
    private final PtzModule module;

    public PtzModule_ProvidesPtzMetricsListenerFactory(PtzModule ptzModule, Provider<CamerasMobilyticsService> provider) {
        this.module = ptzModule;
        this.metricsServiceProvider = provider;
    }

    public static PtzModule_ProvidesPtzMetricsListenerFactory create(PtzModule ptzModule, Provider<CamerasMobilyticsService> provider) {
        return new PtzModule_ProvidesPtzMetricsListenerFactory(ptzModule, provider);
    }

    public static PtzMetricsListener provideInstance(PtzModule ptzModule, Provider<CamerasMobilyticsService> provider) {
        return proxyProvidesPtzMetricsListener(ptzModule, provider.mo10268get());
    }

    public static PtzMetricsListener proxyProvidesPtzMetricsListener(PtzModule ptzModule, CamerasMobilyticsService camerasMobilyticsService) {
        return (PtzMetricsListener) Preconditions.checkNotNull(ptzModule.providesPtzMetricsListener(camerasMobilyticsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PtzMetricsListener mo10268get() {
        return provideInstance(this.module, this.metricsServiceProvider);
    }
}
