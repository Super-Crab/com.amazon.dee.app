package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.ActivityApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ActivitiesStore_MembersInjector implements MembersInjector<ActivitiesStore> {
    private final Provider<ActivityApi> activityApiProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public ActivitiesStore_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivityApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.activityApiProvider = provider4;
    }

    public static MembersInjector<ActivitiesStore> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivityApi> provider4) {
        return new ActivitiesStore_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectActivityApi(ActivitiesStore activitiesStore, Lazy<ActivityApi> lazy) {
        activitiesStore.activityApi = lazy;
    }

    public static void injectBilobaMetricsService(ActivitiesStore activitiesStore, Lazy<BilobaMetricsService> lazy) {
        activitiesStore.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(ActivitiesStore activitiesStore, Lazy<CrashMetadata> lazy) {
        activitiesStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(ActivitiesStore activitiesStore, Lazy<CrashReporter> lazy) {
        activitiesStore.crashReporter = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActivitiesStore activitiesStore) {
        injectCrashReporter(activitiesStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(activitiesStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(activitiesStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectActivityApi(activitiesStore, DoubleCheck.lazy(this.activityApiProvider));
    }
}
