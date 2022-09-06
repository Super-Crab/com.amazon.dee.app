package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.CommsApi;
import com.amazon.alexa.biloba.generated.network.api.EmergencySettingsApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CommsStore_Factory implements Factory<CommsStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CommsApi> commsApiProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<EmergencySettingsApi> emergencySettingsApiProvider;

    public CommsStore_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CommsApi> provider4, Provider<EmergencySettingsApi> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.commsApiProvider = provider4;
        this.emergencySettingsApiProvider = provider5;
    }

    public static CommsStore_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CommsApi> provider4, Provider<EmergencySettingsApi> provider5) {
        return new CommsStore_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CommsStore newCommsStore() {
        return new CommsStore();
    }

    public static CommsStore provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CommsApi> provider4, Provider<EmergencySettingsApi> provider5) {
        CommsStore commsStore = new CommsStore();
        CommsStore_MembersInjector.injectCrashReporter(commsStore, DoubleCheck.lazy(provider));
        CommsStore_MembersInjector.injectCrashMetadata(commsStore, DoubleCheck.lazy(provider2));
        CommsStore_MembersInjector.injectBilobaMetricsService(commsStore, DoubleCheck.lazy(provider3));
        CommsStore_MembersInjector.injectCommsApi(commsStore, DoubleCheck.lazy(provider4));
        CommsStore_MembersInjector.injectEmergencySettingsApi(commsStore, DoubleCheck.lazy(provider5));
        return commsStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsStore mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.commsApiProvider, this.emergencySettingsApiProvider);
    }
}
