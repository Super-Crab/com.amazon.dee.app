package com.amazon.alexa.biloba.view.comms;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class EmergencyViewModel_MembersInjector implements MembersInjector<EmergencyViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsStore> commsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<BilobaUrlResolver> urlResolverProvider;

    public EmergencyViewModel_MembersInjector(Provider<BilobaUrlResolver> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CommsStore> provider5, Provider<CareActorsStore> provider6, Provider<EnvironmentService> provider7, Provider<FeatureServiceV2> provider8) {
        this.urlResolverProvider = provider;
        this.crashReporterProvider = provider2;
        this.crashMetadataProvider = provider3;
        this.bilobaMetricsServiceProvider = provider4;
        this.commsStoreProvider = provider5;
        this.careActorsStoreProvider = provider6;
        this.environmentServiceProvider = provider7;
        this.featureServiceV2Provider = provider8;
    }

    public static MembersInjector<EmergencyViewModel> create(Provider<BilobaUrlResolver> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CommsStore> provider5, Provider<CareActorsStore> provider6, Provider<EnvironmentService> provider7, Provider<FeatureServiceV2> provider8) {
        return new EmergencyViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectBilobaMetricsService(EmergencyViewModel emergencyViewModel, Lazy<BilobaMetricsService> lazy) {
        emergencyViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(EmergencyViewModel emergencyViewModel, Lazy<CareActorsStore> lazy) {
        emergencyViewModel.careActorsStore = lazy;
    }

    public static void injectCommsStore(EmergencyViewModel emergencyViewModel, Lazy<CommsStore> lazy) {
        emergencyViewModel.commsStore = lazy;
    }

    public static void injectCrashMetadata(EmergencyViewModel emergencyViewModel, Lazy<CrashMetadata> lazy) {
        emergencyViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(EmergencyViewModel emergencyViewModel, Lazy<CrashReporter> lazy) {
        emergencyViewModel.crashReporter = lazy;
    }

    public static void injectEnvironmentService(EmergencyViewModel emergencyViewModel, Lazy<EnvironmentService> lazy) {
        emergencyViewModel.environmentService = lazy;
    }

    public static void injectFeatureServiceV2(EmergencyViewModel emergencyViewModel, Lazy<FeatureServiceV2> lazy) {
        emergencyViewModel.featureServiceV2 = lazy;
    }

    public static void injectUrlResolver(EmergencyViewModel emergencyViewModel, Lazy<BilobaUrlResolver> lazy) {
        emergencyViewModel.urlResolver = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EmergencyViewModel emergencyViewModel) {
        injectUrlResolver(emergencyViewModel, DoubleCheck.lazy(this.urlResolverProvider));
        injectCrashReporter(emergencyViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(emergencyViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(emergencyViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCommsStore(emergencyViewModel, DoubleCheck.lazy(this.commsStoreProvider));
        injectCareActorsStore(emergencyViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectEnvironmentService(emergencyViewModel, DoubleCheck.lazy(this.environmentServiceProvider));
        injectFeatureServiceV2(emergencyViewModel, DoubleCheck.lazy(this.featureServiceV2Provider));
    }
}
