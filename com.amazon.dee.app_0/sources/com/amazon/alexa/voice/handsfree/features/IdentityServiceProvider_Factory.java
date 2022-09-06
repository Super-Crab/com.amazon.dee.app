package com.amazon.alexa.voice.handsfree.features;

import dagger.internal.Factory;
/* loaded from: classes11.dex */
public final class IdentityServiceProvider_Factory implements Factory<IdentityServiceProvider> {
    private static final IdentityServiceProvider_Factory INSTANCE = new IdentityServiceProvider_Factory();

    public static IdentityServiceProvider_Factory create() {
        return INSTANCE;
    }

    public static IdentityServiceProvider newIdentityServiceProvider() {
        return new IdentityServiceProvider();
    }

    public static IdentityServiceProvider provideInstance() {
        return new IdentityServiceProvider();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityServiceProvider mo10268get() {
        return provideInstance();
    }
}
