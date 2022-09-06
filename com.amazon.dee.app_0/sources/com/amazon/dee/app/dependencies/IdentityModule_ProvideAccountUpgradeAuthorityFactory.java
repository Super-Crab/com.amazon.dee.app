package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.MAPAccountUpgradeService;
import com.amazon.alexa.identity.api.AccountUpgradeAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideAccountUpgradeAuthorityFactory implements Factory<AccountUpgradeAuthority> {
    private final Provider<MAPAccountUpgradeService> accountUpgradeServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideAccountUpgradeAuthorityFactory(IdentityModule identityModule, Provider<MAPAccountUpgradeService> provider) {
        this.module = identityModule;
        this.accountUpgradeServiceProvider = provider;
    }

    public static IdentityModule_ProvideAccountUpgradeAuthorityFactory create(IdentityModule identityModule, Provider<MAPAccountUpgradeService> provider) {
        return new IdentityModule_ProvideAccountUpgradeAuthorityFactory(identityModule, provider);
    }

    public static AccountUpgradeAuthority provideInstance(IdentityModule identityModule, Provider<MAPAccountUpgradeService> provider) {
        return proxyProvideAccountUpgradeAuthority(identityModule, provider.mo10268get());
    }

    public static AccountUpgradeAuthority proxyProvideAccountUpgradeAuthority(IdentityModule identityModule, MAPAccountUpgradeService mAPAccountUpgradeService) {
        return (AccountUpgradeAuthority) Preconditions.checkNotNull(identityModule.provideAccountUpgradeAuthority(mAPAccountUpgradeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountUpgradeAuthority mo10268get() {
        return provideInstance(this.module, this.accountUpgradeServiceProvider);
    }
}
