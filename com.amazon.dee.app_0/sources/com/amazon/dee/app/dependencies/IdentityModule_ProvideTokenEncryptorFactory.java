package com.amazon.dee.app.dependencies;

import com.amazon.alexa.delegatedidentity.storage.LocalAndroidKeyValueStore;
import com.amazon.alexa.delegatedidentity.storage.TokenEncryptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideTokenEncryptorFactory implements Factory<TokenEncryptor> {
    private final Provider<LocalAndroidKeyValueStore> localAndroidKeyValueStoreProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideTokenEncryptorFactory(IdentityModule identityModule, Provider<LocalAndroidKeyValueStore> provider) {
        this.module = identityModule;
        this.localAndroidKeyValueStoreProvider = provider;
    }

    public static IdentityModule_ProvideTokenEncryptorFactory create(IdentityModule identityModule, Provider<LocalAndroidKeyValueStore> provider) {
        return new IdentityModule_ProvideTokenEncryptorFactory(identityModule, provider);
    }

    public static TokenEncryptor provideInstance(IdentityModule identityModule, Provider<LocalAndroidKeyValueStore> provider) {
        return proxyProvideTokenEncryptor(identityModule, provider.mo10268get());
    }

    public static TokenEncryptor proxyProvideTokenEncryptor(IdentityModule identityModule, LocalAndroidKeyValueStore localAndroidKeyValueStore) {
        return (TokenEncryptor) Preconditions.checkNotNull(identityModule.provideTokenEncryptor(localAndroidKeyValueStore), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TokenEncryptor mo10268get() {
        return provideInstance(this.module, this.localAndroidKeyValueStoreProvider);
    }
}
