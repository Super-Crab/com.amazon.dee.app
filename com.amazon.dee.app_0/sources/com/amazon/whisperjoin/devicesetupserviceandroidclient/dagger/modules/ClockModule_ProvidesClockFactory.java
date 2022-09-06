package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ClockModule_ProvidesClockFactory implements Factory<Clock> {
    private final ClockModule module;

    public ClockModule_ProvidesClockFactory(ClockModule clockModule) {
        this.module = clockModule;
    }

    public static ClockModule_ProvidesClockFactory create(ClockModule clockModule) {
        return new ClockModule_ProvidesClockFactory(clockModule);
    }

    public static Clock provideInstance(ClockModule clockModule) {
        return proxyProvidesClock(clockModule);
    }

    public static Clock proxyProvidesClock(ClockModule clockModule) {
        return (Clock) Preconditions.checkNotNull(clockModule.providesClock(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Clock mo10268get() {
        return provideInstance(this.module);
    }
}
