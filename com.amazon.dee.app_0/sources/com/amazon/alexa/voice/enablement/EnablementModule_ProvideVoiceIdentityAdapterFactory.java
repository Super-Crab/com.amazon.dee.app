package com.amazon.alexa.voice.enablement;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class EnablementModule_ProvideVoiceIdentityAdapterFactory implements Factory<VoiceIdentityAdapter> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<PersistentStorage.Factory> persistentStorageProvider;

    public EnablementModule_ProvideVoiceIdentityAdapterFactory(Provider<IdentityService> provider, Provider<PersistentStorage.Factory> provider2, Provider<EventBus> provider3) {
        this.identityServiceProvider = provider;
        this.persistentStorageProvider = provider2;
        this.eventBusProvider = provider3;
    }

    public static EnablementModule_ProvideVoiceIdentityAdapterFactory create(Provider<IdentityService> provider, Provider<PersistentStorage.Factory> provider2, Provider<EventBus> provider3) {
        return new EnablementModule_ProvideVoiceIdentityAdapterFactory(provider, provider2, provider3);
    }

    public static VoiceIdentityAdapter provideInstance(Provider<IdentityService> provider, Provider<PersistentStorage.Factory> provider2, Provider<EventBus> provider3) {
        return proxyProvideVoiceIdentityAdapter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static VoiceIdentityAdapter proxyProvideVoiceIdentityAdapter(IdentityService identityService, PersistentStorage.Factory factory, EventBus eventBus) {
        return (VoiceIdentityAdapter) Preconditions.checkNotNull(EnablementModule.provideVoiceIdentityAdapter(identityService, factory, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceIdentityAdapter mo10268get() {
        return provideInstance(this.identityServiceProvider, this.persistentStorageProvider, this.eventBusProvider);
    }
}
