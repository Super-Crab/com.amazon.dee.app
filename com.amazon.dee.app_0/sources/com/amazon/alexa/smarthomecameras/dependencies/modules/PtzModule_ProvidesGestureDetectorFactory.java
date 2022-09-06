package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import android.view.GestureDetector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesGestureDetectorFactory implements Factory<GestureDetector> {
    private final Provider<Context> contextProvider;
    private final PtzModule module;
    private final Provider<GestureDetector.SimpleOnGestureListener> simpleGestureListenerProvider;

    public PtzModule_ProvidesGestureDetectorFactory(PtzModule ptzModule, Provider<Context> provider, Provider<GestureDetector.SimpleOnGestureListener> provider2) {
        this.module = ptzModule;
        this.contextProvider = provider;
        this.simpleGestureListenerProvider = provider2;
    }

    public static PtzModule_ProvidesGestureDetectorFactory create(PtzModule ptzModule, Provider<Context> provider, Provider<GestureDetector.SimpleOnGestureListener> provider2) {
        return new PtzModule_ProvidesGestureDetectorFactory(ptzModule, provider, provider2);
    }

    public static GestureDetector provideInstance(PtzModule ptzModule, Provider<Context> provider, Provider<GestureDetector.SimpleOnGestureListener> provider2) {
        return proxyProvidesGestureDetector(ptzModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static GestureDetector proxyProvidesGestureDetector(PtzModule ptzModule, Context context, GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        return (GestureDetector) Preconditions.checkNotNull(ptzModule.providesGestureDetector(context, simpleOnGestureListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GestureDetector mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.simpleGestureListenerProvider);
    }
}
