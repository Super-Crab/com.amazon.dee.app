package com.amazon.alexa.biloba.view.startup;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.service.PasscodeApi;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.IdentityLocalDataStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class StartupViewModel_MembersInjector implements MembersInjector<StartupViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<DeferredRoutingHelper> deferredRoutingHelperProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<IdentityLocalDataStore> identityLocalDataStoreProvider;
    private final Provider<PasscodeApi> passcodeApiProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public StartupViewModel_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CareActorsStore> provider4, Provider<PasscodeApi> provider5, Provider<IdentityLocalDataStore> provider6, Provider<FeatureServiceV2> provider7, Provider<DeferredRoutingHelper> provider8, Provider<RoutingService> provider9) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.careActorsStoreProvider = provider4;
        this.passcodeApiProvider = provider5;
        this.identityLocalDataStoreProvider = provider6;
        this.featureServiceV2Provider = provider7;
        this.deferredRoutingHelperProvider = provider8;
        this.routingServiceProvider = provider9;
    }

    public static MembersInjector<StartupViewModel> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CareActorsStore> provider4, Provider<PasscodeApi> provider5, Provider<IdentityLocalDataStore> provider6, Provider<FeatureServiceV2> provider7, Provider<DeferredRoutingHelper> provider8, Provider<RoutingService> provider9) {
        return new StartupViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectBilobaMetricsService(StartupViewModel startupViewModel, Lazy<BilobaMetricsService> lazy) {
        startupViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(StartupViewModel startupViewModel, Lazy<CareActorsStore> lazy) {
        startupViewModel.careActorsStore = lazy;
    }

    public static void injectCrashMetadata(StartupViewModel startupViewModel, Lazy<CrashMetadata> lazy) {
        startupViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(StartupViewModel startupViewModel, Lazy<CrashReporter> lazy) {
        startupViewModel.crashReporter = lazy;
    }

    public static void injectDeferredRoutingHelper(StartupViewModel startupViewModel, Lazy<DeferredRoutingHelper> lazy) {
        startupViewModel.deferredRoutingHelper = lazy;
    }

    public static void injectFeatureServiceV2(StartupViewModel startupViewModel, Lazy<FeatureServiceV2> lazy) {
        startupViewModel.featureServiceV2 = lazy;
    }

    public static void injectIdentityLocalDataStore(StartupViewModel startupViewModel, Lazy<IdentityLocalDataStore> lazy) {
        startupViewModel.identityLocalDataStore = lazy;
    }

    public static void injectPasscodeApi(StartupViewModel startupViewModel, Lazy<PasscodeApi> lazy) {
        startupViewModel.passcodeApi = lazy;
    }

    public static void injectRoutingService(StartupViewModel startupViewModel, Lazy<RoutingService> lazy) {
        startupViewModel.routingService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(StartupViewModel startupViewModel) {
        injectCrashReporter(startupViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(startupViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(startupViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCareActorsStore(startupViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectPasscodeApi(startupViewModel, DoubleCheck.lazy(this.passcodeApiProvider));
        injectIdentityLocalDataStore(startupViewModel, DoubleCheck.lazy(this.identityLocalDataStoreProvider));
        injectFeatureServiceV2(startupViewModel, DoubleCheck.lazy(this.featureServiceV2Provider));
        injectDeferredRoutingHelper(startupViewModel, DoubleCheck.lazy(this.deferredRoutingHelperProvider));
        injectRoutingService(startupViewModel, DoubleCheck.lazy(this.routingServiceProvider));
    }
}
