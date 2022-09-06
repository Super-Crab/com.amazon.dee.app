package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesMarketplaceAuthorityFactory implements Factory<MarketplaceAuthority> {
    private final Provider<DefaultMarketplaceAuthority> marketplaceAuthorityProvider;
    private final CardModule module;

    public CardModule_ProvidesMarketplaceAuthorityFactory(CardModule cardModule, Provider<DefaultMarketplaceAuthority> provider) {
        this.module = cardModule;
        this.marketplaceAuthorityProvider = provider;
    }

    public static CardModule_ProvidesMarketplaceAuthorityFactory create(CardModule cardModule, Provider<DefaultMarketplaceAuthority> provider) {
        return new CardModule_ProvidesMarketplaceAuthorityFactory(cardModule, provider);
    }

    public static MarketplaceAuthority provideInstance(CardModule cardModule, Provider<DefaultMarketplaceAuthority> provider) {
        return proxyProvidesMarketplaceAuthority(cardModule, provider.mo10268get());
    }

    public static MarketplaceAuthority proxyProvidesMarketplaceAuthority(CardModule cardModule, Object obj) {
        return (MarketplaceAuthority) Preconditions.checkNotNull(cardModule.providesMarketplaceAuthority((DefaultMarketplaceAuthority) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MarketplaceAuthority mo10268get() {
        return provideInstance(this.module, this.marketplaceAuthorityProvider);
    }
}
