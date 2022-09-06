package com.amazon.alexa.redesign.dependency;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.Scheduler;
/* loaded from: classes10.dex */
public final class CacheModule_ProvideHomeDiskSchedulerFactory implements Factory<Scheduler> {
    private final CacheModule module;

    public CacheModule_ProvideHomeDiskSchedulerFactory(CacheModule cacheModule) {
        this.module = cacheModule;
    }

    public static CacheModule_ProvideHomeDiskSchedulerFactory create(CacheModule cacheModule) {
        return new CacheModule_ProvideHomeDiskSchedulerFactory(cacheModule);
    }

    public static Scheduler provideInstance(CacheModule cacheModule) {
        return proxyProvideHomeDiskScheduler(cacheModule);
    }

    public static Scheduler proxyProvideHomeDiskScheduler(CacheModule cacheModule) {
        return (Scheduler) Preconditions.checkNotNull(cacheModule.provideHomeDiskScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Scheduler mo10268get() {
        return provideInstance(this.module);
    }
}
