package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AuthModule_ProvidesMapAccountManagerFactory implements Factory<MAPAccountManager> {
    private final Provider<Context> contextProvider;
    private final AuthModule module;

    public AuthModule_ProvidesMapAccountManagerFactory(AuthModule authModule, Provider<Context> provider) {
        this.module = authModule;
        this.contextProvider = provider;
    }

    public static AuthModule_ProvidesMapAccountManagerFactory create(AuthModule authModule, Provider<Context> provider) {
        return new AuthModule_ProvidesMapAccountManagerFactory(authModule, provider);
    }

    public static MAPAccountManager provideInstance(AuthModule authModule, Provider<Context> provider) {
        return proxyProvidesMapAccountManager(authModule, provider.mo10268get());
    }

    public static MAPAccountManager proxyProvidesMapAccountManager(AuthModule authModule, Context context) {
        return (MAPAccountManager) Preconditions.checkNotNull(authModule.providesMapAccountManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPAccountManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
