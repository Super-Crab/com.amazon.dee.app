package com.amazon.ptz.dagger;

import android.content.Context;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.ScalePtzGestureListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureListenerModule_ProvideScaleGestureDetectorFactory implements Factory<OldScaleGestureDetector> {
    private final Provider<Context> contextProvider;
    private final GestureListenerModule module;
    private final Provider<ScalePtzGestureListener> scalePtzGestureListenerProvider;

    public GestureListenerModule_ProvideScaleGestureDetectorFactory(GestureListenerModule gestureListenerModule, Provider<Context> provider, Provider<ScalePtzGestureListener> provider2) {
        this.module = gestureListenerModule;
        this.contextProvider = provider;
        this.scalePtzGestureListenerProvider = provider2;
    }

    public static GestureListenerModule_ProvideScaleGestureDetectorFactory create(GestureListenerModule gestureListenerModule, Provider<Context> provider, Provider<ScalePtzGestureListener> provider2) {
        return new GestureListenerModule_ProvideScaleGestureDetectorFactory(gestureListenerModule, provider, provider2);
    }

    public static OldScaleGestureDetector provideInstance(GestureListenerModule gestureListenerModule, Provider<Context> provider, Provider<ScalePtzGestureListener> provider2) {
        return proxyProvideScaleGestureDetector(gestureListenerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static OldScaleGestureDetector proxyProvideScaleGestureDetector(GestureListenerModule gestureListenerModule, Context context, ScalePtzGestureListener scalePtzGestureListener) {
        return (OldScaleGestureDetector) Preconditions.checkNotNull(gestureListenerModule.provideScaleGestureDetector(context, scalePtzGestureListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OldScaleGestureDetector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.scalePtzGestureListenerProvider);
    }
}
