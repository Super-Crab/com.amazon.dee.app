package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.GroupApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CareActorsStoreV1_MembersInjector implements MembersInjector<CareActorsStoreV1> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<GroupApi> groupApiProvider;
    private final Provider<Gson> gsonProvider;

    public CareActorsStoreV1_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupApi> provider4, Provider<Gson> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.groupApiProvider = provider4;
        this.gsonProvider = provider5;
    }

    public static MembersInjector<CareActorsStoreV1> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupApi> provider4, Provider<Gson> provider5) {
        return new CareActorsStoreV1_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectBilobaMetricsService(CareActorsStoreV1 careActorsStoreV1, Lazy<BilobaMetricsService> lazy) {
        careActorsStoreV1.bilobaMetricsService = lazy;
    }

    public static void injectCrashMetadata(CareActorsStoreV1 careActorsStoreV1, Lazy<CrashMetadata> lazy) {
        careActorsStoreV1.crashMetadata = lazy;
    }

    public static void injectCrashReporter(CareActorsStoreV1 careActorsStoreV1, Lazy<CrashReporter> lazy) {
        careActorsStoreV1.crashReporter = lazy;
    }

    public static void injectGroupApi(CareActorsStoreV1 careActorsStoreV1, Lazy<GroupApi> lazy) {
        careActorsStoreV1.groupApi = lazy;
    }

    public static void injectGson(CareActorsStoreV1 careActorsStoreV1, Lazy<Gson> lazy) {
        careActorsStoreV1.gson = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CareActorsStoreV1 careActorsStoreV1) {
        injectCrashReporter(careActorsStoreV1, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(careActorsStoreV1, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(careActorsStoreV1, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectGroupApi(careActorsStoreV1, DoubleCheck.lazy(this.groupApiProvider));
        injectGson(careActorsStoreV1, DoubleCheck.lazy(this.gsonProvider));
    }
}
