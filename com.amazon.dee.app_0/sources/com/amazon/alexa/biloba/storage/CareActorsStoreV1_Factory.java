package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.generated.network.api.GroupApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CareActorsStoreV1_Factory implements Factory<CareActorsStoreV1> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<GroupApi> groupApiProvider;
    private final Provider<Gson> gsonProvider;

    public CareActorsStoreV1_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupApi> provider4, Provider<Gson> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.groupApiProvider = provider4;
        this.gsonProvider = provider5;
    }

    public static CareActorsStoreV1_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupApi> provider4, Provider<Gson> provider5) {
        return new CareActorsStoreV1_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CareActorsStoreV1 newCareActorsStoreV1() {
        return new CareActorsStoreV1();
    }

    public static CareActorsStoreV1 provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupApi> provider4, Provider<Gson> provider5) {
        CareActorsStoreV1 careActorsStoreV1 = new CareActorsStoreV1();
        CareActorsStoreV1_MembersInjector.injectCrashReporter(careActorsStoreV1, DoubleCheck.lazy(provider));
        CareActorsStoreV1_MembersInjector.injectCrashMetadata(careActorsStoreV1, DoubleCheck.lazy(provider2));
        CareActorsStoreV1_MembersInjector.injectBilobaMetricsService(careActorsStoreV1, DoubleCheck.lazy(provider3));
        CareActorsStoreV1_MembersInjector.injectGroupApi(careActorsStoreV1, DoubleCheck.lazy(provider4));
        CareActorsStoreV1_MembersInjector.injectGson(careActorsStoreV1, DoubleCheck.lazy(provider5));
        return careActorsStoreV1;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CareActorsStoreV1 mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.groupApiProvider, this.gsonProvider);
    }
}
