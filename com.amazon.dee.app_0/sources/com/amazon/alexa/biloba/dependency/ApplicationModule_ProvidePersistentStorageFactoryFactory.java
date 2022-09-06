package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidePersistentStorageFactoryFactory implements Factory<PersistentStorage.Factory> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidePersistentStorageFactoryFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidePersistentStorageFactoryFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidePersistentStorageFactoryFactory(applicationModule);
    }

    public static PersistentStorage.Factory provideInstance(ApplicationModule applicationModule) {
        return proxyProvidePersistentStorageFactory(applicationModule);
    }

    public static PersistentStorage.Factory proxyProvidePersistentStorageFactory(ApplicationModule applicationModule) {
        return (PersistentStorage.Factory) Preconditions.checkNotNull(applicationModule.providePersistentStorageFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentStorage.Factory mo10268get() {
        return provideInstance(this.module);
    }
}
