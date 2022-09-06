package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.MAPAccountUpgradeService;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideAccountUpgradeServiceFactory implements Factory<AccountUpgradeService> {
    private final Provider<MAPAccountUpgradeService> accountUpgradeServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideAccountUpgradeServiceFactory(IdentityModule identityModule, Provider<MAPAccountUpgradeService> provider) {
        this.module = identityModule;
        this.accountUpgradeServiceProvider = provider;
    }

    public static IdentityModule_ProvideAccountUpgradeServiceFactory create(IdentityModule identityModule, Provider<MAPAccountUpgradeService> provider) {
        return new IdentityModule_ProvideAccountUpgradeServiceFactory(identityModule, provider);
    }

    public static AccountUpgradeService provideInstance(IdentityModule identityModule, Provider<MAPAccountUpgradeService> provider) {
        return proxyProvideAccountUpgradeService(identityModule, provider.mo10268get());
    }

    public static AccountUpgradeService proxyProvideAccountUpgradeService(IdentityModule identityModule, MAPAccountUpgradeService mAPAccountUpgradeService) {
        return (AccountUpgradeService) Preconditions.checkNotNull(identityModule.provideAccountUpgradeService(mAPAccountUpgradeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountUpgradeService mo10268get() {
        return provideInstance(this.module, this.accountUpgradeServiceProvider);
    }
}
