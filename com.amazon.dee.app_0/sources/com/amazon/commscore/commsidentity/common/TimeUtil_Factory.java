package com.amazon.commscore.commsidentity.common;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class TimeUtil_Factory implements Factory<TimeUtil> {
    private static final TimeUtil_Factory INSTANCE = new TimeUtil_Factory();

    public static TimeUtil_Factory create() {
        return INSTANCE;
    }

    public static TimeUtil newTimeUtil() {
        return new TimeUtil();
    }

    public static TimeUtil provideInstance() {
        return new TimeUtil();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeUtil mo10268get() {
        return provideInstance();
    }
}
