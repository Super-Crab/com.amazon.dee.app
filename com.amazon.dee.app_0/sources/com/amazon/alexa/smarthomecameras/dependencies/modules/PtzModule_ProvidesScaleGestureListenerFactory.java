package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesScaleGestureListenerFactory implements Factory<ScalePtzGestureListener> {
    private final Provider<GestureHandler> gestureHandlerProvider;
    private final Provider<MetricRecorder> metricsRecorderProvider;
    private final PtzModule module;

    public PtzModule_ProvidesScaleGestureListenerFactory(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        this.module = ptzModule;
        this.gestureHandlerProvider = provider;
        this.metricsRecorderProvider = provider2;
    }

    public static PtzModule_ProvidesScaleGestureListenerFactory create(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return new PtzModule_ProvidesScaleGestureListenerFactory(ptzModule, provider, provider2);
    }

    public static ScalePtzGestureListener provideInstance(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return proxyProvidesScaleGestureListener(ptzModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ScalePtzGestureListener proxyProvidesScaleGestureListener(PtzModule ptzModule, GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return (ScalePtzGestureListener) Preconditions.checkNotNull(ptzModule.providesScaleGestureListener(gestureHandler, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ScalePtzGestureListener mo10268get() {
        return provideInstance(this.module, this.gestureHandlerProvider, this.metricsRecorderProvider);
    }
}
