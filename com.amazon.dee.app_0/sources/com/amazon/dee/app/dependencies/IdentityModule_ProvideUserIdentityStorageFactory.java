package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideUserIdentityStorageFactory implements Factory<UserIdentityStorage> {
    private final Provider<PersistentStorage.Factory> factoryProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideUserIdentityStorageFactory(IdentityModule identityModule, Provider<PersistentStorage.Factory> provider) {
        this.module = identityModule;
        this.factoryProvider = provider;
    }

    public static IdentityModule_ProvideUserIdentityStorageFactory create(IdentityModule identityModule, Provider<PersistentStorage.Factory> provider) {
        return new IdentityModule_ProvideUserIdentityStorageFactory(identityModule, provider);
    }

    public static UserIdentityStorage provideInstance(IdentityModule identityModule, Provider<PersistentStorage.Factory> provider) {
        return proxyProvideUserIdentityStorage(identityModule, provider.mo10268get());
    }

    public static UserIdentityStorage proxyProvideUserIdentityStorage(IdentityModule identityModule, PersistentStorage.Factory factory) {
        return (UserIdentityStorage) Preconditions.checkNotNull(identityModule.provideUserIdentityStorage(factory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserIdentityStorage mo10268get() {
        return provideInstance(this.module, this.factoryProvider);
    }
}
