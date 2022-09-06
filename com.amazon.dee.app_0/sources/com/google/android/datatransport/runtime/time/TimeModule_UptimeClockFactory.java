package com.google.android.datatransport.runtime.time;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class TimeModule_UptimeClockFactory implements Factory<Clock> {
    private static final TimeModule_UptimeClockFactory INSTANCE = new TimeModule_UptimeClockFactory();

    public static TimeModule_UptimeClockFactory create() {
        return INSTANCE;
    }

    public static Clock uptimeClock() {
        return (Clock) Preconditions.checkNotNull(TimeModule.uptimeClock(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Clock mo10268get() {
        return uptimeClock();
    }
}
