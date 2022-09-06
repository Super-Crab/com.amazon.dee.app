package com.amazon.deecomms.calling.model;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class CallContext_Factory implements Factory<CallContext> {
    private static final CallContext_Factory INSTANCE = new CallContext_Factory();

    public static CallContext_Factory create() {
        return INSTANCE;
    }

    public static CallContext newCallContext() {
        return new CallContext();
    }

    public static CallContext provideInstance() {
        return new CallContext();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallContext mo10268get() {
        return provideInstance();
    }
}
