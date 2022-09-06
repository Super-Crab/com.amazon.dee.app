package com.amazon.alexa.biloba.view.recent;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.ActivitiesStore;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class RecentActivityListViewModel_MembersInjector implements MembersInjector<RecentActivityListViewModel> {
    private final Provider<ActivitiesStore> activitiesStoreProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public RecentActivityListViewModel_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivitiesStore> provider4, Provider<CareActorsStore> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.activitiesStoreProvider = provider4;
        this.careActorsStoreProvider = provider5;
    }

    public static MembersInjector<RecentActivityListViewModel> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivitiesStore> provider4, Provider<CareActorsStore> provider5) {
        return new RecentActivityListViewModel_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectActivitiesStore(RecentActivityListViewModel recentActivityListViewModel, Lazy<ActivitiesStore> lazy) {
        recentActivityListViewModel.activitiesStore = lazy;
    }

    public static void injectBilobaMetricsService(RecentActivityListViewModel recentActivityListViewModel, Lazy<BilobaMetricsService> lazy) {
        recentActivityListViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(RecentActivityListViewModel recentActivityListViewModel, Lazy<CareActorsStore> lazy) {
        recentActivityListViewModel.careActorsStore = lazy;
    }

    public static void injectCrashMetadata(RecentActivityListViewModel recentActivityListViewModel, Lazy<CrashMetadata> lazy) {
        recentActivityListViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(RecentActivityListViewModel recentActivityListViewModel, Lazy<CrashReporter> lazy) {
        recentActivityListViewModel.crashReporter = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RecentActivityListViewModel recentActivityListViewModel) {
        injectCrashReporter(recentActivityListViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(recentActivityListViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(recentActivityListViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectActivitiesStore(recentActivityListViewModel, DoubleCheck.lazy(this.activitiesStoreProvider));
        injectCareActorsStore(recentActivityListViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
