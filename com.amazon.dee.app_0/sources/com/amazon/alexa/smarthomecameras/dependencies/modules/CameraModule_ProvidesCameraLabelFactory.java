package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class CameraModule_ProvidesCameraLabelFactory implements Factory<CameraLabel> {
    private final CameraModule module;

    public CameraModule_ProvidesCameraLabelFactory(CameraModule cameraModule) {
        this.module = cameraModule;
    }

    public static CameraModule_ProvidesCameraLabelFactory create(CameraModule cameraModule) {
        return new CameraModule_ProvidesCameraLabelFactory(cameraModule);
    }

    public static CameraLabel provideInstance(CameraModule cameraModule) {
        return proxyProvidesCameraLabel(cameraModule);
    }

    public static CameraLabel proxyProvidesCameraLabel(CameraModule cameraModule) {
        return (CameraLabel) Preconditions.checkNotNull(cameraModule.providesCameraLabel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CameraLabel mo10268get() {
        return provideInstance(this.module);
    }
}
