package com.amazon.alexa.biloba.view.alerts;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.AlertConfigurationRepo;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.DevicesStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsListViewModel_MembersInjector implements MembersInjector<AlertsListViewModel> {
    private final Provider<AlertConfigurationRepo> alertConfigurationRepoProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<DevicesStore> devicesStoreProvider;

    public AlertsListViewModel_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationRepo> provider4, Provider<CareActorsStore> provider5, Provider<DevicesStore> provider6) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.alertConfigurationRepoProvider = provider4;
        this.careActorsStoreProvider = provider5;
        this.devicesStoreProvider = provider6;
    }

    public static MembersInjector<AlertsListViewModel> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationRepo> provider4, Provider<CareActorsStore> provider5, Provider<DevicesStore> provider6) {
        return new AlertsListViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectAlertConfigurationRepo(AlertsListViewModel alertsListViewModel, Lazy<AlertConfigurationRepo> lazy) {
        alertsListViewModel.alertConfigurationRepo = lazy;
    }

    public static void injectBilobaMetricsService(AlertsListViewModel alertsListViewModel, Lazy<BilobaMetricsService> lazy) {
        alertsListViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(AlertsListViewModel alertsListViewModel, Lazy<CareActorsStore> lazy) {
        alertsListViewModel.careActorsStore = lazy;
    }

    public static void injectCrashMetadata(AlertsListViewModel alertsListViewModel, Lazy<CrashMetadata> lazy) {
        alertsListViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(AlertsListViewModel alertsListViewModel, Lazy<CrashReporter> lazy) {
        alertsListViewModel.crashReporter = lazy;
    }

    public static void injectDevicesStore(AlertsListViewModel alertsListViewModel, Lazy<DevicesStore> lazy) {
        alertsListViewModel.devicesStore = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertsListViewModel alertsListViewModel) {
        injectCrashReporter(alertsListViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(alertsListViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(alertsListViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectAlertConfigurationRepo(alertsListViewModel, DoubleCheck.lazy(this.alertConfigurationRepoProvider));
        injectCareActorsStore(alertsListViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectDevicesStore(alertsListViewModel, DoubleCheck.lazy(this.devicesStoreProvider));
    }
}
