package com.amazon.dee.app.dependencies;

import com.amazon.alexa.delegatedidentity.TokenAccessor;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideTokenAccessorFactory implements Factory<TokenAccessor> {
    private final Provider<CoralService> coralServiceProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideTokenAccessorFactory(IdentityModule identityModule, Provider<CoralService> provider) {
        this.module = identityModule;
        this.coralServiceProvider = provider;
    }

    public static IdentityModule_ProvideTokenAccessorFactory create(IdentityModule identityModule, Provider<CoralService> provider) {
        return new IdentityModule_ProvideTokenAccessorFactory(identityModule, provider);
    }

    public static TokenAccessor provideInstance(IdentityModule identityModule, Provider<CoralService> provider) {
        return proxyProvideTokenAccessor(identityModule, provider.mo10268get());
    }

    public static TokenAccessor proxyProvideTokenAccessor(IdentityModule identityModule, CoralService coralService) {
        return (TokenAccessor) Preconditions.checkNotNull(identityModule.provideTokenAccessor(coralService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TokenAccessor mo10268get() {
        return provideInstance(this.module, this.coralServiceProvider);
    }
}
