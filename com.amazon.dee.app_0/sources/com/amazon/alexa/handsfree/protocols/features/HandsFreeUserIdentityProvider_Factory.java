package com.amazon.alexa.handsfree.protocols.features;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class HandsFreeUserIdentityProvider_Factory implements Factory<HandsFreeUserIdentityProvider> {
    private static final HandsFreeUserIdentityProvider_Factory INSTANCE = new HandsFreeUserIdentityProvider_Factory();

    public static HandsFreeUserIdentityProvider_Factory create() {
        return INSTANCE;
    }

    public static HandsFreeUserIdentityProvider newHandsFreeUserIdentityProvider() {
        return new HandsFreeUserIdentityProvider();
    }

    public static HandsFreeUserIdentityProvider provideInstance() {
        return new HandsFreeUserIdentityProvider();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeUserIdentityProvider mo10268get() {
        return provideInstance();
    }
}
