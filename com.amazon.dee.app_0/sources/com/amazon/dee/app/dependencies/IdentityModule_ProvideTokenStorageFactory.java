package com.amazon.dee.app.dependencies;

import com.amazon.alexa.delegatedidentity.storage.TokenEncryptor;
import com.amazon.alexa.delegatedidentity.storage.TokenStorage;
import com.amazon.alexa.protocols.datastore.DataStoreService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideTokenStorageFactory implements Factory<TokenStorage> {
    private final Provider<DataStoreService> dataStoreServiceProvider;
    private final IdentityModule module;
    private final Provider<TokenEncryptor> tokenEncryptorProvider;

    public IdentityModule_ProvideTokenStorageFactory(IdentityModule identityModule, Provider<DataStoreService> provider, Provider<TokenEncryptor> provider2) {
        this.module = identityModule;
        this.dataStoreServiceProvider = provider;
        this.tokenEncryptorProvider = provider2;
    }

    public static IdentityModule_ProvideTokenStorageFactory create(IdentityModule identityModule, Provider<DataStoreService> provider, Provider<TokenEncryptor> provider2) {
        return new IdentityModule_ProvideTokenStorageFactory(identityModule, provider, provider2);
    }

    public static TokenStorage provideInstance(IdentityModule identityModule, Provider<DataStoreService> provider, Provider<TokenEncryptor> provider2) {
        return proxyProvideTokenStorage(identityModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static TokenStorage proxyProvideTokenStorage(IdentityModule identityModule, DataStoreService dataStoreService, TokenEncryptor tokenEncryptor) {
        return (TokenStorage) Preconditions.checkNotNull(identityModule.provideTokenStorage(dataStoreService, tokenEncryptor), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TokenStorage mo10268get() {
        return provideInstance(this.module, this.dataStoreServiceProvider, this.tokenEncryptorProvider);
    }
}
