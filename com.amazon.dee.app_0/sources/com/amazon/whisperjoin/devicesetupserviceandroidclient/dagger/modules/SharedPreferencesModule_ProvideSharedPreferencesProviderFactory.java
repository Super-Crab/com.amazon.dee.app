package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.content.Context;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.SharedPreferencesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class SharedPreferencesModule_ProvideSharedPreferencesProviderFactory implements Factory<SharedPreferencesProvider> {
    private final Provider<Context> contextProvider;
    private final SharedPreferencesModule module;

    public SharedPreferencesModule_ProvideSharedPreferencesProviderFactory(SharedPreferencesModule sharedPreferencesModule, Provider<Context> provider) {
        this.module = sharedPreferencesModule;
        this.contextProvider = provider;
    }

    public static SharedPreferencesModule_ProvideSharedPreferencesProviderFactory create(SharedPreferencesModule sharedPreferencesModule, Provider<Context> provider) {
        return new SharedPreferencesModule_ProvideSharedPreferencesProviderFactory(sharedPreferencesModule, provider);
    }

    public static SharedPreferencesProvider provideInstance(SharedPreferencesModule sharedPreferencesModule, Provider<Context> provider) {
        return proxyProvideSharedPreferencesProvider(sharedPreferencesModule, provider.mo10268get());
    }

    public static SharedPreferencesProvider proxyProvideSharedPreferencesProvider(SharedPreferencesModule sharedPreferencesModule, Context context) {
        return (SharedPreferencesProvider) Preconditions.checkNotNull(sharedPreferencesModule.provideSharedPreferencesProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SharedPreferencesProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
