package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.TodaysActivitiesApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TodaysActivitiesStore_MembersInjector implements MembersInjector<TodaysActivitiesStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<TodaysActivitiesApi> todaysActivitiesApiProvider;

    public TodaysActivitiesStore_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TodaysActivitiesApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.todaysActivitiesApiProvider = provider4;
    }

    public static MembersInjector<TodaysActivitiesStore> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TodaysActivitiesApi> provider4) {
        return new TodaysActivitiesStore_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectBilobaMetricsService(TodaysActivitiesStore todaysActivitiesStore, Lazy<BilobaMetricsService> lazy) {
        todaysActivitiesStore.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(TodaysActivitiesStore todaysActivitiesStore, Lazy<CrashMetadata> lazy) {
        todaysActivitiesStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(TodaysActivitiesStore todaysActivitiesStore, Lazy<CrashReporter> lazy) {
        todaysActivitiesStore.crashReporter = lazy;
    }

    public static void injectTodaysActivitiesApi(TodaysActivitiesStore todaysActivitiesStore, Lazy<TodaysActivitiesApi> lazy) {
        todaysActivitiesStore.todaysActivitiesApi = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TodaysActivitiesStore todaysActivitiesStore) {
        injectCrashReporter(todaysActivitiesStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(todaysActivitiesStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(todaysActivitiesStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectTodaysActivitiesApi(todaysActivitiesStore, DoubleCheck.lazy(this.todaysActivitiesApiProvider));
    }
}
