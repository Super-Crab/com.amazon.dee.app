package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusClient;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ArcusModule_ProvidesFFSArcusSyncCoordinatorFactory implements Factory<FFSArcusSyncCoordinator> {
    private final Provider<Clock> clockProvider;
    private final Provider<Context> contextProvider;
    private final Provider<FFSArcusClient> ffsArcusClientProvider;
    private final Provider<JobInfoProvider> jobInfoProvider;
    private final Provider<JobScheduler> jobSchedulerProvider;
    private final ArcusModule module;
    private final Provider<SharedPreferencesProvider> sharedPreferencesProvider;

    public ArcusModule_ProvidesFFSArcusSyncCoordinatorFactory(ArcusModule arcusModule, Provider<JobScheduler> provider, Provider<JobInfoProvider> provider2, Provider<Context> provider3, Provider<SharedPreferencesProvider> provider4, Provider<Clock> provider5, Provider<FFSArcusClient> provider6) {
        this.module = arcusModule;
        this.jobSchedulerProvider = provider;
        this.jobInfoProvider = provider2;
        this.contextProvider = provider3;
        this.sharedPreferencesProvider = provider4;
        this.clockProvider = provider5;
        this.ffsArcusClientProvider = provider6;
    }

    public static ArcusModule_ProvidesFFSArcusSyncCoordinatorFactory create(ArcusModule arcusModule, Provider<JobScheduler> provider, Provider<JobInfoProvider> provider2, Provider<Context> provider3, Provider<SharedPreferencesProvider> provider4, Provider<Clock> provider5, Provider<FFSArcusClient> provider6) {
        return new ArcusModule_ProvidesFFSArcusSyncCoordinatorFactory(arcusModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static FFSArcusSyncCoordinator provideInstance(ArcusModule arcusModule, Provider<JobScheduler> provider, Provider<JobInfoProvider> provider2, Provider<Context> provider3, Provider<SharedPreferencesProvider> provider4, Provider<Clock> provider5, Provider<FFSArcusClient> provider6) {
        return proxyProvidesFFSArcusSyncCoordinator(arcusModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static FFSArcusSyncCoordinator proxyProvidesFFSArcusSyncCoordinator(ArcusModule arcusModule, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, FFSArcusClient fFSArcusClient) {
        return (FFSArcusSyncCoordinator) Preconditions.checkNotNull(arcusModule.providesFFSArcusSyncCoordinator(jobScheduler, jobInfoProvider, context, sharedPreferencesProvider, clock, fFSArcusClient), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FFSArcusSyncCoordinator mo10268get() {
        return provideInstance(this.module, this.jobSchedulerProvider, this.jobInfoProvider, this.contextProvider, this.sharedPreferencesProvider, this.clockProvider, this.ffsArcusClientProvider);
    }
}
