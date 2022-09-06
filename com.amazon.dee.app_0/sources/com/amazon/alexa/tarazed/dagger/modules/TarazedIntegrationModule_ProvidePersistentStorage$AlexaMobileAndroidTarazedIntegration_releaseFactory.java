package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<PersistentStorage> {
    private final Provider<PersistentStorage.Factory> factoryProvider;
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule, Provider<PersistentStorage.Factory> provider) {
        this.module = tarazedIntegrationModule;
        this.factoryProvider = provider;
    }

    public static TarazedIntegrationModule_ProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule, Provider<PersistentStorage.Factory> provider) {
        return new TarazedIntegrationModule_ProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule, provider);
    }

    public static PersistentStorage provideInstance(TarazedIntegrationModule tarazedIntegrationModule, Provider<PersistentStorage.Factory> provider) {
        return proxyProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule, provider.mo10268get());
    }

    public static PersistentStorage proxyProvidePersistentStorage$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule, PersistentStorage.Factory factory) {
        return (PersistentStorage) Preconditions.checkNotNull(tarazedIntegrationModule.providePersistentStorage$AlexaMobileAndroidTarazedIntegration_release(factory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentStorage mo10268get() {
        return provideInstance(this.module, this.factoryProvider);
    }
}
