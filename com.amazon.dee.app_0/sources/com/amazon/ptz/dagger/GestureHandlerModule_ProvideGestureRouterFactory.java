package com.amazon.ptz.dagger;

import com.amazon.ptz.digital.DigitalPtzGestureHandler;
import com.amazon.ptz.gestures.handlers.GestureHandler;
import com.amazon.ptz.physical.PhysicalPtzGestureHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class GestureHandlerModule_ProvideGestureRouterFactory implements Factory<GestureHandler> {
    private final Provider<DigitalPtzGestureHandler> digitalPtzHandlerProvider;
    private final GestureHandlerModule module;
    private final Provider<PhysicalPtzGestureHandler> physicalPtzHandlerProvider;

    public GestureHandlerModule_ProvideGestureRouterFactory(GestureHandlerModule gestureHandlerModule, Provider<DigitalPtzGestureHandler> provider, Provider<PhysicalPtzGestureHandler> provider2) {
        this.module = gestureHandlerModule;
        this.digitalPtzHandlerProvider = provider;
        this.physicalPtzHandlerProvider = provider2;
    }

    public static GestureHandlerModule_ProvideGestureRouterFactory create(GestureHandlerModule gestureHandlerModule, Provider<DigitalPtzGestureHandler> provider, Provider<PhysicalPtzGestureHandler> provider2) {
        return new GestureHandlerModule_ProvideGestureRouterFactory(gestureHandlerModule, provider, provider2);
    }

    public static GestureHandler provideInstance(GestureHandlerModule gestureHandlerModule, Provider<DigitalPtzGestureHandler> provider, Provider<PhysicalPtzGestureHandler> provider2) {
        return proxyProvideGestureRouter(gestureHandlerModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static GestureHandler proxyProvideGestureRouter(GestureHandlerModule gestureHandlerModule, DigitalPtzGestureHandler digitalPtzGestureHandler, PhysicalPtzGestureHandler physicalPtzGestureHandler) {
        return (GestureHandler) Preconditions.checkNotNull(gestureHandlerModule.provideGestureRouter(digitalPtzGestureHandler, physicalPtzGestureHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GestureHandler mo10268get() {
        return provideInstance(this.module, this.digitalPtzHandlerProvider, this.physicalPtzHandlerProvider);
    }
}
