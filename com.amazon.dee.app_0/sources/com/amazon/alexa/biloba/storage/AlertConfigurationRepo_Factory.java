package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.AlertConfigurationApi;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertConfigurationRepo_Factory implements Factory<AlertConfigurationRepo> {
    private final Provider<AlertConfigurationApi> alertConfigurationApiProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public AlertConfigurationRepo_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.alertConfigurationApiProvider = provider4;
    }

    public static AlertConfigurationRepo_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationApi> provider4) {
        return new AlertConfigurationRepo_Factory(provider, provider2, provider3, provider4);
    }

    public static AlertConfigurationRepo newAlertConfigurationRepo() {
        return new AlertConfigurationRepo();
    }

    public static AlertConfigurationRepo provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<AlertConfigurationApi> provider4) {
        AlertConfigurationRepo alertConfigurationRepo = new AlertConfigurationRepo();
        AlertConfigurationRepo_MembersInjector.injectCrashReporter(alertConfigurationRepo, DoubleCheck.lazy(provider));
        AlertConfigurationRepo_MembersInjector.injectCrashMetadata(alertConfigurationRepo, DoubleCheck.lazy(provider2));
        AlertConfigurationRepo_MembersInjector.injectBilobaMetricsService(alertConfigurationRepo, DoubleCheck.lazy(provider3));
        AlertConfigurationRepo_MembersInjector.injectAlertConfigurationApi(alertConfigurationRepo, DoubleCheck.lazy(provider4));
        return alertConfigurationRepo;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertConfigurationRepo mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.alertConfigurationApiProvider);
    }
}
