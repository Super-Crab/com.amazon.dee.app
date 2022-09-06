package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.content.Context;
import com.amazon.identity.auth.device.api.TokenManagement;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AuthModule_ProvidesTokenManagementFactory implements Factory<TokenManagement> {
    private final Provider<Context> contextProvider;
    private final AuthModule module;

    public AuthModule_ProvidesTokenManagementFactory(AuthModule authModule, Provider<Context> provider) {
        this.module = authModule;
        this.contextProvider = provider;
    }

    public static AuthModule_ProvidesTokenManagementFactory create(AuthModule authModule, Provider<Context> provider) {
        return new AuthModule_ProvidesTokenManagementFactory(authModule, provider);
    }

    public static TokenManagement provideInstance(AuthModule authModule, Provider<Context> provider) {
        return proxyProvidesTokenManagement(authModule, provider.mo10268get());
    }

    public static TokenManagement proxyProvidesTokenManagement(AuthModule authModule, Context context) {
        return (TokenManagement) Preconditions.checkNotNull(authModule.providesTokenManagement(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TokenManagement mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
