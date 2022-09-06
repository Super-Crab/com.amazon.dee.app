package com.amazon.alexa.biloba.view.alerts.edit;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.AlertConfigurationRepo;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertSettingsViewModel_MembersInjector implements MembersInjector<AlertSettingsViewModel> {
    private final Provider<AlertConfigurationRepo> alertConfigurationRepoProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public AlertSettingsViewModel_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationRepo> provider4, Provider<CareActorsStore> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.alertConfigurationRepoProvider = provider4;
        this.careActorsStoreProvider = provider5;
    }

    public static MembersInjector<AlertSettingsViewModel> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationRepo> provider4, Provider<CareActorsStore> provider5) {
        return new AlertSettingsViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAlertConfigurationRepo(AlertSettingsViewModel alertSettingsViewModel, Lazy<AlertConfigurationRepo> lazy) {
        alertSettingsViewModel.alertConfigurationRepo = lazy;
    }

    public static void injectBilobaMetricsService(AlertSettingsViewModel alertSettingsViewModel, Lazy<BilobaMetricsService> lazy) {
        alertSettingsViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(AlertSettingsViewModel alertSettingsViewModel, Lazy<CareActorsStore> lazy) {
        alertSettingsViewModel.careActorsStore = lazy;
    }

    public static void injectCrashMetadata(AlertSettingsViewModel alertSettingsViewModel, Lazy<CrashMetadata> lazy) {
        alertSettingsViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(AlertSettingsViewModel alertSettingsViewModel, Lazy<CrashReporter> lazy) {
        alertSettingsViewModel.crashReporter = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertSettingsViewModel alertSettingsViewModel) {
        injectCrashReporter(alertSettingsViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(alertSettingsViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(alertSettingsViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectAlertConfigurationRepo(alertSettingsViewModel, DoubleCheck.lazy(this.alertConfigurationRepoProvider));
        injectCareActorsStore(alertSettingsViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
