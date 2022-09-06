package com.amazon.ptz.dagger;

import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureListenerModule_ProvideScalePtzGestureListenerFactory implements Factory<ScalePtzGestureListener> {
    private final Provider<GestureHandler> gestureHandlerProvider;
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final GestureListenerModule module;

    public GestureListenerModule_ProvideScalePtzGestureListenerFactory(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        this.module = gestureListenerModule;
        this.gestureHandlerProvider = provider;
        this.metricRecorderProvider = provider2;
    }

    public static GestureListenerModule_ProvideScalePtzGestureListenerFactory create(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return new GestureListenerModule_ProvideScalePtzGestureListenerFactory(gestureListenerModule, provider, provider2);
    }

    public static ScalePtzGestureListener provideInstance(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return proxyProvideScalePtzGestureListener(gestureListenerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static ScalePtzGestureListener proxyProvideScalePtzGestureListener(GestureListenerModule gestureListenerModule, GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return (ScalePtzGestureListener) Preconditions.checkNotNull(gestureListenerModule.provideScalePtzGestureListener(gestureHandler, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ScalePtzGestureListener mo10268get() {
        return provideInstance(this.module, this.gestureHandlerProvider, this.metricRecorderProvider);
    }
}
