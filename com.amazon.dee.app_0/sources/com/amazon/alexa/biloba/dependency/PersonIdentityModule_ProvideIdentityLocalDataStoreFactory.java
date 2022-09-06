package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.storage.IdentityLocalDataStore;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class PersonIdentityModule_ProvideIdentityLocalDataStoreFactory implements Factory<IdentityLocalDataStore> {
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public PersonIdentityModule_ProvideIdentityLocalDataStoreFactory(Provider<PersistentStorage.Factory> provider, Provider<IdentityService> provider2) {
        this.storageFactoryProvider = provider;
        this.identityServiceProvider = provider2;
    }

    public static PersonIdentityModule_ProvideIdentityLocalDataStoreFactory create(Provider<PersistentStorage.Factory> provider, Provider<IdentityService> provider2) {
        return new PersonIdentityModule_ProvideIdentityLocalDataStoreFactory(provider, provider2);
    }

    public static IdentityLocalDataStore provideInstance(Provider<PersistentStorage.Factory> provider, Provider<IdentityService> provider2) {
        return proxyProvideIdentityLocalDataStore(provider.mo10268get(), provider2.mo10268get());
    }

    public static IdentityLocalDataStore proxyProvideIdentityLocalDataStore(PersistentStorage.Factory factory, IdentityService identityService) {
        return (IdentityLocalDataStore) Preconditions.checkNotNull(PersonIdentityModule.provideIdentityLocalDataStore(factory, identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityLocalDataStore mo10268get() {
        return provideInstance(this.storageFactoryProvider, this.identityServiceProvider);
    }
}
