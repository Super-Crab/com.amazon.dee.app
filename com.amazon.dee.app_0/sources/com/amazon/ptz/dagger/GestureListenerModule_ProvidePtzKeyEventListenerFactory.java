package com.amazon.ptz.dagger;

import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.PtzKeyEventListener;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureListenerModule_ProvidePtzKeyEventListenerFactory implements Factory<PtzKeyEventListener> {
    private final Provider<GestureHandler> gestureHandlerProvider;
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final GestureListenerModule module;
    private final Provider<DigitalZoomState> zoomStateProvider;

    public GestureListenerModule_ProvidePtzKeyEventListenerFactory(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2, Provider<DigitalZoomState> provider3) {
        this.module = gestureListenerModule;
        this.gestureHandlerProvider = provider;
        this.metricRecorderProvider = provider2;
        this.zoomStateProvider = provider3;
    }

    public static GestureListenerModule_ProvidePtzKeyEventListenerFactory create(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2, Provider<DigitalZoomState> provider3) {
        return new GestureListenerModule_ProvidePtzKeyEventListenerFactory(gestureListenerModule, provider, provider2, provider3);
    }

    public static PtzKeyEventListener provideInstance(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<MetricRecorder> provider2, Provider<DigitalZoomState> provider3) {
        return proxyProvidePtzKeyEventListener(gestureListenerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static PtzKeyEventListener proxyProvidePtzKeyEventListener(GestureListenerModule gestureListenerModule, GestureHandler gestureHandler, MetricRecorder metricRecorder, DigitalZoomState digitalZoomState) {
        return (PtzKeyEventListener) Preconditions.checkNotNull(gestureListenerModule.providePtzKeyEventListener(gestureHandler, metricRecorder, digitalZoomState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PtzKeyEventListener mo10268get() {
        return provideInstance(this.module, this.gestureHandlerProvider, this.metricRecorderProvider, this.zoomStateProvider);
    }
}
