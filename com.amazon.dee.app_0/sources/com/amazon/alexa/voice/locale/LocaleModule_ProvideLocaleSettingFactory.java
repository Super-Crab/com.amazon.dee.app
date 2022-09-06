package com.amazon.alexa.voice.locale;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class LocaleModule_ProvideLocaleSettingFactory implements Factory<LocalePreference> {
    private final Provider<Context> applicationContextProvider;

    public LocaleModule_ProvideLocaleSettingFactory(Provider<Context> provider) {
        this.applicationContextProvider = provider;
    }

    public static LocaleModule_ProvideLocaleSettingFactory create(Provider<Context> provider) {
        return new LocaleModule_ProvideLocaleSettingFactory(provider);
    }

    public static LocalePreference provideInstance(Provider<Context> provider) {
        return proxyProvideLocaleSetting(provider.mo10268get());
    }

    public static LocalePreference proxyProvideLocaleSetting(Context context) {
        return (LocalePreference) Preconditions.checkNotNull(LocaleModule.provideLocaleSetting(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocalePreference mo10268get() {
        return provideInstance(this.applicationContextProvider);
    }
}
