package com.amazon.alexa.mobilytics.configuration;

import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PersistentConfigStorage_Factory implements Factory<PersistentConfigStorage> {
    private final Provider<JsonConverter> converterProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public PersistentConfigStorage_Factory(Provider<PersistentStorage.Factory> provider, Provider<JsonConverter> provider2) {
        this.storageFactoryProvider = provider;
        this.converterProvider = provider2;
    }

    public static PersistentConfigStorage_Factory create(Provider<PersistentStorage.Factory> provider, Provider<JsonConverter> provider2) {
        return new PersistentConfigStorage_Factory(provider, provider2);
    }

    public static PersistentConfigStorage newPersistentConfigStorage(PersistentStorage.Factory factory, JsonConverter jsonConverter) {
        return new PersistentConfigStorage(factory, jsonConverter);
    }

    public static PersistentConfigStorage provideInstance(Provider<PersistentStorage.Factory> provider, Provider<JsonConverter> provider2) {
        return new PersistentConfigStorage(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentConfigStorage mo10268get() {
        return provideInstance(this.storageFactoryProvider, this.converterProvider);
    }
}
