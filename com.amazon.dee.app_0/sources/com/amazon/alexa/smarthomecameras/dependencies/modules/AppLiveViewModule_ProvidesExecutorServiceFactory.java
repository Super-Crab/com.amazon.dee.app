package com.amazon.alexa.smarthomecameras.dependencies.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesExecutorServiceFactory implements Factory<ScheduledExecutorService> {
    private static final AppLiveViewModule_ProvidesExecutorServiceFactory INSTANCE = new AppLiveViewModule_ProvidesExecutorServiceFactory();

    public static AppLiveViewModule_ProvidesExecutorServiceFactory create() {
        return INSTANCE;
    }

    public static ScheduledExecutorService provideInstance() {
        return proxyProvidesExecutorService();
    }

    public static ScheduledExecutorService proxyProvidesExecutorService() {
        return (ScheduledExecutorService) Preconditions.checkNotNull(AppLiveViewModule.providesExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public ScheduledExecutorService mo10268get() {
        return provideInstance();
    }
}
