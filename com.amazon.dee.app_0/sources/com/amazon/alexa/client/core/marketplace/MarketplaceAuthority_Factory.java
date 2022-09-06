package com.amazon.alexa.client.core.marketplace;

import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MarketplaceAuthority_Factory implements Factory<MarketplaceAuthority> {
    private final Provider<AccountManager> accountManagerProvider;
    private final Provider<PersistentStorage> persistentStorageProvider;

    public MarketplaceAuthority_Factory(Provider<AccountManager> provider, Provider<PersistentStorage> provider2) {
        this.accountManagerProvider = provider;
        this.persistentStorageProvider = provider2;
    }

    public static MarketplaceAuthority_Factory create(Provider<AccountManager> provider, Provider<PersistentStorage> provider2) {
        return new MarketplaceAuthority_Factory(provider, provider2);
    }

    public static MarketplaceAuthority newMarketplaceAuthority(AccountManager accountManager, Lazy<PersistentStorage> lazy) {
        return new MarketplaceAuthority(accountManager, lazy);
    }

    public static MarketplaceAuthority provideInstance(Provider<AccountManager> provider, Provider<PersistentStorage> provider2) {
        return new MarketplaceAuthority(provider.mo10268get(), DoubleCheck.lazy(provider2));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MarketplaceAuthority mo10268get() {
        return provideInstance(this.accountManagerProvider, this.persistentStorageProvider);
    }
}
