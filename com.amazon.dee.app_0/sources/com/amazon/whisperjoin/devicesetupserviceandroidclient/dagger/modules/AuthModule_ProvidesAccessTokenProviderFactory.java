package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AuthModule_ProvidesAccessTokenProviderFactory implements Factory<MapAccessTokenProvider> {
    private final Provider<MAPAccountManager> managerProvider;
    private final AuthModule module;
    private final Provider<String> tokenKeysProvider;
    private final Provider<TokenManagement> tokenManagementProvider;

    public AuthModule_ProvidesAccessTokenProviderFactory(AuthModule authModule, Provider<MAPAccountManager> provider, Provider<TokenManagement> provider2, Provider<String> provider3) {
        this.module = authModule;
        this.managerProvider = provider;
        this.tokenManagementProvider = provider2;
        this.tokenKeysProvider = provider3;
    }

    public static AuthModule_ProvidesAccessTokenProviderFactory create(AuthModule authModule, Provider<MAPAccountManager> provider, Provider<TokenManagement> provider2, Provider<String> provider3) {
        return new AuthModule_ProvidesAccessTokenProviderFactory(authModule, provider, provider2, provider3);
    }

    public static MapAccessTokenProvider provideInstance(AuthModule authModule, Provider<MAPAccountManager> provider, Provider<TokenManagement> provider2, Provider<String> provider3) {
        return proxyProvidesAccessTokenProvider(authModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static MapAccessTokenProvider proxyProvidesAccessTokenProvider(AuthModule authModule, MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, String str) {
        return (MapAccessTokenProvider) Preconditions.checkNotNull(authModule.providesAccessTokenProvider(mAPAccountManager, tokenManagement, str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MapAccessTokenProvider mo10268get() {
        return provideInstance(this.module, this.managerProvider, this.tokenManagementProvider, this.tokenKeysProvider);
    }
}
