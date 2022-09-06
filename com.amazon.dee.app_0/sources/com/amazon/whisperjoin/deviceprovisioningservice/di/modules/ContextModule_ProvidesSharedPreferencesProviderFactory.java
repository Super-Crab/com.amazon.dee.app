package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ContextModule_ProvidesSharedPreferencesProviderFactory implements Factory<SharedPreferencesProvider> {
    private final Provider<Context> contextProvider;
    private final ContextModule module;

    public ContextModule_ProvidesSharedPreferencesProviderFactory(ContextModule contextModule, Provider<Context> provider) {
        this.module = contextModule;
        this.contextProvider = provider;
    }

    public static ContextModule_ProvidesSharedPreferencesProviderFactory create(ContextModule contextModule, Provider<Context> provider) {
        return new ContextModule_ProvidesSharedPreferencesProviderFactory(contextModule, provider);
    }

    public static SharedPreferencesProvider provideInstance(ContextModule contextModule, Provider<Context> provider) {
        return proxyProvidesSharedPreferencesProvider(contextModule, provider.mo10268get());
    }

    public static SharedPreferencesProvider proxyProvidesSharedPreferencesProvider(ContextModule contextModule, Context context) {
        return (SharedPreferencesProvider) Preconditions.checkNotNull(contextModule.providesSharedPreferencesProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferencesProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
