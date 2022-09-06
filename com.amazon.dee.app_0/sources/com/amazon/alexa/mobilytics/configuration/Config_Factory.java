package com.amazon.alexa.mobilytics.configuration;

import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class Config_Factory implements Factory<Config> {
    private static final Config_Factory INSTANCE = new Config_Factory();

    public static Config_Factory create() {
        return INSTANCE;
    }

    public static Config newConfig() {
        return new Config();
    }

    public static Config provideInstance() {
        return new Config();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Config mo10268get() {
        return provideInstance();
    }
}
