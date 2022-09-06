package com.amazon.alexa.sharing.util;

import dagger.internal.Factory;
/* loaded from: classes10.dex */
public final class ThemingUtil_Factory implements Factory<ThemingUtil> {
    private static final ThemingUtil_Factory INSTANCE = new ThemingUtil_Factory();

    public static ThemingUtil_Factory create() {
        return INSTANCE;
    }

    public static ThemingUtil newThemingUtil() {
        return new ThemingUtil();
    }

    public static ThemingUtil provideInstance() {
        return new ThemingUtil();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ThemingUtil mo10268get() {
        return provideInstance();
    }
}
