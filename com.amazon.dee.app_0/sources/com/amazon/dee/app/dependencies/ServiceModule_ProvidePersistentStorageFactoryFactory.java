package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.storage.JsonConverter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidePersistentStorageFactoryFactory implements Factory<PersistentStorage.Factory> {
    private final Provider<Context> contextProvider;
    private final ServiceModule module;
    private final Provider<JsonConverter> serializerProvider;

    public ServiceModule_ProvidePersistentStorageFactoryFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<JsonConverter> provider2) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.serializerProvider = provider2;
    }

    public static ServiceModule_ProvidePersistentStorageFactoryFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<JsonConverter> provider2) {
        return new ServiceModule_ProvidePersistentStorageFactoryFactory(serviceModule, provider, provider2);
    }

    public static PersistentStorage.Factory provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<JsonConverter> provider2) {
        return proxyProvidePersistentStorageFactory(serviceModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static PersistentStorage.Factory proxyProvidePersistentStorageFactory(ServiceModule serviceModule, Context context, JsonConverter jsonConverter) {
        return (PersistentStorage.Factory) Preconditions.checkNotNull(serviceModule.providePersistentStorageFactory(context, jsonConverter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentStorage.Factory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.serializerProvider);
    }
}
