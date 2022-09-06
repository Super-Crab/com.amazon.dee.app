package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ServiceModule_ProvideSchedulerFactory implements Factory<SchedulerProvider> {
    private final ServiceModule module;

    public ServiceModule_ProvideSchedulerFactory(ServiceModule serviceModule) {
        this.module = serviceModule;
    }

    public static ServiceModule_ProvideSchedulerFactory create(ServiceModule serviceModule) {
        return new ServiceModule_ProvideSchedulerFactory(serviceModule);
    }

    public static SchedulerProvider provideInstance(ServiceModule serviceModule) {
        return proxyProvideScheduler(serviceModule);
    }

    public static SchedulerProvider proxyProvideScheduler(ServiceModule serviceModule) {
        return (SchedulerProvider) Preconditions.checkNotNull(serviceModule.provideScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SchedulerProvider mo10268get() {
        return provideInstance(this.module);
    }
}
