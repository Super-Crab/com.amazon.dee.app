package com.amazon.deecomms.calling.impl;

import com.amazon.deecomms.calling.model.CallContext;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallingAPIPopulator_Factory implements Factory<CallingAPIPopulator> {
    private final Provider<CallContext> callContextProvider;

    public CallingAPIPopulator_Factory(Provider<CallContext> provider) {
        this.callContextProvider = provider;
    }

    public static CallingAPIPopulator_Factory create(Provider<CallContext> provider) {
        return new CallingAPIPopulator_Factory(provider);
    }

    public static CallingAPIPopulator newCallingAPIPopulator(CallContext callContext) {
        return new CallingAPIPopulator(callContext);
    }

    public static CallingAPIPopulator provideInstance(Provider<CallContext> provider) {
        return new CallingAPIPopulator(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallingAPIPopulator mo10268get() {
        return provideInstance(this.callContextProvider);
    }
}
