package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class CameraModule_ProvidesDevicePayloadFactory implements Factory<DevicePayload> {
    private final CameraModule module;

    public CameraModule_ProvidesDevicePayloadFactory(CameraModule cameraModule) {
        this.module = cameraModule;
    }

    public static CameraModule_ProvidesDevicePayloadFactory create(CameraModule cameraModule) {
        return new CameraModule_ProvidesDevicePayloadFactory(cameraModule);
    }

    public static DevicePayload provideInstance(CameraModule cameraModule) {
        return proxyProvidesDevicePayload(cameraModule);
    }

    public static DevicePayload proxyProvidesDevicePayload(CameraModule cameraModule) {
        return (DevicePayload) Preconditions.checkNotNull(cameraModule.providesDevicePayload(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DevicePayload mo10268get() {
        return provideInstance(this.module);
    }
}
