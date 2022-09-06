package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.MAPAccountUpgradeService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideImplementationAccountUpgradeServiceFactory implements Factory<MAPAccountUpgradeService> {
    private final Provider<String> deviceNameTemplateProvider;
    private final Provider<MAPAccountManager> mapAccountManagerProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideImplementationAccountUpgradeServiceFactory(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<String> provider2) {
        this.module = identityModule;
        this.mapAccountManagerProvider = provider;
        this.deviceNameTemplateProvider = provider2;
    }

    public static IdentityModule_ProvideImplementationAccountUpgradeServiceFactory create(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<String> provider2) {
        return new IdentityModule_ProvideImplementationAccountUpgradeServiceFactory(identityModule, provider, provider2);
    }

    public static MAPAccountUpgradeService provideInstance(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<String> provider2) {
        return proxyProvideImplementationAccountUpgradeService(identityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MAPAccountUpgradeService proxyProvideImplementationAccountUpgradeService(IdentityModule identityModule, MAPAccountManager mAPAccountManager, String str) {
        return (MAPAccountUpgradeService) Preconditions.checkNotNull(identityModule.provideImplementationAccountUpgradeService(mAPAccountManager, str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAccountUpgradeService mo10268get() {
        return provideInstance(this.module, this.mapAccountManagerProvider, this.deviceNameTemplateProvider);
    }
}
