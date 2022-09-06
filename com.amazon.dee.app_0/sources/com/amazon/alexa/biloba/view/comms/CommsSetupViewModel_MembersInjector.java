package com.amazon.alexa.biloba.view.comms;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CommsSetupViewModel_MembersInjector implements MembersInjector<CommsSetupViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<BilobaUrlResolver> urlResolverProvider;

    public CommsSetupViewModel_MembersInjector(Provider<BilobaUrlResolver> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        this.urlResolverProvider = provider;
        this.crashReporterProvider = provider2;
        this.crashMetadataProvider = provider3;
        this.bilobaMetricsServiceProvider = provider4;
        this.careActorsStoreProvider = provider5;
    }

    public static MembersInjector<CommsSetupViewModel> create(Provider<BilobaUrlResolver> provider, Provider<CrashReporter> provider2, Provider<CrashMetadata> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        return new CommsSetupViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectBilobaMetricsService(CommsSetupViewModel commsSetupViewModel, Lazy<BilobaMetricsService> lazy) {
        commsSetupViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(CommsSetupViewModel commsSetupViewModel, Lazy<CareActorsStore> lazy) {
        commsSetupViewModel.careActorsStore = lazy;
    }

    public static void injectCrashMetadata(CommsSetupViewModel commsSetupViewModel, Lazy<CrashMetadata> lazy) {
        commsSetupViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(CommsSetupViewModel commsSetupViewModel, Lazy<CrashReporter> lazy) {
        commsSetupViewModel.crashReporter = lazy;
    }

    public static void injectUrlResolver(CommsSetupViewModel commsSetupViewModel, Lazy<BilobaUrlResolver> lazy) {
        commsSetupViewModel.urlResolver = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsSetupViewModel commsSetupViewModel) {
        injectUrlResolver(commsSetupViewModel, DoubleCheck.lazy(this.urlResolverProvider));
        injectCrashReporter(commsSetupViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(commsSetupViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(commsSetupViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCareActorsStore(commsSetupViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
