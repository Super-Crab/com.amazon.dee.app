package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.network.api.GroupV2Api;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CareActorsStoreV2_MembersInjector implements MembersInjector<CareActorsStoreV2> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<GroupV2Api> groupApiProvider;
    private final Provider<Gson> gsonProvider;

    public CareActorsStoreV2_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupV2Api> provider4, Provider<Gson> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.groupApiProvider = provider4;
        this.gsonProvider = provider5;
    }

    public static MembersInjector<CareActorsStoreV2> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupV2Api> provider4, Provider<Gson> provider5) {
        return new CareActorsStoreV2_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectBilobaMetricsService(CareActorsStoreV2 careActorsStoreV2, Lazy<BilobaMetricsService> lazy) {
        careActorsStoreV2.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(CareActorsStoreV2 careActorsStoreV2, Lazy<CrashMetadata> lazy) {
        careActorsStoreV2.crashMetadata = lazy;
    }

    public static void injectCrashReporter(CareActorsStoreV2 careActorsStoreV2, Lazy<CrashReporter> lazy) {
        careActorsStoreV2.crashReporter = lazy;
    }

    public static void injectGroupApi(CareActorsStoreV2 careActorsStoreV2, Lazy<GroupV2Api> lazy) {
        careActorsStoreV2.groupApi = lazy;
    }

    public static void injectGson(CareActorsStoreV2 careActorsStoreV2, Lazy<Gson> lazy) {
        careActorsStoreV2.gson = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CareActorsStoreV2 careActorsStoreV2) {
        injectCrashReporter(careActorsStoreV2, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(careActorsStoreV2, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(careActorsStoreV2, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectGroupApi(careActorsStoreV2, DoubleCheck.lazy(this.groupApiProvider));
        injectGson(careActorsStoreV2, DoubleCheck.lazy(this.gsonProvider));
    }
}
