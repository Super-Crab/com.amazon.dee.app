package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesAppLifecycleOwnerFactory implements Factory<AppLifecycleOwner> {
    private static final AppLiveViewModule_ProvidesAppLifecycleOwnerFactory INSTANCE = new AppLiveViewModule_ProvidesAppLifecycleOwnerFactory();

    public static AppLiveViewModule_ProvidesAppLifecycleOwnerFactory create() {
        return INSTANCE;
    }

    public static AppLifecycleOwner provideInstance() {
        return proxyProvidesAppLifecycleOwner();
    }

    public static AppLifecycleOwner proxyProvidesAppLifecycleOwner() {
        return (AppLifecycleOwner) Preconditions.checkNotNull(AppLiveViewModule.providesAppLifecycleOwner(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AppLifecycleOwner mo10268get() {
        return provideInstance();
    }
}
