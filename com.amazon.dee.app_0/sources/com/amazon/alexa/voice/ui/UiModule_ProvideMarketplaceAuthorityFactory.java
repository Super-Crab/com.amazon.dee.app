package com.amazon.alexa.voice.ui;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class UiModule_ProvideMarketplaceAuthorityFactory implements Factory<MarketplaceAuthority> {
    private final Provider<IdentityService> identityServiceProvider;

    public UiModule_ProvideMarketplaceAuthorityFactory(Provider<IdentityService> provider) {
        this.identityServiceProvider = provider;
    }

    public static UiModule_ProvideMarketplaceAuthorityFactory create(Provider<IdentityService> provider) {
        return new UiModule_ProvideMarketplaceAuthorityFactory(provider);
    }

    public static MarketplaceAuthority provideInstance(Provider<IdentityService> provider) {
        return proxyProvideMarketplaceAuthority(provider.mo10268get());
    }

    public static MarketplaceAuthority proxyProvideMarketplaceAuthority(IdentityService identityService) {
        return (MarketplaceAuthority) Preconditions.checkNotNull(UiModule.provideMarketplaceAuthority(identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MarketplaceAuthority mo10268get() {
        return provideInstance(this.identityServiceProvider);
    }
}
