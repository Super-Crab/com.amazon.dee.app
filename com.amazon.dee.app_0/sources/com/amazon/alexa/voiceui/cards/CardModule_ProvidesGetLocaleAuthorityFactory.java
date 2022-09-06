package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesGetLocaleAuthorityFactory implements Factory<GetLocaleAuthority> {
    private final Provider<DefaultLocaleAuthority> localeAuthorityProvider;
    private final CardModule module;

    public CardModule_ProvidesGetLocaleAuthorityFactory(CardModule cardModule, Provider<DefaultLocaleAuthority> provider) {
        this.module = cardModule;
        this.localeAuthorityProvider = provider;
    }

    public static CardModule_ProvidesGetLocaleAuthorityFactory create(CardModule cardModule, Provider<DefaultLocaleAuthority> provider) {
        return new CardModule_ProvidesGetLocaleAuthorityFactory(cardModule, provider);
    }

    public static GetLocaleAuthority provideInstance(CardModule cardModule, Provider<DefaultLocaleAuthority> provider) {
        return proxyProvidesGetLocaleAuthority(cardModule, provider.mo10268get());
    }

    public static GetLocaleAuthority proxyProvidesGetLocaleAuthority(CardModule cardModule, DefaultLocaleAuthority defaultLocaleAuthority) {
        return (GetLocaleAuthority) Preconditions.checkNotNull(cardModule.providesGetLocaleAuthority(defaultLocaleAuthority), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public GetLocaleAuthority mo10268get() {
        return provideInstance(this.module, this.localeAuthorityProvider);
    }
}
