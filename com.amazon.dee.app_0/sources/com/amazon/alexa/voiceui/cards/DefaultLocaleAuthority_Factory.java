package com.amazon.alexa.voiceui.cards;

import dagger.internal.Factory;
/* loaded from: classes11.dex */
public final class DefaultLocaleAuthority_Factory implements Factory<DefaultLocaleAuthority> {
    private static final DefaultLocaleAuthority_Factory INSTANCE = new DefaultLocaleAuthority_Factory();

    public static DefaultLocaleAuthority_Factory create() {
        return INSTANCE;
    }

    public static DefaultLocaleAuthority newDefaultLocaleAuthority() {
        return new DefaultLocaleAuthority();
    }

    public static DefaultLocaleAuthority provideInstance() {
        return new DefaultLocaleAuthority();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultLocaleAuthority mo10268get() {
        return provideInstance();
    }
}
