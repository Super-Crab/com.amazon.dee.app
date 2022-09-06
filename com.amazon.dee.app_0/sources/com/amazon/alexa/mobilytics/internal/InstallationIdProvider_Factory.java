package com.amazon.alexa.mobilytics.internal;

import android.content.Context;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class InstallationIdProvider_Factory implements Factory<InstallationIdProvider> {
    private final Provider<Context> contextProvider;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public InstallationIdProvider_Factory(Provider<PersistentStorage.Factory> provider, Provider<Context> provider2) {
        this.storageFactoryProvider = provider;
        this.contextProvider = provider2;
    }

    public static InstallationIdProvider_Factory create(Provider<PersistentStorage.Factory> provider, Provider<Context> provider2) {
        return new InstallationIdProvider_Factory(provider, provider2);
    }

    public static InstallationIdProvider newInstallationIdProvider(PersistentStorage.Factory factory, Context context) {
        return new InstallationIdProvider(factory, context);
    }

    public static InstallationIdProvider provideInstance(Provider<PersistentStorage.Factory> provider, Provider<Context> provider2) {
        return new InstallationIdProvider(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public InstallationIdProvider mo10268get() {
        return provideInstance(this.storageFactoryProvider, this.contextProvider);
    }
}
