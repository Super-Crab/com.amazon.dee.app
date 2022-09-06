package com.amazon.dee.app.dependencies;

import android.webkit.CookieManager;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.AccountUpgradeAuthority;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideAccountServiceFactory implements Factory<AccountService> {
    private final Provider<AccountUpgradeAuthority> accountUpgradeServiceProvider;
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<String> deviceNameTemplateProvider;
    private final Provider<MAPAccountManager> mapAccountManagerProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideAccountServiceFactory(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<AccountUpgradeAuthority> provider2, Provider<CookieManager> provider3, Provider<CommsManager> provider4, Provider<String> provider5) {
        this.module = identityModule;
        this.mapAccountManagerProvider = provider;
        this.accountUpgradeServiceProvider = provider2;
        this.cookieManagerProvider = provider3;
        this.commsManagerProvider = provider4;
        this.deviceNameTemplateProvider = provider5;
    }

    public static IdentityModule_ProvideAccountServiceFactory create(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<AccountUpgradeAuthority> provider2, Provider<CookieManager> provider3, Provider<CommsManager> provider4, Provider<String> provider5) {
        return new IdentityModule_ProvideAccountServiceFactory(identityModule, provider, provider2, provider3, provider4, provider5);
    }

    public static AccountService provideInstance(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<AccountUpgradeAuthority> provider2, Provider<CookieManager> provider3, Provider<CommsManager> provider4, Provider<String> provider5) {
        return proxyProvideAccountService(identityModule, provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3), provider4.mo10268get(), provider5.mo10268get());
    }

    public static AccountService proxyProvideAccountService(IdentityModule identityModule, MAPAccountManager mAPAccountManager, AccountUpgradeAuthority accountUpgradeAuthority, Lazy<CookieManager> lazy, CommsManager commsManager, String str) {
        return (AccountService) Preconditions.checkNotNull(identityModule.provideAccountService(mAPAccountManager, accountUpgradeAuthority, lazy, commsManager, str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountService mo10268get() {
        return provideInstance(this.module, this.mapAccountManagerProvider, this.accountUpgradeServiceProvider, this.cookieManagerProvider, this.commsManagerProvider, this.deviceNameTemplateProvider);
    }
}
