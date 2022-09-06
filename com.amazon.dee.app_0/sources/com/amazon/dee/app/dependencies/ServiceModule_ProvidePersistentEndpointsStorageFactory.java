package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.environment.PersistentEndpointsStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvidePersistentEndpointsStorageFactory implements Factory<PersistentEndpointsStorage> {
    private final ServiceModule module;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public ServiceModule_ProvidePersistentEndpointsStorageFactory(ServiceModule serviceModule, Provider<PersistentStorage.Factory> provider) {
        this.module = serviceModule;
        this.storageFactoryProvider = provider;
    }

    public static ServiceModule_ProvidePersistentEndpointsStorageFactory create(ServiceModule serviceModule, Provider<PersistentStorage.Factory> provider) {
        return new ServiceModule_ProvidePersistentEndpointsStorageFactory(serviceModule, provider);
    }

    public static PersistentEndpointsStorage provideInstance(ServiceModule serviceModule, Provider<PersistentStorage.Factory> provider) {
        return proxyProvidePersistentEndpointsStorage(serviceModule, provider.mo10268get());
    }

    public static PersistentEndpointsStorage proxyProvidePersistentEndpointsStorage(ServiceModule serviceModule, PersistentStorage.Factory factory) {
        return (PersistentEndpointsStorage) Preconditions.checkNotNull(serviceModule.providePersistentEndpointsStorage(factory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentEndpointsStorage mo10268get() {
        return provideInstance(this.module, this.storageFactoryProvider);
    }
}
