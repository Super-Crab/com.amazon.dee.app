package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class UiModule_ProvideAlexaLocaleAuthorityFactory implements Factory<AlexaLocaleAuthority> {
    private final Provider<LocaleInteractor> localeInteractorProvider;

    public UiModule_ProvideAlexaLocaleAuthorityFactory(Provider<LocaleInteractor> provider) {
        this.localeInteractorProvider = provider;
    }

    public static UiModule_ProvideAlexaLocaleAuthorityFactory create(Provider<LocaleInteractor> provider) {
        return new UiModule_ProvideAlexaLocaleAuthorityFactory(provider);
    }

    public static AlexaLocaleAuthority provideInstance(Provider<LocaleInteractor> provider) {
        return proxyProvideAlexaLocaleAuthority(provider.mo10268get());
    }

    public static AlexaLocaleAuthority proxyProvideAlexaLocaleAuthority(LocaleInteractor localeInteractor) {
        return (AlexaLocaleAuthority) Preconditions.checkNotNull(UiModule.provideAlexaLocaleAuthority(localeInteractor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaLocaleAuthority mo10268get() {
        return provideInstance(this.localeInteractorProvider);
    }
}
