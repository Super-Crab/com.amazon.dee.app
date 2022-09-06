package com.amazon.alexa.mobilytics.session;

import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultSessionStorage_Factory implements Factory<DefaultSessionStorage> {
    private final Provider<String> installationIdProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public DefaultSessionStorage_Factory(Provider<PersistentStorage.Factory> provider, Provider<String> provider2) {
        this.storageFactoryProvider = provider;
        this.installationIdProvider = provider2;
    }

    public static DefaultSessionStorage_Factory create(Provider<PersistentStorage.Factory> provider, Provider<String> provider2) {
        return new DefaultSessionStorage_Factory(provider, provider2);
    }

    public static DefaultSessionStorage newDefaultSessionStorage(PersistentStorage.Factory factory, String str) {
        return new DefaultSessionStorage(factory, str);
    }

    public static DefaultSessionStorage provideInstance(Provider<PersistentStorage.Factory> provider, Provider<String> provider2) {
        return new DefaultSessionStorage(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultSessionStorage mo10268get() {
        return provideInstance(this.storageFactoryProvider, this.installationIdProvider);
    }
}
