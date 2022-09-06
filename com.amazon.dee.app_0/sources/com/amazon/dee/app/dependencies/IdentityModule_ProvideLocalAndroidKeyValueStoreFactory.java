package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.delegatedidentity.storage.LocalAndroidKeyValueStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideLocalAndroidKeyValueStoreFactory implements Factory<LocalAndroidKeyValueStore> {
    private final Provider<Context> contextProvider;
    private final IdentityModule module;

    public IdentityModule_ProvideLocalAndroidKeyValueStoreFactory(IdentityModule identityModule, Provider<Context> provider) {
        this.module = identityModule;
        this.contextProvider = provider;
    }

    public static IdentityModule_ProvideLocalAndroidKeyValueStoreFactory create(IdentityModule identityModule, Provider<Context> provider) {
        return new IdentityModule_ProvideLocalAndroidKeyValueStoreFactory(identityModule, provider);
    }

    public static LocalAndroidKeyValueStore provideInstance(IdentityModule identityModule, Provider<Context> provider) {
        return proxyProvideLocalAndroidKeyValueStore(identityModule, provider.mo10268get());
    }

    public static LocalAndroidKeyValueStore proxyProvideLocalAndroidKeyValueStore(IdentityModule identityModule, Context context) {
        return (LocalAndroidKeyValueStore) Preconditions.checkNotNull(identityModule.provideLocalAndroidKeyValueStore(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocalAndroidKeyValueStore mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
