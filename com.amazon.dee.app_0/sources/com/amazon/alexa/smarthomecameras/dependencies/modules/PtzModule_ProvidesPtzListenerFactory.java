package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import android.view.GestureDetector;
import com.amazon.alexa.smarthomecameras.handlers.LiveViewPtzListener;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesPtzListenerFactory implements Factory<LiveViewPtzListener> {
    private final Provider<Context> contextProvider;
    private final Provider<GestureDetector> gestureDetectorProvider;
    private final Provider<PtzGestureListener> listenerProvider;
    private final PtzModule module;
    private final Provider<OldScaleGestureDetector> oldScaleGestureDetectorProvider;
    private final Provider<GestureHandler> routerProvider;

    public PtzModule_ProvidesPtzListenerFactory(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<GestureDetector> provider2, Provider<OldScaleGestureDetector> provider3, Provider<Context> provider4, Provider<PtzGestureListener> provider5) {
        this.module = ptzModule;
        this.routerProvider = provider;
        this.gestureDetectorProvider = provider2;
        this.oldScaleGestureDetectorProvider = provider3;
        this.contextProvider = provider4;
        this.listenerProvider = provider5;
    }

    public static PtzModule_ProvidesPtzListenerFactory create(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<GestureDetector> provider2, Provider<OldScaleGestureDetector> provider3, Provider<Context> provider4, Provider<PtzGestureListener> provider5) {
        return new PtzModule_ProvidesPtzListenerFactory(ptzModule, provider, provider2, provider3, provider4, provider5);
    }

    public static LiveViewPtzListener provideInstance(PtzModule ptzModule, Provider<GestureHandler> provider, Provider<GestureDetector> provider2, Provider<OldScaleGestureDetector> provider3, Provider<Context> provider4, Provider<PtzGestureListener> provider5) {
        return proxyProvidesPtzListener(ptzModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static LiveViewPtzListener proxyProvidesPtzListener(PtzModule ptzModule, GestureHandler gestureHandler, GestureDetector gestureDetector, OldScaleGestureDetector oldScaleGestureDetector, Context context, PtzGestureListener ptzGestureListener) {
        return (LiveViewPtzListener) Preconditions.checkNotNull(ptzModule.providesPtzListener(gestureHandler, gestureDetector, oldScaleGestureDetector, context, ptzGestureListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LiveViewPtzListener mo10268get() {
        return provideInstance(this.module, this.routerProvider, this.gestureDetectorProvider, this.oldScaleGestureDetectorProvider, this.contextProvider, this.listenerProvider);
    }
}
