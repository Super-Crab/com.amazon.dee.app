package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class UtilModule_ProvidesClockFactory implements Factory<Clock> {
    private final UtilModule module;

    public UtilModule_ProvidesClockFactory(UtilModule utilModule) {
        this.module = utilModule;
    }

    public static UtilModule_ProvidesClockFactory create(UtilModule utilModule) {
        return new UtilModule_ProvidesClockFactory(utilModule);
    }

    public static Clock provideInstance(UtilModule utilModule) {
        return proxyProvidesClock(utilModule);
    }

    public static Clock proxyProvidesClock(UtilModule utilModule) {
        return (Clock) Preconditions.checkNotNull(utilModule.providesClock(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Clock mo10268get() {
        return provideInstance(this.module);
    }
}
