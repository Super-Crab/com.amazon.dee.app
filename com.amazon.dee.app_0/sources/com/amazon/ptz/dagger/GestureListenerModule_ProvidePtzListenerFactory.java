package com.amazon.ptz.dagger;

import android.content.Context;
import android.view.GestureDetector;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.gestures.listeners.OldScaleGestureDetector;
import com.amazon.ptz.gestures.listeners.PtzListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureListenerModule_ProvidePtzListenerFactory implements Factory<PtzListener> {
    private final Provider<Context> contextProvider;
    private final Provider<GestureDetector> gestureDetectorProvider;
    private final Provider<GestureHandler> gestureHandlerProvider;
    private final GestureListenerModule module;
    private final Provider<OldScaleGestureDetector> scaleGestureDetectorProvider;

    public GestureListenerModule_ProvidePtzListenerFactory(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<GestureDetector> provider2, Provider<OldScaleGestureDetector> provider3, Provider<Context> provider4) {
        this.module = gestureListenerModule;
        this.gestureHandlerProvider = provider;
        this.gestureDetectorProvider = provider2;
        this.scaleGestureDetectorProvider = provider3;
        this.contextProvider = provider4;
    }

    public static GestureListenerModule_ProvidePtzListenerFactory create(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<GestureDetector> provider2, Provider<OldScaleGestureDetector> provider3, Provider<Context> provider4) {
        return new GestureListenerModule_ProvidePtzListenerFactory(gestureListenerModule, provider, provider2, provider3, provider4);
    }

    public static PtzListener provideInstance(GestureListenerModule gestureListenerModule, Provider<GestureHandler> provider, Provider<GestureDetector> provider2, Provider<OldScaleGestureDetector> provider3, Provider<Context> provider4) {
        return proxyProvidePtzListener(gestureListenerModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static PtzListener proxyProvidePtzListener(GestureListenerModule gestureListenerModule, GestureHandler gestureHandler, GestureDetector gestureDetector, OldScaleGestureDetector oldScaleGestureDetector, Context context) {
        return (PtzListener) Preconditions.checkNotNull(gestureListenerModule.providePtzListener(gestureHandler, gestureDetector, oldScaleGestureDetector, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PtzListener mo10268get() {
        return provideInstance(this.module, this.gestureHandlerProvider, this.gestureDetectorProvider, this.scaleGestureDetectorProvider, this.contextProvider);
    }
}
