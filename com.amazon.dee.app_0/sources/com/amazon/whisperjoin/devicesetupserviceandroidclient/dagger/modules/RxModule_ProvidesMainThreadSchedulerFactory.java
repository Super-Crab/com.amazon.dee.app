package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Scheduler;
/* loaded from: classes13.dex */
public final class RxModule_ProvidesMainThreadSchedulerFactory implements Factory<Scheduler> {
    private final RxModule module;

    public RxModule_ProvidesMainThreadSchedulerFactory(RxModule rxModule) {
        this.module = rxModule;
    }

    public static RxModule_ProvidesMainThreadSchedulerFactory create(RxModule rxModule) {
        return new RxModule_ProvidesMainThreadSchedulerFactory(rxModule);
    }

    public static Scheduler provideInstance(RxModule rxModule) {
        return proxyProvidesMainThreadScheduler(rxModule);
    }

    public static Scheduler proxyProvidesMainThreadScheduler(RxModule rxModule) {
        return (Scheduler) Preconditions.checkNotNull(rxModule.providesMainThreadScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Scheduler mo10268get() {
        return provideInstance(this.module);
    }
}
