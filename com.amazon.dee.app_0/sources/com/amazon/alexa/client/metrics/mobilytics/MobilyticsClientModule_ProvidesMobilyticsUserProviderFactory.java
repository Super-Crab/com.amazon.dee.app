package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MobilyticsClientModule_ProvidesMobilyticsUserProviderFactory implements Factory<MobilyticsUserProviderImpl> {
    private final Provider<AccountManager> accountManagerProvider;
    private final Provider<MarketplaceAuthority> marketplaceAuthorityProvider;
    private final MobilyticsClientModule module;

    public MobilyticsClientModule_ProvidesMobilyticsUserProviderFactory(MobilyticsClientModule mobilyticsClientModule, Provider<AccountManager> provider, Provider<MarketplaceAuthority> provider2) {
        this.module = mobilyticsClientModule;
        this.accountManagerProvider = provider;
        this.marketplaceAuthorityProvider = provider2;
    }

    public static MobilyticsClientModule_ProvidesMobilyticsUserProviderFactory create(MobilyticsClientModule mobilyticsClientModule, Provider<AccountManager> provider, Provider<MarketplaceAuthority> provider2) {
        return new MobilyticsClientModule_ProvidesMobilyticsUserProviderFactory(mobilyticsClientModule, provider, provider2);
    }

    public static MobilyticsUserProviderImpl provideInstance(MobilyticsClientModule mobilyticsClientModule, Provider<AccountManager> provider, Provider<MarketplaceAuthority> provider2) {
        return proxyProvidesMobilyticsUserProvider(mobilyticsClientModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MobilyticsUserProviderImpl proxyProvidesMobilyticsUserProvider(MobilyticsClientModule mobilyticsClientModule, AccountManager accountManager, MarketplaceAuthority marketplaceAuthority) {
        return (MobilyticsUserProviderImpl) Preconditions.checkNotNull(mobilyticsClientModule.providesMobilyticsUserProvider(accountManager, marketplaceAuthority), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsUserProviderImpl mo10268get() {
        return provideInstance(this.module, this.accountManagerProvider, this.marketplaceAuthorityProvider);
    }
}
