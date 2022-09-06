package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.ptz.PhysicalPtzGestureHandler;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvideGestureRouterFactory implements Factory<GestureHandler> {
    private final Provider<DigitalPtzGestureHandler> digitalHandlerProvider;
    private final Provider<PtzGestureListener> listenerProvider;
    private final PtzModule module;
    private final Provider<PhysicalPtzGestureHandler> physicalHandlerProvider;

    public PtzModule_ProvideGestureRouterFactory(PtzModule ptzModule, Provider<DigitalPtzGestureHandler> provider, Provider<PhysicalPtzGestureHandler> provider2, Provider<PtzGestureListener> provider3) {
        this.module = ptzModule;
        this.digitalHandlerProvider = provider;
        this.physicalHandlerProvider = provider2;
        this.listenerProvider = provider3;
    }

    public static PtzModule_ProvideGestureRouterFactory create(PtzModule ptzModule, Provider<DigitalPtzGestureHandler> provider, Provider<PhysicalPtzGestureHandler> provider2, Provider<PtzGestureListener> provider3) {
        return new PtzModule_ProvideGestureRouterFactory(ptzModule, provider, provider2, provider3);
    }

    public static GestureHandler provideInstance(PtzModule ptzModule, Provider<DigitalPtzGestureHandler> provider, Provider<PhysicalPtzGestureHandler> provider2, Provider<PtzGestureListener> provider3) {
        return proxyProvideGestureRouter(ptzModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static GestureHandler proxyProvideGestureRouter(PtzModule ptzModule, DigitalPtzGestureHandler digitalPtzGestureHandler, PhysicalPtzGestureHandler physicalPtzGestureHandler, PtzGestureListener ptzGestureListener) {
        return (GestureHandler) Preconditions.checkNotNull(ptzModule.provideGestureRouter(digitalPtzGestureHandler, physicalPtzGestureHandler, ptzGestureListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GestureHandler mo10268get() {
        return provideInstance(this.module, this.digitalHandlerProvider, this.physicalHandlerProvider, this.listenerProvider);
    }
}
