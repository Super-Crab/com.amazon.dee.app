package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.DevicesApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class DevicesStore_Factory implements Factory<DevicesStore> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<DevicesApi> deviceApiProvider;

    public DevicesStore_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<DevicesApi> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.deviceApiProvider = provider4;
    }

    public static DevicesStore_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<DevicesApi> provider4) {
        return new DevicesStore_Factory(provider, provider2, provider3, provider4);
    }

    public static DevicesStore newDevicesStore() {
        return new DevicesStore();
    }

    public static DevicesStore provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<DevicesApi> provider4) {
        DevicesStore devicesStore = new DevicesStore();
        DevicesStore_MembersInjector.injectCrashReporter(devicesStore, DoubleCheck.lazy(provider));
        DevicesStore_MembersInjector.injectCrashMetadata(devicesStore, DoubleCheck.lazy(provider2));
        DevicesStore_MembersInjector.injectBilobaMetricsService(devicesStore, DoubleCheck.lazy(provider3));
        DevicesStore_MembersInjector.injectDeviceApi(devicesStore, DoubleCheck.lazy(provider4));
        return devicesStore;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DevicesStore mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.deviceApiProvider);
    }
}
