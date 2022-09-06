package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.TimeZoneApi;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TimeZoneStore_MembersInjector implements MembersInjector<TimeZoneStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<TimeZoneApi> timeZoneApiProvider;

    public TimeZoneStore_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TimeZoneApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.timeZoneApiProvider = provider4;
    }

    public static MembersInjector<TimeZoneStore> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TimeZoneApi> provider4) {
        return new TimeZoneStore_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectBilobaMetricsService(TimeZoneStore timeZoneStore, Lazy<BilobaMetricsService> lazy) {
        timeZoneStore.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(TimeZoneStore timeZoneStore, Lazy<CrashMetadata> lazy) {
        timeZoneStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(TimeZoneStore timeZoneStore, Lazy<CrashReporter> lazy) {
        timeZoneStore.crashReporter = lazy;
    }

    public static void injectTimeZoneApi(TimeZoneStore timeZoneStore, Lazy<TimeZoneApi> lazy) {
        timeZoneStore.timeZoneApi = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TimeZoneStore timeZoneStore) {
        injectCrashReporter(timeZoneStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(timeZoneStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(timeZoneStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectTimeZoneApi(timeZoneStore, DoubleCheck.lazy(this.timeZoneApiProvider));
    }
}
