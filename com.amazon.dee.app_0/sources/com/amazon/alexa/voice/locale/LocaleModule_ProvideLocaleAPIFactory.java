package com.amazon.alexa.voice.locale;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class LocaleModule_ProvideLocaleAPIFactory implements Factory<LocaleAPI> {
    private static final LocaleModule_ProvideLocaleAPIFactory INSTANCE = new LocaleModule_ProvideLocaleAPIFactory();

    public static LocaleModule_ProvideLocaleAPIFactory create() {
        return INSTANCE;
    }

    public static LocaleAPI provideInstance() {
        return proxyProvideLocaleAPI();
    }

    public static LocaleAPI proxyProvideLocaleAPI() {
        return (LocaleAPI) Preconditions.checkNotNull(LocaleModule.provideLocaleAPI(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocaleAPI mo10268get() {
        return provideInstance();
    }
}
