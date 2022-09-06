package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.CameraViewContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesModelFactory implements Factory<CameraViewContract.Model> {
    private static final AppLiveViewModule_ProvidesModelFactory INSTANCE = new AppLiveViewModule_ProvidesModelFactory();

    public static AppLiveViewModule_ProvidesModelFactory create() {
        return INSTANCE;
    }

    public static CameraViewContract.Model provideInstance() {
        return proxyProvidesModel();
    }

    public static CameraViewContract.Model proxyProvidesModel() {
        return (CameraViewContract.Model) Preconditions.checkNotNull(AppLiveViewModule.providesModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CameraViewContract.Model mo10268get() {
        return provideInstance();
    }
}
