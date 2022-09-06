package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<PersistentStorage.Factory> {
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule) {
        this.module = tarazedIntegrationModule;
    }

    public static TarazedIntegrationModule_ProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule) {
        return new TarazedIntegrationModule_ProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule);
    }

    public static PersistentStorage.Factory provideInstance(TarazedIntegrationModule tarazedIntegrationModule) {
        return proxyProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule);
    }

    public static PersistentStorage.Factory proxyProvidePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule) {
        return (PersistentStorage.Factory) Preconditions.checkNotNull(tarazedIntegrationModule.providePersistentStorageFactory$AlexaMobileAndroidTarazedIntegration_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentStorage.Factory mo10268get() {
        return provideInstance(this.module);
    }
}
