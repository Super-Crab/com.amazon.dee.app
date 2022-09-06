package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesScaleGestureDetectorFactory implements Factory<OldScaleGestureDetector> {
    private final Provider<Context> contextProvider;
    private final PtzModule module;
    private final Provider<ScalePtzGestureListener> scaleGestureListenerProvider;

    public PtzModule_ProvidesScaleGestureDetectorFactory(PtzModule ptzModule, Provider<Context> provider, Provider<ScalePtzGestureListener> provider2) {
        this.module = ptzModule;
        this.contextProvider = provider;
        this.scaleGestureListenerProvider = provider2;
    }

    public static PtzModule_ProvidesScaleGestureDetectorFactory create(PtzModule ptzModule, Provider<Context> provider, Provider<ScalePtzGestureListener> provider2) {
        return new PtzModule_ProvidesScaleGestureDetectorFactory(ptzModule, provider, provider2);
    }

    public static OldScaleGestureDetector provideInstance(PtzModule ptzModule, Provider<Context> provider, Provider<ScalePtzGestureListener> provider2) {
        return proxyProvidesScaleGestureDetector(ptzModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static OldScaleGestureDetector proxyProvidesScaleGestureDetector(PtzModule ptzModule, Context context, ScalePtzGestureListener scalePtzGestureListener) {
        return (OldScaleGestureDetector) Preconditions.checkNotNull(ptzModule.providesScaleGestureDetector(context, scalePtzGestureListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OldScaleGestureDetector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.scaleGestureListenerProvider);
    }
}
