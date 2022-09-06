package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.AuthenticationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideAuthenticationProviderFactory implements Factory<AuthenticationProvider> {
    private final Provider<MAPIdentityService> mapIdentityServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideAuthenticationProviderFactory(IdentityModule identityModule, Provider<MAPIdentityService> provider) {
        this.module = identityModule;
        this.mapIdentityServiceProvider = provider;
    }

    public static IdentityModule_ProvideAuthenticationProviderFactory create(IdentityModule identityModule, Provider<MAPIdentityService> provider) {
        return new IdentityModule_ProvideAuthenticationProviderFactory(identityModule, provider);
    }

    public static AuthenticationProvider provideInstance(IdentityModule identityModule, Provider<MAPIdentityService> provider) {
        return proxyProvideAuthenticationProvider(identityModule, provider.mo10268get());
    }

    public static AuthenticationProvider proxyProvideAuthenticationProvider(IdentityModule identityModule, MAPIdentityService mAPIdentityService) {
        return (AuthenticationProvider) Preconditions.checkNotNull(identityModule.provideAuthenticationProvider(mAPIdentityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AuthenticationProvider mo10268get() {
        return provideInstance(this.module, this.mapIdentityServiceProvider);
    }
}
