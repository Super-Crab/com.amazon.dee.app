package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_PresenceDataStoreFactory implements Factory<PersistentLocalStorage.PresenceDataStore> {
    private final PresenceModule module;

    public PresenceModule_PresenceDataStoreFactory(PresenceModule presenceModule) {
        this.module = presenceModule;
    }

    public static PresenceModule_PresenceDataStoreFactory create(PresenceModule presenceModule) {
        return new PresenceModule_PresenceDataStoreFactory(presenceModule);
    }

    public static PersistentLocalStorage.PresenceDataStore provideInstance(PresenceModule presenceModule) {
        return proxyPresenceDataStore(presenceModule);
    }

    public static PersistentLocalStorage.PresenceDataStore proxyPresenceDataStore(PresenceModule presenceModule) {
        return (PersistentLocalStorage.PresenceDataStore) Preconditions.checkNotNull(presenceModule.presenceDataStore(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PersistentLocalStorage.PresenceDataStore mo10268get() {
        return provideInstance(this.module);
    }
}
