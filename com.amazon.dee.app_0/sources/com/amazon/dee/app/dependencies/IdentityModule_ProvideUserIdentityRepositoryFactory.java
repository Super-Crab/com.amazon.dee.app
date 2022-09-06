package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.identity.UserIdentityMapper;
import com.amazon.alexa.identity.api.UserIdentityRepository;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideUserIdentityRepositoryFactory implements Factory<UserIdentityRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<MAPAccountManager> mapAccountManagerProvider;
    private final Provider<MarketplaceService> marketplaceServiceProvider;
    private final IdentityModule module;
    private final Provider<UserProfileManager> profileManagerProvider;
    private final Provider<TokenManagement> tokenManagementProvider;
    private final Provider<UserIdentityMapper> userIdentityMapperProvider;
    private final Provider<UserIdentityStorage> userStorageProvider;

    public IdentityModule_ProvideUserIdentityRepositoryFactory(IdentityModule identityModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<MarketplaceService> provider3, Provider<UserIdentityMapper> provider4, Provider<UserIdentityStorage> provider5, Provider<MAPAccountManager> provider6, Provider<TokenManagement> provider7, Provider<UserProfileManager> provider8) {
        this.module = identityModule;
        this.contextProvider = provider;
        this.coralServiceProvider = provider2;
        this.marketplaceServiceProvider = provider3;
        this.userIdentityMapperProvider = provider4;
        this.userStorageProvider = provider5;
        this.mapAccountManagerProvider = provider6;
        this.tokenManagementProvider = provider7;
        this.profileManagerProvider = provider8;
    }

    public static IdentityModule_ProvideUserIdentityRepositoryFactory create(IdentityModule identityModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<MarketplaceService> provider3, Provider<UserIdentityMapper> provider4, Provider<UserIdentityStorage> provider5, Provider<MAPAccountManager> provider6, Provider<TokenManagement> provider7, Provider<UserProfileManager> provider8) {
        return new IdentityModule_ProvideUserIdentityRepositoryFactory(identityModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static UserIdentityRepository provideInstance(IdentityModule identityModule, Provider<Context> provider, Provider<CoralService> provider2, Provider<MarketplaceService> provider3, Provider<UserIdentityMapper> provider4, Provider<UserIdentityStorage> provider5, Provider<MAPAccountManager> provider6, Provider<TokenManagement> provider7, Provider<UserProfileManager> provider8) {
        return proxyProvideUserIdentityRepository(identityModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static UserIdentityRepository proxyProvideUserIdentityRepository(IdentityModule identityModule, Context context, CoralService coralService, MarketplaceService marketplaceService, UserIdentityMapper userIdentityMapper, UserIdentityStorage userIdentityStorage, MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, UserProfileManager userProfileManager) {
        return (UserIdentityRepository) Preconditions.checkNotNull(identityModule.provideUserIdentityRepository(context, coralService, marketplaceService, userIdentityMapper, userIdentityStorage, mAPAccountManager, tokenManagement, userProfileManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UserIdentityRepository mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.coralServiceProvider, this.marketplaceServiceProvider, this.userIdentityMapperProvider, this.userStorageProvider, this.mapAccountManagerProvider, this.tokenManagementProvider, this.profileManagerProvider);
    }
}
