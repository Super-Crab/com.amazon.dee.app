package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.network.api.GroupV2Api;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CareActorsStoreV2_Factory implements Factory<CareActorsStoreV2> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<GroupV2Api> groupApiProvider;
    private final Provider<Gson> gsonProvider;

    public CareActorsStoreV2_Factory(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupV2Api> provider4, Provider<Gson> provider5) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.groupApiProvider = provider4;
        this.gsonProvider = provider5;
    }

    public static CareActorsStoreV2_Factory create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupV2Api> provider4, Provider<Gson> provider5) {
        return new CareActorsStoreV2_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CareActorsStoreV2 newCareActorsStoreV2() {
        return new CareActorsStoreV2();
    }

    public static CareActorsStoreV2 provideInstance(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<GroupV2Api> provider4, Provider<Gson> provider5) {
        CareActorsStoreV2 careActorsStoreV2 = new CareActorsStoreV2();
        CareActorsStoreV2_MembersInjector.injectCrashReporter(careActorsStoreV2, DoubleCheck.lazy(provider));
        CareActorsStoreV2_MembersInjector.injectCrashMetadata(careActorsStoreV2, DoubleCheck.lazy(provider2));
        CareActorsStoreV2_MembersInjector.injectBilobaMetricsService(careActorsStoreV2, DoubleCheck.lazy(provider3));
        CareActorsStoreV2_MembersInjector.injectGroupApi(careActorsStoreV2, DoubleCheck.lazy(provider4));
        CareActorsStoreV2_MembersInjector.injectGson(careActorsStoreV2, DoubleCheck.lazy(provider5));
        return careActorsStoreV2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CareActorsStoreV2 mo10268get() {
        return provideInstance(this.crashReporterProvider, this.crashMetadataProvider, this.bilobaMetricsServiceProvider, this.groupApiProvider, this.gsonProvider);
    }
}
