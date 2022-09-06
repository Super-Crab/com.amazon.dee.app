package com.amazon.alexa.biloba.view.dashboard;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.CardsStore;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.storage.TodaysActivitiesStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class BilobaDashboardViewModel_MembersInjector implements MembersInjector<BilobaDashboardViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CardsStore> cardsStoreProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsStore> commsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<SettingsStore> settingsStoreProvider;
    private final Provider<TodaysActivitiesStore> todaysActivitiesStoreProvider;

    public BilobaDashboardViewModel_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CareActorsStore> provider4, Provider<TodaysActivitiesStore> provider5, Provider<CardsStore> provider6, Provider<SettingsStore> provider7, Provider<CommsStore> provider8, Provider<FeatureServiceV2> provider9) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.careActorsStoreProvider = provider4;
        this.todaysActivitiesStoreProvider = provider5;
        this.cardsStoreProvider = provider6;
        this.settingsStoreProvider = provider7;
        this.commsStoreProvider = provider8;
        this.featureServiceV2Provider = provider9;
    }

    public static MembersInjector<BilobaDashboardViewModel> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CareActorsStore> provider4, Provider<TodaysActivitiesStore> provider5, Provider<CardsStore> provider6, Provider<SettingsStore> provider7, Provider<CommsStore> provider8, Provider<FeatureServiceV2> provider9) {
        return new BilobaDashboardViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectBilobaMetricsService(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<BilobaMetricsService> lazy) {
        bilobaDashboardViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCardsStore(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<CardsStore> lazy) {
        bilobaDashboardViewModel.cardsStore = lazy;
    }

    public static void injectCareActorsStore(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<CareActorsStore> lazy) {
        bilobaDashboardViewModel.careActorsStore = lazy;
    }

    public static void injectCommsStore(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<CommsStore> lazy) {
        bilobaDashboardViewModel.commsStore = lazy;
    }

    public static void injectCrashMetadata(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<CrashMetadata> lazy) {
        bilobaDashboardViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<CrashReporter> lazy) {
        bilobaDashboardViewModel.crashReporter = lazy;
    }

    public static void injectFeatureServiceV2(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<FeatureServiceV2> lazy) {
        bilobaDashboardViewModel.featureServiceV2 = lazy;
    }

    public static void injectSettingsStore(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<SettingsStore> lazy) {
        bilobaDashboardViewModel.settingsStore = lazy;
    }

    public static void injectTodaysActivitiesStore(BilobaDashboardViewModel bilobaDashboardViewModel, Lazy<TodaysActivitiesStore> lazy) {
        bilobaDashboardViewModel.todaysActivitiesStore = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BilobaDashboardViewModel bilobaDashboardViewModel) {
        injectCrashReporter(bilobaDashboardViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(bilobaDashboardViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(bilobaDashboardViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCareActorsStore(bilobaDashboardViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectTodaysActivitiesStore(bilobaDashboardViewModel, DoubleCheck.lazy(this.todaysActivitiesStoreProvider));
        injectCardsStore(bilobaDashboardViewModel, DoubleCheck.lazy(this.cardsStoreProvider));
        injectSettingsStore(bilobaDashboardViewModel, DoubleCheck.lazy(this.settingsStoreProvider));
        injectCommsStore(bilobaDashboardViewModel, DoubleCheck.lazy(this.commsStoreProvider));
        injectFeatureServiceV2(bilobaDashboardViewModel, DoubleCheck.lazy(this.featureServiceV2Provider));
    }
}
