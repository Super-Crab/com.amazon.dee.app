package com.amazon.alexa.auto.storage;

import android.content.Context;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class StorageModule_ProvideNavigationPreferenceStorageFactory implements Factory<PersistentStorage> {
    private final Provider<Context> contextProvider;
    private final StorageModule module;

    public StorageModule_ProvideNavigationPreferenceStorageFactory(StorageModule storageModule, Provider<Context> provider) {
        this.module = storageModule;
        this.contextProvider = provider;
    }

    public static StorageModule_ProvideNavigationPreferenceStorageFactory create(StorageModule storageModule, Provider<Context> provider) {
        return new StorageModule_ProvideNavigationPreferenceStorageFactory(storageModule, provider);
    }

    public static PersistentStorage provideInstance(StorageModule storageModule, Provider<Context> provider) {
        return proxyProvideNavigationPreferenceStorage(storageModule, provider.mo10268get());
    }

    public static PersistentStorage proxyProvideNavigationPreferenceStorage(StorageModule storageModule, Context context) {
        return (PersistentStorage) Preconditions.checkNotNull(storageModule.provideNavigationPreferenceStorage(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentStorage mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
