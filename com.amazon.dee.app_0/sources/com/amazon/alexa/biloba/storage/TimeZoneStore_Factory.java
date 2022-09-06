package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.TimeZoneApi;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TimeZoneStore_Factory implements Factory<TimeZoneStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<TimeZoneApi> timeZoneApiProvider;

    public TimeZoneStore_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TimeZoneApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.timeZoneApiProvider = provider4;
    }

    public static TimeZoneStore_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TimeZoneApi> provider4) {
        return new TimeZoneStore_Factory(provider, provider2, provider3, provider4);
    }

    public static TimeZoneStore newTimeZoneStore() {
        return new TimeZoneStore();
    }

    public static TimeZoneStore provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<TimeZoneApi> provider4) {
        TimeZoneStore timeZoneStore = new TimeZoneStore();
        TimeZoneStore_MembersInjector.injectCrashReporter(timeZoneStore, DoubleCheck.lazy(provider));
        TimeZoneStore_MembersInjector.injectCrashMetadata(timeZoneStore, DoubleCheck.lazy(provider2));
        TimeZoneStore_MembersInjector.injectBilobaMetricsService(timeZoneStore, DoubleCheck.lazy(provider3));
        TimeZoneStore_MembersInjector.injectTimeZoneApi(timeZoneStore, DoubleCheck.lazy(provider4));
        return timeZoneStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimeZoneStore mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.timeZoneApiProvider);
    }
}
