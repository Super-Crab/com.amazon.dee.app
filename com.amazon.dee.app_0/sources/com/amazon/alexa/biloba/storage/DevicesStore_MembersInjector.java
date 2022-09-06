package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.DevicesApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DevicesStore_MembersInjector implements MembersInjector<DevicesStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<DevicesApi> deviceApiProvider;

    public DevicesStore_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<DevicesApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.deviceApiProvider = provider4;
    }

    public static MembersInjector<DevicesStore> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<DevicesApi> provider4) {
        return new DevicesStore_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectBilobaMetricsService(DevicesStore devicesStore, Lazy<BilobaMetricsService> lazy) {
        devicesStore.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(DevicesStore devicesStore, Lazy<CrashMetadata> lazy) {
        devicesStore.crashMetadata = lazy;
    }

    public static void injectCrashReporter(DevicesStore devicesStore, Lazy<CrashReporter> lazy) {
        devicesStore.crashReporter = lazy;
    }

    public static void injectDeviceApi(DevicesStore devicesStore, Lazy<DevicesApi> lazy) {
        devicesStore.deviceApi = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DevicesStore devicesStore) {
        injectCrashReporter(devicesStore, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(devicesStore, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(devicesStore, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectDeviceApi(devicesStore, DoubleCheck.lazy(this.deviceApiProvider));
    }
}
