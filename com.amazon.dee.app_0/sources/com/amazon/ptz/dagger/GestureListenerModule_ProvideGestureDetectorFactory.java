package com.amazon.ptz.dagger;

import android.content.Context;
import android.view.GestureDetector;
import com.amazon.ptz.gestures.listeners.SimplePtzGestureListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureListenerModule_ProvideGestureDetectorFactory implements Factory<GestureDetector> {
    private final Provider<Context> contextProvider;
    private final GestureListenerModule module;
    private final Provider<SimplePtzGestureListener> simplePtzGestureListenerProvider;

    public GestureListenerModule_ProvideGestureDetectorFactory(GestureListenerModule gestureListenerModule, Provider<Context> provider, Provider<SimplePtzGestureListener> provider2) {
        this.module = gestureListenerModule;
        this.contextProvider = provider;
        this.simplePtzGestureListenerProvider = provider2;
    }

    public static GestureListenerModule_ProvideGestureDetectorFactory create(GestureListenerModule gestureListenerModule, Provider<Context> provider, Provider<SimplePtzGestureListener> provider2) {
        return new GestureListenerModule_ProvideGestureDetectorFactory(gestureListenerModule, provider, provider2);
    }

    public static GestureDetector provideInstance(GestureListenerModule gestureListenerModule, Provider<Context> provider, Provider<SimplePtzGestureListener> provider2) {
        return proxyProvideGestureDetector(gestureListenerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static GestureDetector proxyProvideGestureDetector(GestureListenerModule gestureListenerModule, Context context, SimplePtzGestureListener simplePtzGestureListener) {
        return (GestureDetector) Preconditions.checkNotNull(gestureListenerModule.provideGestureDetector(context, simplePtzGestureListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GestureDetector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.simplePtzGestureListenerProvider);
    }
}
