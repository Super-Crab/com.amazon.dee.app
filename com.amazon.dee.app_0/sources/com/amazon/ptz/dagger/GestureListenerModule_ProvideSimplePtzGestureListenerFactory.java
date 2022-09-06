package com.amazon.ptz.dagger;

import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.SimplePtzGestureListener;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureListenerModule_ProvideSimplePtzGestureListenerFactory implements Factory<SimplePtzGestureListener> {
    private final Provider<GestureHandler> gestureHandlerProvider;
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final GestureListenerModule module;

    public GestureListenerModule_ProvideSimplePtzGestureListenerFactory(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        this.module = gestureListenerModule;
        this.gestureHandlerProvider = provider;
        this.metricRecorderProvider = provider2;
    }

    public static GestureListenerModule_ProvideSimplePtzGestureListenerFactory create(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return new GestureListenerModule_ProvideSimplePtzGestureListenerFactory(gestureListenerModule, provider, provider2);
    }

    public static SimplePtzGestureListener provideInstance(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2) {
        return proxyProvideSimplePtzGestureListener(gestureListenerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static SimplePtzGestureListener proxyProvideSimplePtzGestureListener(GestureListenerModule gestureListenerModule, GestureHandler gestureHandler, MetricRecorder metricRecorder) {
        return (SimplePtzGestureListener) Preconditions.checkNotNull(gestureListenerModule.provideSimplePtzGestureListener(gestureHandler, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SimplePtzGestureListener mo10268get() {
        return provideInstance(this.module, this.gestureHandlerProvider, this.metricRecorderProvider);
    }
}
