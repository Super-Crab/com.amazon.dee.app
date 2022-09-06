package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.CommsApi;
import com.amazon.alexa.biloba.generated.network.api.EmergencySettingsApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CommsStore_MembersInjector implements MembersInjector<CommsStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CommsApi> commsApiProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<EmergencySettingsApi> emergencySettingsApiProvider;

    public CommsStore_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CommsApi> provider4, Provider<EmergencySettingsApi> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.commsApiProvider = provider4;
        this.emergencySettingsApiProvider = provider5;
    }

    public static MembersInjector<CommsStore> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CommsApi> provider4, Provider<EmergencySettingsApi> provider5) {
        return new CommsStore_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectBilobaMetricsService(CommsStore commsStore, Lazy<BilobaMetricsService> lazy) {
        commsStore.bilobaMetricsService = lazy;
    }

    public static void injectCommsApi(CommsStore commsStore, Lazy<CommsApi> lazy) {
        commsStore.commsApi = lazy;
    }

    public static void injectCrashMetadata(CommsStore commsStore, Lazy<CrashMetadata> lazy) {
        commsStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(CommsStore commsStore, Lazy<CrashReporter> lazy) {
        commsStore.crashReporter = lazy;
    }

    public static void injectEmergencySettingsApi(CommsStore commsStore, Lazy<EmergencySettingsApi> lazy) {
        commsStore.emergencySettingsApi = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsStore commsStore) {
        injectCrashReporter(commsStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(commsStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(commsStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCommsApi(commsStore, DoubleCheck.lazy(this.commsApiProvider));
        injectEmergencySettingsApi(commsStore, DoubleCheck.lazy(this.emergencySettingsApiProvider));
    }
}
