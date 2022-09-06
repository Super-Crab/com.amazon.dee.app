package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.TodaysActivitiesApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TodaysActivitiesStore_Factory implements Factory<TodaysActivitiesStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<TodaysActivitiesApi> todaysActivitiesApiProvider;

    public TodaysActivitiesStore_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TodaysActivitiesApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.todaysActivitiesApiProvider = provider4;
    }

    public static TodaysActivitiesStore_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TodaysActivitiesApi> provider4) {
        return new TodaysActivitiesStore_Factory(provider, provider2, provider3, provider4);
    }

    public static TodaysActivitiesStore newTodaysActivitiesStore() {
        return new TodaysActivitiesStore();
    }

    public static TodaysActivitiesStore provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TodaysActivitiesApi> provider4) {
        TodaysActivitiesStore todaysActivitiesStore = new TodaysActivitiesStore();
        TodaysActivitiesStore_MembersInjector.injectCrashReporter(todaysActivitiesStore, DoubleCheck.lazy(provider));
        TodaysActivitiesStore_MembersInjector.injectCrashMetadata(todaysActivitiesStore, DoubleCheck.lazy(provider2));
        TodaysActivitiesStore_MembersInjector.injectBilobaMetricsService(todaysActivitiesStore, DoubleCheck.lazy(provider3));
        TodaysActivitiesStore_MembersInjector.injectTodaysActivitiesApi(todaysActivitiesStore, DoubleCheck.lazy(provider4));
        return todaysActivitiesStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TodaysActivitiesStore mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.todaysActivitiesApiProvider);
    }
}
