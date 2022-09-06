package com.amazon.ptz.dagger;

import android.view.View;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.digital.DigitalZoomState;
import com.amazon.ptz.metrics.MetricRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureHandlerModule_ProvideDigitalPtzGestureHandlerFactory implements Factory<DigitalPtzGestureHandler> {
    private final Provider<MetricRecorder> metricRecorderProvider;
    private final GestureHandlerModule module;
    private final Provider<View> viewProvider;
    private final Provider<DigitalZoomState> zoomStateProvider;

    public GestureHandlerModule_ProvideDigitalPtzGestureHandlerFactory(GestureHandlerModule gestureHandlerModule, Provider<View> provider, Provider<DigitalZoomState> provider2, Provider<MetricRecorder> provider3) {
        this.module = gestureHandlerModule;
        this.viewProvider = provider;
        this.zoomStateProvider = provider2;
        this.metricRecorderProvider = provider3;
    }

    public static GestureHandlerModule_ProvideDigitalPtzGestureHandlerFactory create(GestureHandlerModule gestureHandlerModule, Provider<View> provider, Provider<DigitalZoomState> provider2, Provider<MetricRecorder> provider3) {
        return new GestureHandlerModule_ProvideDigitalPtzGestureHandlerFactory(gestureHandlerModule, provider, provider2, provider3);
    }

    public static DigitalPtzGestureHandler provideInstance(GestureHandlerModule gestureHandlerModule, Provider<View> provider, Provider<DigitalZoomState> provider2, Provider<MetricRecorder> provider3) {
        return proxyProvideDigitalPtzGestureHandler(gestureHandlerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static DigitalPtzGestureHandler proxyProvideDigitalPtzGestureHandler(GestureHandlerModule gestureHandlerModule, View view, DigitalZoomState digitalZoomState, MetricRecorder metricRecorder) {
        return (DigitalPtzGestureHandler) Preconditions.checkNotNull(gestureHandlerModule.provideDigitalPtzGestureHandler(view, digitalZoomState, metricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DigitalPtzGestureHandler mo10268get() {
        return provideInstance(this.module, this.viewProvider, this.zoomStateProvider, this.metricRecorderProvider);
    }
}
