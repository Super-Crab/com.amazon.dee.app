package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.AlexaServicesApis;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModel_Factory implements Factory<CardModel> {
    private final Provider<AlexaServicesApis> alexaServicesApisProvider;
    private final Provider<DefaultLocaleAuthority> localeAuthorityProvider;
    private final Provider<DefaultMarketplaceAuthority> marketplaceAuthorityProvider;

    public CardModel_Factory(Provider<AlexaServicesApis> provider, Provider<DefaultMarketplaceAuthority> provider2, Provider<DefaultLocaleAuthority> provider3) {
        this.alexaServicesApisProvider = provider;
        this.marketplaceAuthorityProvider = provider2;
        this.localeAuthorityProvider = provider3;
    }

    public static CardModel_Factory create(Provider<AlexaServicesApis> provider, Provider<DefaultMarketplaceAuthority> provider2, Provider<DefaultLocaleAuthority> provider3) {
        return new CardModel_Factory(provider, provider2, provider3);
    }

    public static CardModel newCardModel(AlexaServicesApis alexaServicesApis, Object obj, DefaultLocaleAuthority defaultLocaleAuthority) {
        return new CardModel(alexaServicesApis, (DefaultMarketplaceAuthority) obj, defaultLocaleAuthority);
    }

    public static CardModel provideInstance(Provider<AlexaServicesApis> provider, Provider<DefaultMarketplaceAuthority> provider2, Provider<DefaultLocaleAuthority> provider3) {
        return new CardModel(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardModel mo10268get() {
        return provideInstance(this.alexaServicesApisProvider, this.marketplaceAuthorityProvider, this.localeAuthorityProvider);
    }
}
