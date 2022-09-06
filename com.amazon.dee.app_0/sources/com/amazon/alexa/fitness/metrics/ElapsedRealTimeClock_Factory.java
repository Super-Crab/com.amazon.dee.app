package com.amazon.alexa.fitness.metrics;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class ElapsedRealTimeClock_Factory implements Factory<ElapsedRealTimeClock> {
    private static final ElapsedRealTimeClock_Factory INSTANCE = new ElapsedRealTimeClock_Factory();

    public static ElapsedRealTimeClock_Factory create() {
        return INSTANCE;
    }

    public static ElapsedRealTimeClock newElapsedRealTimeClock() {
        return new ElapsedRealTimeClock();
    }

    public static ElapsedRealTimeClock provideInstance() {
        return new ElapsedRealTimeClock();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ElapsedRealTimeClock mo10268get() {
        return provideInstance();
    }
}
