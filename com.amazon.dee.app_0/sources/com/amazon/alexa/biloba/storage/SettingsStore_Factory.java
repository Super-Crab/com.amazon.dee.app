package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.SettingsApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.dee.app.data.api.ElementLocalStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class SettingsStore_Factory implements Factory<SettingsStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<ElementLocalStorage> elementLocalStorageProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<SettingsApi> settingsApiProvider;

    public SettingsStore_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<SettingsApi> provider4, Provider<ElementLocalStorage> provider5, Provider<FeatureServiceV2> provider6) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.settingsApiProvider = provider4;
        this.elementLocalStorageProvider = provider5;
        this.featureServiceV2Provider = provider6;
    }

    public static SettingsStore_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<SettingsApi> provider4, Provider<ElementLocalStorage> provider5, Provider<FeatureServiceV2> provider6) {
        return new SettingsStore_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static SettingsStore newSettingsStore() {
        return new SettingsStore();
    }

    public static SettingsStore provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<SettingsApi> provider4, Provider<ElementLocalStorage> provider5, Provider<FeatureServiceV2> provider6) {
        SettingsStore settingsStore = new SettingsStore();
        SettingsStore_MembersInjector.injectCrashReporter(settingsStore, DoubleCheck.lazy(provider));
        SettingsStore_MembersInjector.injectCrashMetadata(settingsStore, DoubleCheck.lazy(provider2));
        SettingsStore_MembersInjector.injectBilobaMetricsService(settingsStore, DoubleCheck.lazy(provider3));
        SettingsStore_MembersInjector.injectSettingsApi(settingsStore, DoubleCheck.lazy(provider4));
        SettingsStore_MembersInjector.injectElementLocalStorage(settingsStore, DoubleCheck.lazy(provider5));
        SettingsStore_MembersInjector.injectFeatureServiceV2(settingsStore, DoubleCheck.lazy(provider6));
        return settingsStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SettingsStore mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.settingsApiProvider, this.elementLocalStorageProvider, this.featureServiceV2Provider);
    }
}
