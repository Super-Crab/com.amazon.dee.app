package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement;
import com.amazon.alexa.identity.ApesCallerInterface;
import com.amazon.alexa.identity.MAPAccountRegistrationService;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.api.UserIdentityRepository;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.alexa.protocols.datastore.DataStoreService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class IdentityModule_ProvideImplementationMAPIdentityServiceFactory implements Factory<MAPIdentityService> {
    private final Provider<MAPAccountRegistrationService> accountRegistrationServiceProvider;
    private final Provider<ApesCallerInterface> apesCallerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CookieManager> cookieManagerProvider;
    private final Provider<DataStoreService> dataStoreServiceProvider;
    private final Provider<DelegatedTokenManagement> delegatedTokenManagementProvider;
    private final Provider<MAPAccountManager> mapAccountManagerProvider;
    private final IdentityModule module;
    private final Provider<TokenManagement> tokenManagementProvider;
    private final Provider<UserProfileManager> userProfileManagerProvider;
    private final Provider<UserIdentityRepository> userRepositoryProvider;

    public IdentityModule_ProvideImplementationMAPIdentityServiceFactory(IdentityModule identityModule, Provider<Context> provider, Provider<MAPAccountManager> provider2, Provider<CookieManager> provider3, Provider<TokenManagement> provider4, Provider<DataStoreService> provider5, Provider<UserIdentityRepository> provider6, Provider<UserProfileManager> provider7, Provider<MAPAccountRegistrationService> provider8, Provider<DelegatedTokenManagement> provider9, Provider<ApesCallerInterface> provider10) {
        this.module = identityModule;
        this.contextProvider = provider;
        this.mapAccountManagerProvider = provider2;
        this.cookieManagerProvider = provider3;
        this.tokenManagementProvider = provider4;
        this.dataStoreServiceProvider = provider5;
        this.userRepositoryProvider = provider6;
        this.userProfileManagerProvider = provider7;
        this.accountRegistrationServiceProvider = provider8;
        this.delegatedTokenManagementProvider = provider9;
        this.apesCallerProvider = provider10;
    }

    public static IdentityModule_ProvideImplementationMAPIdentityServiceFactory create(IdentityModule identityModule, Provider<Context> provider, Provider<MAPAccountManager> provider2, Provider<CookieManager> provider3, Provider<TokenManagement> provider4, Provider<DataStoreService> provider5, Provider<UserIdentityRepository> provider6, Provider<UserProfileManager> provider7, Provider<MAPAccountRegistrationService> provider8, Provider<DelegatedTokenManagement> provider9, Provider<ApesCallerInterface> provider10) {
        return new IdentityModule_ProvideImplementationMAPIdentityServiceFactory(identityModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static MAPIdentityService provideInstance(IdentityModule identityModule, Provider<Context> provider, Provider<MAPAccountManager> provider2, Provider<CookieManager> provider3, Provider<TokenManagement> provider4, Provider<DataStoreService> provider5, Provider<UserIdentityRepository> provider6, Provider<UserProfileManager> provider7, Provider<MAPAccountRegistrationService> provider8, Provider<DelegatedTokenManagement> provider9, Provider<ApesCallerInterface> provider10) {
        return proxyProvideImplementationMAPIdentityService(identityModule, provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), DoubleCheck.lazy(provider8), provider9.mo10268get(), provider10.mo10268get());
    }

    public static MAPIdentityService proxyProvideImplementationMAPIdentityService(IdentityModule identityModule, Context context, MAPAccountManager mAPAccountManager, Lazy<CookieManager> lazy, TokenManagement tokenManagement, DataStoreService dataStoreService, UserIdentityRepository userIdentityRepository, UserProfileManager userProfileManager, Lazy<MAPAccountRegistrationService> lazy2, DelegatedTokenManagement delegatedTokenManagement, ApesCallerInterface apesCallerInterface) {
        return (MAPIdentityService) Preconditions.checkNotNull(identityModule.provideImplementationMAPIdentityService(context, mAPAccountManager, lazy, tokenManagement, dataStoreService, userIdentityRepository, userProfileManager, lazy2, delegatedTokenManagement, apesCallerInterface), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MAPIdentityService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.mapAccountManagerProvider, this.cookieManagerProvider, this.tokenManagementProvider, this.dataStoreServiceProvider, this.userRepositoryProvider, this.userProfileManagerProvider, this.accountRegistrationServiceProvider, this.delegatedTokenManagementProvider, this.apesCallerProvider);
    }
}
