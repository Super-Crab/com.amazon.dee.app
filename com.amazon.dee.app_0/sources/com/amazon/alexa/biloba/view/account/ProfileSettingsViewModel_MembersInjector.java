package com.amazon.alexa.biloba.view.account;

import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ProfileSettingsViewModel_MembersInjector implements MembersInjector<ProfileSettingsViewModel> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsStore> commsStoreProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<SettingsStore> settingsStoreProvider;
    private final Provider<UrlHelper> urlHelperProvider;

    public ProfileSettingsViewModel_MembersInjector(Provider<CareActorsStore> provider, Provider<SettingsStore> provider2, Provider<CommsStore> provider3, Provider<RoutingService> provider4, Provider<UrlHelper> provider5, Provider<FeatureServiceV2> provider6, Provider<EnvironmentService> provider7) {
        this.careActorsStoreProvider = provider;
        this.settingsStoreProvider = provider2;
        this.commsStoreProvider = provider3;
        this.routingServiceProvider = provider4;
        this.urlHelperProvider = provider5;
        this.featureServiceV2Provider = provider6;
        this.environmentServiceProvider = provider7;
    }

    public static MembersInjector<ProfileSettingsViewModel> create(Provider<CareActorsStore> provider, Provider<SettingsStore> provider2, Provider<CommsStore> provider3, Provider<RoutingService> provider4, Provider<UrlHelper> provider5, Provider<FeatureServiceV2> provider6, Provider<EnvironmentService> provider7) {
        return new ProfileSettingsViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectCareActorsStore(ProfileSettingsViewModel profileSettingsViewModel, Lazy<CareActorsStore> lazy) {
        profileSettingsViewModel.careActorsStore = lazy;
    }

    public static void injectCommsStore(ProfileSettingsViewModel profileSettingsViewModel, Lazy<CommsStore> lazy) {
        profileSettingsViewModel.commsStore = lazy;
    }

    public static void injectEnvironmentService(ProfileSettingsViewModel profileSettingsViewModel, Lazy<EnvironmentService> lazy) {
        profileSettingsViewModel.environmentService = lazy;
    }

    public static void injectFeatureServiceV2(ProfileSettingsViewModel profileSettingsViewModel, Lazy<FeatureServiceV2> lazy) {
        profileSettingsViewModel.featureServiceV2 = lazy;
    }

    public static void injectRoutingService(ProfileSettingsViewModel profileSettingsViewModel, Lazy<RoutingService> lazy) {
        profileSettingsViewModel.routingService = lazy;
    }

    public static void injectSettingsStore(ProfileSettingsViewModel profileSettingsViewModel, Lazy<SettingsStore> lazy) {
        profileSettingsViewModel.settingsStore = lazy;
    }

    public static void injectUrlHelper(ProfileSettingsViewModel profileSettingsViewModel, Lazy<UrlHelper> lazy) {
        profileSettingsViewModel.urlHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ProfileSettingsViewModel profileSettingsViewModel) {
        injectCareActorsStore(profileSettingsViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectSettingsStore(profileSettingsViewModel, DoubleCheck.lazy(this.settingsStoreProvider));
        injectCommsStore(profileSettingsViewModel, DoubleCheck.lazy(this.commsStoreProvider));
        injectRoutingService(profileSettingsViewModel, DoubleCheck.lazy(this.routingServiceProvider));
        injectUrlHelper(profileSettingsViewModel, DoubleCheck.lazy(this.urlHelperProvider));
        injectFeatureServiceV2(profileSettingsViewModel, DoubleCheck.lazy(this.featureServiceV2Provider));
        injectEnvironmentService(profileSettingsViewModel, DoubleCheck.lazy(this.environmentServiceProvider));
    }
}
