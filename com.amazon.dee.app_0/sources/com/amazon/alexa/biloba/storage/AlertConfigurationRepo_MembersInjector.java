package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.AlertConfigurationApi;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertConfigurationRepo_MembersInjector implements MembersInjector<AlertConfigurationRepo> {
    private final Provider<AlertConfigurationApi> alertConfigurationApiProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public AlertConfigurationRepo_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.alertConfigurationApiProvider = provider4;
    }

    public static MembersInjector<AlertConfigurationRepo> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationApi> provider4) {
        return new AlertConfigurationRepo_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectAlertConfigurationApi(AlertConfigurationRepo alertConfigurationRepo, Lazy<AlertConfigurationApi> lazy) {
        alertConfigurationRepo.alertConfigurationApi = lazy;
    }

    public static void injectBilobaMetricsService(AlertConfigurationRepo alertConfigurationRepo, Lazy<BilobaMetricsService> lazy) {
        alertConfigurationRepo.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(AlertConfigurationRepo alertConfigurationRepo, Lazy<CrashMetadata> lazy) {
        alertConfigurationRepo.crashMetadata = lazy;
    }

    public static void injectCrashReporter(AlertConfigurationRepo alertConfigurationRepo, Lazy<CrashReporter> lazy) {
        alertConfigurationRepo.crashReporter = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertConfigurationRepo alertConfigurationRepo) {
        injectCrashReporter(alertConfigurationRepo, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(alertConfigurationRepo, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(alertConfigurationRepo, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectAlertConfigurationApi(alertConfigurationRepo, DoubleCheck.lazy(this.alertConfigurationApiProvider));
    }
}
