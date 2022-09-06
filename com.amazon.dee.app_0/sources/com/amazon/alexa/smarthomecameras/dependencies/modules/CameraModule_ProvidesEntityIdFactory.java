package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.model.EntityId;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class CameraModule_ProvidesEntityIdFactory implements Factory<EntityId> {
    private final CameraModule module;

    public CameraModule_ProvidesEntityIdFactory(CameraModule cameraModule) {
        this.module = cameraModule;
    }

    public static CameraModule_ProvidesEntityIdFactory create(CameraModule cameraModule) {
        return new CameraModule_ProvidesEntityIdFactory(cameraModule);
    }

    public static EntityId provideInstance(CameraModule cameraModule) {
        return proxyProvidesEntityId(cameraModule);
    }

    public static EntityId proxyProvidesEntityId(CameraModule cameraModule) {
        return (EntityId) Preconditions.checkNotNull(cameraModule.providesEntityId(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntityId mo10268get() {
        return provideInstance(this.module);
    }
}
