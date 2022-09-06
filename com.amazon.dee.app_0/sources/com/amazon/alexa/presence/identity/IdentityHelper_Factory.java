package com.amazon.alexa.presence.identity;

import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class IdentityHelper_Factory implements Factory<IdentityHelper> {
    private static final IdentityHelper_Factory INSTANCE = new IdentityHelper_Factory();

    public static IdentityHelper_Factory create() {
        return INSTANCE;
    }

    public static IdentityHelper newIdentityHelper() {
        return new IdentityHelper();
    }

    public static IdentityHelper provideInstance() {
        return new IdentityHelper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityHelper mo10268get() {
        return provideInstance();
    }
}
