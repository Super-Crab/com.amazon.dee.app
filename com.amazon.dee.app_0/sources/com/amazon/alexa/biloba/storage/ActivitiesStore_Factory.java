package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.ActivityApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ActivitiesStore_Factory implements Factory<ActivitiesStore> {
    private final Provider<ActivityApi> activityApiProvider;
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public ActivitiesStore_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivityApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.activityApiProvider = provider4;
    }

    public static ActivitiesStore_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivityApi> provider4) {
        return new ActivitiesStore_Factory(provider, provider2, provider3, provider4);
    }

    public static ActivitiesStore newActivitiesStore() {
        return new ActivitiesStore();
    }

    public static ActivitiesStore provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<ActivityApi> provider4) {
        ActivitiesStore activitiesStore = new ActivitiesStore();
        ActivitiesStore_MembersInjector.injectCrashReporter(activitiesStore, DoubleCheck.lazy(provider));
        ActivitiesStore_MembersInjector.injectCrashMetadata(activitiesStore, DoubleCheck.lazy(provider2));
        ActivitiesStore_MembersInjector.injectBilobaMetricsService(activitiesStore, DoubleCheck.lazy(provider3));
        ActivitiesStore_MembersInjector.injectActivityApi(activitiesStore, DoubleCheck.lazy(provider4));
        return activitiesStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActivitiesStore mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.activityApiProvider);
    }
}
