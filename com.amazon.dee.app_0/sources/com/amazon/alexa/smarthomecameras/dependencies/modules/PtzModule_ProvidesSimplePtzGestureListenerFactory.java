package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.view.GestureDetector;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesSimplePtzGestureListenerFactory implements Factory<GestureDetector.SimpleOnGestureListener> {
    private final Provider<GestureHandler> gestureHandlerProvider;
    private final Provider<MetricRecorder> metricsRecorderProvider;
    private final PtzModule module;

    public PtzModule_ProvidesSimplePtzGestureListenerFactory(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        this.module = ptzModule;
        this.gestureHandlerProvider = provider;
        this.metricsRecorderProvider = provider2;
    }

    public static PtzModule_ProvidesSimplePtzGestureListenerFactory create(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return new PtzModule_ProvidesSimplePtzGestureListenerFactory(ptzModule, provider, provider2);
    }

    public static GestureDetector.SimpleOnGestureListener provideInstance(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return proxyProvidesSimplePtzGestureListener(ptzModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static GestureDetector.SimpleOnGestureListener proxyProvidesSimplePtzGestureListener(PtzModule ptzModule, GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return (GestureDetector.SimpleOnGestureListener) Preconditions.checkNotNull(ptzModule.providesSimplePtzGestureListener(gestureHandler, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GestureDetector.SimpleOnGestureListener mo10268get() {
        return provideInstance(this.module, this.gestureHandlerProvider, this.metricsRecorderProvider);
    }
}
