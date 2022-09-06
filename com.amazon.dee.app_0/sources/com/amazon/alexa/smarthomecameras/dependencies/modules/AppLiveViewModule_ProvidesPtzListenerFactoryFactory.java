package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesPtzListenerFactoryFactory implements Factory<PtzListenerFactory> {
    private static final AppLiveViewModule_ProvidesPtzListenerFactoryFactory INSTANCE = new AppLiveViewModule_ProvidesPtzListenerFactoryFactory();

    public static AppLiveViewModule_ProvidesPtzListenerFactoryFactory create() {
        return INSTANCE;
    }

    public static PtzListenerFactory provideInstance() {
        return proxyProvidesPtzListenerFactory();
    }

    public static PtzListenerFactory proxyProvidesPtzListenerFactory() {
        return (PtzListenerFactory) Preconditions.checkNotNull(AppLiveViewModule.providesPtzListenerFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PtzListenerFactory mo10268get() {
        return provideInstance();
    }
}
