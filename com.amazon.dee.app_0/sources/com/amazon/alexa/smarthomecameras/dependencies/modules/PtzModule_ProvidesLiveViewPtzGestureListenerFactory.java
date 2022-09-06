package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.metrics.PtzMetricsListener;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.ptz.PtzGestureListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesLiveViewPtzGestureListenerFactory implements Factory<PtzGestureListener> {
    private final Provider<DevicePayload> devicePayloadProvider;
    private final Provider<PtzMetricsListener> listenerProvider;
    private final PtzModule module;

    public PtzModule_ProvidesLiveViewPtzGestureListenerFactory(PtzModule ptzModule, Provider<PtzMetricsListener> provider, Provider<DevicePayload> provider2) {
        this.module = ptzModule;
        this.listenerProvider = provider;
        this.devicePayloadProvider = provider2;
    }

    public static PtzModule_ProvidesLiveViewPtzGestureListenerFactory create(PtzModule ptzModule, Provider<PtzMetricsListener> provider, Provider<DevicePayload> provider2) {
        return new PtzModule_ProvidesLiveViewPtzGestureListenerFactory(ptzModule, provider, provider2);
    }

    public static PtzGestureListener provideInstance(PtzModule ptzModule, Provider<PtzMetricsListener> provider, Provider<DevicePayload> provider2) {
        return proxyProvidesLiveViewPtzGestureListener(ptzModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static PtzGestureListener proxyProvidesLiveViewPtzGestureListener(PtzModule ptzModule, PtzMetricsListener ptzMetricsListener, DevicePayload devicePayload) {
        return (PtzGestureListener) Preconditions.checkNotNull(ptzModule.providesLiveViewPtzGestureListener(ptzMetricsListener, devicePayload), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PtzGestureListener mo10268get() {
        return provideInstance(this.module, this.listenerProvider, this.devicePayloadProvider);
    }
}
