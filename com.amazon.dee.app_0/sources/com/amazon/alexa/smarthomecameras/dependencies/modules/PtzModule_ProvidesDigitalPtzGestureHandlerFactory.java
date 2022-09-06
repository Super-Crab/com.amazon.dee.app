package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.view.View;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesDigitalPtzGestureHandlerFactory implements Factory<DigitalPtzGestureHandler> {
    private final Provider<MetricRecorder> metricsRecorderProvider;
    private final PtzModule module;
    private final Provider<DigitalZoomState> stateProvider;
    private final Provider<View> viewProvider;

    public PtzModule_ProvidesDigitalPtzGestureHandlerFactory(PtzModule ptzModule, Provider<View> provider, Provider<DigitalZoomState> provider2, Provider<MetricRecorder> provider3) {
        this.module = ptzModule;
        this.viewProvider = provider;
        this.stateProvider = provider2;
        this.metricsRecorderProvider = provider3;
    }

    public static PtzModule_ProvidesDigitalPtzGestureHandlerFactory create(PtzModule ptzModule, Provider<View> provider, Provider<DigitalZoomState> provider2, Provider<MetricRecorder> provider3) {
        return new PtzModule_ProvidesDigitalPtzGestureHandlerFactory(ptzModule, provider, provider2, provider3);
    }

    public static DigitalPtzGestureHandler provideInstance(PtzModule ptzModule, Provider<View> provider, Provider<DigitalZoomState> provider2, Provider<MetricRecorder> provider3) {
        return proxyProvidesDigitalPtzGestureHandler(ptzModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static DigitalPtzGestureHandler proxyProvidesDigitalPtzGestureHandler(PtzModule ptzModule, View view, DigitalZoomState digitalZoomState, MetricRecorder metricRecorder) {
        return (DigitalPtzGestureHandler) Preconditions.checkNotNull(ptzModule.providesDigitalPtzGestureHandler(view, digitalZoomState, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DigitalPtzGestureHandler mo10268get() {
        return provideInstance(this.module, this.viewProvider, this.stateProvider, this.metricsRecorderProvider);
    }
}
