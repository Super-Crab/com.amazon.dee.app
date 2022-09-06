package com.amazon.alexa.presence.library;

import android.content.Context;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ContextHelper_Factory implements Factory<ContextHelper> {
    private final Provider<Context> contextProvider;
    private final Provider<PersistentLocalStorage.PresenceDataStore> dataStoreProvider;

    public ContextHelper_Factory(Provider<Context> provider, Provider<PersistentLocalStorage.PresenceDataStore> provider2) {
        this.contextProvider = provider;
        this.dataStoreProvider = provider2;
    }

    public static ContextHelper_Factory create(Provider<Context> provider, Provider<PersistentLocalStorage.PresenceDataStore> provider2) {
        return new ContextHelper_Factory(provider, provider2);
    }

    public static ContextHelper newContextHelper(Context context, PersistentLocalStorage.PresenceDataStore presenceDataStore) {
        return new ContextHelper(context, presenceDataStore);
    }

    public static ContextHelper provideInstance(Provider<Context> provider, Provider<PersistentLocalStorage.PresenceDataStore> provider2) {
        return new ContextHelper(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContextHelper mo10268get() {
        return provideInstance(this.contextProvider, this.dataStoreProvider);
    }
}
