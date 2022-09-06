package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.SettingsApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.dee.app.data.api.ElementLocalStorage;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class SettingsStore_MembersInjector implements MembersInjector<SettingsStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<ElementLocalStorage> elementLocalStorageProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<SettingsApi> settingsApiProvider;

    public SettingsStore_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<SettingsApi> provider4, Provider<ElementLocalStorage> provider5, Provider<FeatureServiceV2> provider6) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.settingsApiProvider = provider4;
        this.elementLocalStorageProvider = provider5;
        this.featureServiceV2Provider = provider6;
    }

    public static MembersInjector<SettingsStore> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<SettingsApi> provider4, Provider<ElementLocalStorage> provider5, Provider<FeatureServiceV2> provider6) {
        return new SettingsStore_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectBilobaMetricsService(SettingsStore settingsStore, Lazy<BilobaMetricsService> lazy) {
        settingsStore.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(SettingsStore settingsStore, Lazy<CrashMetadata> lazy) {
        settingsStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(SettingsStore settingsStore, Lazy<CrashReporter> lazy) {
        settingsStore.crashReporter = lazy;
    }

    public static void injectElementLocalStorage(SettingsStore settingsStore, Lazy<ElementLocalStorage> lazy) {
        settingsStore.elementLocalStorage = lazy;
    }

    public static void injectFeatureServiceV2(SettingsStore settingsStore, Lazy<FeatureServiceV2> lazy) {
        settingsStore.featureServiceV2 = lazy;
    }

    public static void injectSettingsApi(SettingsStore settingsStore, Lazy<SettingsApi> lazy) {
        settingsStore.settingsApi = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SettingsStore settingsStore) {
        injectCrashReporter(settingsStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(settingsStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(settingsStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectSettingsApi(settingsStore, DoubleCheck.lazy(this.settingsApiProvider));
        injectElementLocalStorage(settingsStore, DoubleCheck.lazy(this.elementLocalStorageProvider));
        injectFeatureServiceV2(settingsStore, DoubleCheck.lazy(this.featureServiceV2Provider));
    }
}
