package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AuthModule_ProvidesTokenKeysFactory implements Factory<String> {
    private final Provider<Context> contextProvider;
    private final AuthModule module;

    public AuthModule_ProvidesTokenKeysFactory(AuthModule authModule, Provider<Context> provider) {
        this.module = authModule;
        this.contextProvider = provider;
    }

    public static AuthModule_ProvidesTokenKeysFactory create(AuthModule authModule, Provider<Context> provider) {
        return new AuthModule_ProvidesTokenKeysFactory(authModule, provider);
    }

    public static String provideInstance(AuthModule authModule, Provider<Context> provider) {
        return proxyProvidesTokenKeys(authModule, provider.mo10268get());
    }

    public static String proxyProvidesTokenKeys(AuthModule authModule, Context context) {
        return (String) Preconditions.checkNotNull(authModule.providesTokenKeys(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public String mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
