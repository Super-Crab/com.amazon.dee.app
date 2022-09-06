package com.amazon.dee.app.dependencies;

import com.amazon.alexa.delegatedidentity.TokenAccessor;
import com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement;
import com.amazon.alexa.delegatedidentity.storage.TokenStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideDelegatedTokenManagementFactory implements Factory<DelegatedTokenManagement> {
    private final IdentityModule module;
    private final Provider<TokenAccessor> tokenAccessorProvider;
    private final Provider<TokenStorage> tokenStorageProvider;

    public IdentityModule_ProvideDelegatedTokenManagementFactory(IdentityModule identityModule, Provider<TokenAccessor> provider, Provider<TokenStorage> provider2) {
        this.module = identityModule;
        this.tokenAccessorProvider = provider;
        this.tokenStorageProvider = provider2;
    }

    public static IdentityModule_ProvideDelegatedTokenManagementFactory create(IdentityModule identityModule, Provider<TokenAccessor> provider, Provider<TokenStorage> provider2) {
        return new IdentityModule_ProvideDelegatedTokenManagementFactory(identityModule, provider, provider2);
    }

    public static DelegatedTokenManagement provideInstance(IdentityModule identityModule, Provider<TokenAccessor> provider, Provider<TokenStorage> provider2) {
        return proxyProvideDelegatedTokenManagement(identityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static DelegatedTokenManagement proxyProvideDelegatedTokenManagement(IdentityModule identityModule, TokenAccessor tokenAccessor, TokenStorage tokenStorage) {
        return (DelegatedTokenManagement) Preconditions.checkNotNull(identityModule.provideDelegatedTokenManagement(tokenAccessor, tokenStorage), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DelegatedTokenManagement mo10268get() {
        return provideInstance(this.module, this.tokenAccessorProvider, this.tokenStorageProvider);
    }
}
