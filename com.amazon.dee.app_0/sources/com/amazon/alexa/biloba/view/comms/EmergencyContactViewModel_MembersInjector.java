package com.amazon.alexa.biloba.view.comms;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class EmergencyContactViewModel_MembersInjector implements MembersInjector<EmergencyContactViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsStore> commsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<BilobaUrlResolver> urlResolverProvider;

    public EmergencyContactViewModel_MembersInjector(Provider<BilobaUrlResolver> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CommsStore> provider5, Provider<CareActorsStore> provider6) {
        this.urlResolverProvider = provider;
        this.crashReporterProvider = provider2;
        this.crashMetadataProvider = provider3;
        this.bilobaMetricsServiceProvider = provider4;
        this.commsStoreProvider = provider5;
        this.careActorsStoreProvider = provider6;
    }

    public static MembersInjector<EmergencyContactViewModel> create(Provider<BilobaUrlResolver> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CommsStore> provider5, Provider<CareActorsStore> provider6) {
        return new EmergencyContactViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectBilobaMetricsService(EmergencyContactViewModel emergencyContactViewModel, Lazy<BilobaMetricsService> lazy) {
        emergencyContactViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(EmergencyContactViewModel emergencyContactViewModel, Lazy<CareActorsStore> lazy) {
        emergencyContactViewModel.careActorsStore = lazy;
    }

    public static void injectCommsStore(EmergencyContactViewModel emergencyContactViewModel, Lazy<CommsStore> lazy) {
        emergencyContactViewModel.commsStore = lazy;
    }

    public static void injectCrashMetadata(EmergencyContactViewModel emergencyContactViewModel, Lazy<CrashMetadata> lazy) {
        emergencyContactViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(EmergencyContactViewModel emergencyContactViewModel, Lazy<CrashReporter> lazy) {
        emergencyContactViewModel.crashReporter = lazy;
    }

    public static void injectUrlResolver(EmergencyContactViewModel emergencyContactViewModel, Lazy<BilobaUrlResolver> lazy) {
        emergencyContactViewModel.urlResolver = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EmergencyContactViewModel emergencyContactViewModel) {
        injectUrlResolver(emergencyContactViewModel, DoubleCheck.lazy(this.urlResolverProvider));
        injectCrashReporter(emergencyContactViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(emergencyContactViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(emergencyContactViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCommsStore(emergencyContactViewModel, DoubleCheck.lazy(this.commsStoreProvider));
        injectCareActorsStore(emergencyContactViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
