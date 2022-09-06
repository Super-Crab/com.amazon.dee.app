package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class PtzModule_ProvidesDevicePayloadFactory implements Factory<DevicePayload> {
    private final PtzModule module;

    public PtzModule_ProvidesDevicePayloadFactory(PtzModule ptzModule) {
        this.module = ptzModule;
    }

    public static PtzModule_ProvidesDevicePayloadFactory create(PtzModule ptzModule) {
        return new PtzModule_ProvidesDevicePayloadFactory(ptzModule);
    }

    public static DevicePayload provideInstance(PtzModule ptzModule) {
        return proxyProvidesDevicePayload(ptzModule);
    }

    public static DevicePayload proxyProvidesDevicePayload(PtzModule ptzModule) {
        return (DevicePayload) Preconditions.checkNotNull(ptzModule.providesDevicePayload(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DevicePayload mo10268get() {
        return provideInstance(this.module);
    }
}
