package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.webkit.CookieManager;
import com.amazon.alexa.delegatedidentity.DelegatedTokenManagementImpl;
import com.amazon.alexa.delegatedidentity.PIMSTokenAccessor;
import com.amazon.alexa.delegatedidentity.TokenAccessor;
import com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement;
import com.amazon.alexa.delegatedidentity.storage.LocalAndroidKeyValueStore;
import com.amazon.alexa.delegatedidentity.storage.PersistentTokenStorage;
import com.amazon.alexa.delegatedidentity.storage.TokenEncryptor;
import com.amazon.alexa.delegatedidentity.storage.TokenStorage;
import com.amazon.alexa.identity.ApesCaller;
import com.amazon.alexa.identity.ApesCallerInterface;
import com.amazon.alexa.identity.DefaultPersonIdProvider;
import com.amazon.alexa.identity.DefaultUserIdentityRepository;
import com.amazon.alexa.identity.DefaultUserProfileManager;
import com.amazon.alexa.identity.MAPAccountRegistrationService;
import com.amazon.alexa.identity.MAPAccountService;
import com.amazon.alexa.identity.MAPAccountUpgradeService;
import com.amazon.alexa.identity.MAPIdentityService;
import com.amazon.alexa.identity.PersistentUserIdentityStorage;
import com.amazon.alexa.identity.UserIdentityMapper;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.AccountUpgradeAuthority;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.AuthenticationProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentityRepository;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.protocols.datastore.DataStoreService;
import com.amazon.alexa.protocols.features.FeatureConstraints;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.R;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public class IdentityModule {
    public static final String DEVICE_NAME_TEMPLATE = "deviceNameTemplate";

    @Provides
    @ApplicationScope
    public MAPAccountRegistrationService provideAccountRegistrationService(MAPAccountManager mAPAccountManager, @Named("deviceNameTemplate") String str) {
        return new MAPAccountRegistrationService(mAPAccountManager, str);
    }

    @Provides
    @ApplicationScope
    public AccountService provideAccountService(MAPAccountManager mAPAccountManager, AccountUpgradeAuthority accountUpgradeAuthority, Lazy<CookieManager> lazy, CommsManager commsManager, @Named("deviceNameTemplate") String str) {
        return new MAPAccountService(ComponentRegistry.getInstance(), mAPAccountManager, accountUpgradeAuthority, lazy, commsManager, str);
    }

    @Provides
    @ApplicationScope
    public AccountUpgradeAuthority provideAccountUpgradeAuthority(MAPAccountUpgradeService mAPAccountUpgradeService) {
        return mAPAccountUpgradeService;
    }

    @Provides
    @ApplicationScope
    public AccountUpgradeService provideAccountUpgradeService(MAPAccountUpgradeService mAPAccountUpgradeService) {
        return mAPAccountUpgradeService;
    }

    @Provides
    @ApplicationScope
    public ApesCallerInterface provideApesCaller(CoralService coralService, MetricsService metricsService) {
        return new ApesCaller(coralService, metricsService);
    }

    @Provides
    @ApplicationScope
    public AuthenticationProvider provideAuthenticationProvider(MAPIdentityService mAPIdentityService) {
        return mAPIdentityService;
    }

    @Provides
    @ApplicationScope
    public DelegatedTokenManagement provideDelegatedTokenManagement(TokenAccessor tokenAccessor, TokenStorage tokenStorage) {
        return new DelegatedTokenManagementImpl(tokenAccessor, tokenStorage);
    }

    @Provides
    @ApplicationScope
    @Named("deviceNameTemplate")
    public String provideDeviceNameTemplate(Context context) {
        return context.getString(R.string.alexa_app_device_name_template);
    }

    @Provides
    @ApplicationScope
    public IdentityService provideIdentityService(MAPIdentityService mAPIdentityService) {
        return mAPIdentityService;
    }

    @Provides
    @ApplicationScope
    public MAPAccountUpgradeService provideImplementationAccountUpgradeService(MAPAccountManager mAPAccountManager, @Named("deviceNameTemplate") String str) {
        return new MAPAccountUpgradeService(mAPAccountManager, str);
    }

    @Provides
    @ApplicationScope
    public MAPIdentityService provideImplementationMAPIdentityService(Context context, MAPAccountManager mAPAccountManager, Lazy<CookieManager> lazy, TokenManagement tokenManagement, DataStoreService dataStoreService, UserIdentityRepository userIdentityRepository, UserProfileManager userProfileManager, Lazy<MAPAccountRegistrationService> lazy2, DelegatedTokenManagement delegatedTokenManagement, ApesCallerInterface apesCallerInterface) {
        return new MAPIdentityService(ComponentRegistry.getInstance(), mAPAccountManager, lazy, tokenManagement, dataStoreService, userIdentityRepository, userProfileManager, lazy2, delegatedTokenManagement, context, apesCallerInterface);
    }

    @Provides
    @ApplicationScope
    public LocalAndroidKeyValueStore provideLocalAndroidKeyValueStore(Context context) {
        return new LocalAndroidKeyValueStore(context);
    }

    @Provides
    @ApplicationScope
    public MAPAccountManager provideMAPAccountManager(Context context) {
        return new MAPAccountManager(context);
    }

    @Provides
    @ApplicationScope
    public PersonIdProvider providePersonIdProvider(IdentityService identityService, MetricsService metricsService) {
        return new DefaultPersonIdProvider(identityService, metricsService);
    }

    @Provides
    @ApplicationScope
    public TokenAccessor provideTokenAccessor(CoralService coralService) {
        return new PIMSTokenAccessor(ComponentRegistry.getInstance(), coralService);
    }

    @Provides
    @ApplicationScope
    public TokenEncryptor provideTokenEncryptor(LocalAndroidKeyValueStore localAndroidKeyValueStore) {
        return new TokenEncryptor(localAndroidKeyValueStore);
    }

    @Provides
    public TokenManagement provideTokenManagement(Context context) {
        return new TokenManagement(context);
    }

    @Provides
    @ApplicationScope
    public TokenStorage provideTokenStorage(DataStoreService dataStoreService, TokenEncryptor tokenEncryptor) {
        return new PersistentTokenStorage(dataStoreService, tokenEncryptor);
    }

    @Provides
    @ApplicationScope
    public UserIdentityMapper provideUserIdentityMapper(FeatureConstraints featureConstraints) {
        return new UserIdentityMapper(featureConstraints);
    }

    @Provides
    @ApplicationScope
    public UserIdentityRepository provideUserIdentityRepository(Context context, CoralService coralService, MarketplaceService marketplaceService, UserIdentityMapper userIdentityMapper, UserIdentityStorage userIdentityStorage, MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, UserProfileManager userProfileManager) {
        return new DefaultUserIdentityRepository(context, coralService, marketplaceService, userIdentityMapper, userIdentityStorage, mAPAccountManager, tokenManagement, userProfileManager);
    }

    @Provides
    @ApplicationScope
    public UserIdentityStorage provideUserIdentityStorage(PersistentStorage.Factory factory) {
        return new PersistentUserIdentityStorage(factory);
    }

    @Provides
    @ApplicationScope
    public UserProfileManager provideUserProfileManager(CommsManager commsManager, Lazy<CommsServiceV2> lazy) {
        return new DefaultUserProfileManager(commsManager, lazy);
    }
}
