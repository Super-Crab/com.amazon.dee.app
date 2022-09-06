package com.amazon.dee.app.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.Scheduler;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideDiskSchedulerFactory implements Factory<Scheduler> {
    private final ServiceModule module;

    public ServiceModule_ProvideDiskSchedulerFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideDiskSchedulerFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideDiskSchedulerFactory(serviceModule);
    }

    public static Scheduler provideInstance(ServiceModule serviceModule) {
        return proxyProvideDiskScheduler(serviceModule);
    }

    public static Scheduler proxyProvideDiskScheduler(ServiceModule serviceModule) {
        return (Scheduler) Preconditions.checkNotNull(serviceModule.provideDiskScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Scheduler mo10268get() {
        return provideInstance(this.module);
    }
}
