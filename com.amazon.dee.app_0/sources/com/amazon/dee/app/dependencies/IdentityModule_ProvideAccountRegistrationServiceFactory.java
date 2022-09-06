package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.MAPAccountRegistrationService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideAccountRegistrationServiceFactory implements Factory<MAPAccountRegistrationService> {
    private final Provider<String> deviceNameTemplateProvider;
    private final Provider<MAPAccountManager> mapAccountManagerProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideAccountRegistrationServiceFactory(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<String> provider2) {
        this.module = identityModule;
        this.mapAccountManagerProvider = provider;
        this.deviceNameTemplateProvider = provider2;
    }

    public static IdentityModule_ProvideAccountRegistrationServiceFactory create(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<String> provider2) {
        return new IdentityModule_ProvideAccountRegistrationServiceFactory(identityModule, provider, provider2);
    }

    public static MAPAccountRegistrationService provideInstance(IdentityModule identityModule, Provider<MAPAccountManager> provider, Provider<String> provider2) {
        return proxyProvideAccountRegistrationService(identityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static MAPAccountRegistrationService proxyProvideAccountRegistrationService(IdentityModule identityModule, MAPAccountManager mAPAccountManager, String str) {
        return (MAPAccountRegistrationService) Preconditions.checkNotNull(identityModule.provideAccountRegistrationService(mAPAccountManager, str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAccountRegistrationService mo10268get() {
        return provideInstance(this.module, this.mapAccountManagerProvider, this.deviceNameTemplateProvider);
    }
}
