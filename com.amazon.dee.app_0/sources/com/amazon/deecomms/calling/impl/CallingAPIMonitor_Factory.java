package com.amazon.deecomms.calling.impl;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallingAPIMonitor_Factory implements Factory<CallingAPIMonitor> {
    private final Provider<CallingAPIPopulator> populatorProvider;

    public CallingAPIMonitor_Factory(Provider<CallingAPIPopulator> provider) {
        this.populatorProvider = provider;
    }

    public static CallingAPIMonitor_Factory create(Provider<CallingAPIPopulator> provider) {
        return new CallingAPIMonitor_Factory(provider);
    }

    public static CallingAPIMonitor newCallingAPIMonitor(CallingAPIPopulator callingAPIPopulator) {
        return new CallingAPIMonitor(callingAPIPopulator);
    }

    public static CallingAPIMonitor provideInstance(Provider<CallingAPIPopulator> provider) {
        return new CallingAPIMonitor(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallingAPIMonitor mo10268get() {
        return provideInstance(this.populatorProvider);
    }
}
