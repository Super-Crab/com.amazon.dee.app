package com.amazon.alexa.voice.handsfree.features;

import dagger.internal.Factory;
/* loaded from: classes11.dex */
public final class AmokSignInNotifier_Factory implements Factory<AmokSignInNotifier> {
    private static final AmokSignInNotifier_Factory INSTANCE = new AmokSignInNotifier_Factory();

    public static AmokSignInNotifier_Factory create() {
        return INSTANCE;
    }

    public static AmokSignInNotifier newAmokSignInNotifier() {
        return new AmokSignInNotifier();
    }

    public static AmokSignInNotifier provideInstance() {
        return new AmokSignInNotifier();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AmokSignInNotifier mo10268get() {
        return provideInstance();
    }
}
