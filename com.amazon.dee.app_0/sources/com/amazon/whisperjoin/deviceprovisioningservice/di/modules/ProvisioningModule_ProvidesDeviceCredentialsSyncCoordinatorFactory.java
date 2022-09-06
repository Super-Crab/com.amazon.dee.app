package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesDeviceCredentialsSyncCoordinatorFactory implements Factory<ZigbeeCredentialsSyncCoordinator> {
    private final Provider<Clock> clockProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final Provider<JobInfoProvider> jobInfoProvider;
    private final Provider<JobScheduler> jobSchedulerProvider;
    private final ProvisioningModule module;
    private final Provider<ProvisionerInfo> provisionerinfoProvider;
    private final Provider<SharedPreferencesProvider> sharedPreferencesProvider;

    public ProvisioningModule_ProvidesDeviceCredentialsSyncCoordinatorFactory(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisionerInfo> provider2, Provider<DSSClient> provider3, Provider<JobScheduler> provider4, Provider<JobInfoProvider> provider5, Provider<Clock> provider6, Provider<SharedPreferencesProvider> provider7) {
        this.module = provisioningModule;
        this.contextProvider = provider;
        this.provisionerinfoProvider = provider2;
        this.dssClientProvider = provider3;
        this.jobSchedulerProvider = provider4;
        this.jobInfoProvider = provider5;
        this.clockProvider = provider6;
        this.sharedPreferencesProvider = provider7;
    }

    public static ProvisioningModule_ProvidesDeviceCredentialsSyncCoordinatorFactory create(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisionerInfo> provider2, Provider<DSSClient> provider3, Provider<JobScheduler> provider4, Provider<JobInfoProvider> provider5, Provider<Clock> provider6, Provider<SharedPreferencesProvider> provider7) {
        return new ProvisioningModule_ProvidesDeviceCredentialsSyncCoordinatorFactory(provisioningModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static ZigbeeCredentialsSyncCoordinator provideInstance(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<ProvisionerInfo> provider2, Provider<DSSClient> provider3, Provider<JobScheduler> provider4, Provider<JobInfoProvider> provider5, Provider<Clock> provider6, Provider<SharedPreferencesProvider> provider7) {
        return proxyProvidesDeviceCredentialsSyncCoordinator(provisioningModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static ZigbeeCredentialsSyncCoordinator proxyProvidesDeviceCredentialsSyncCoordinator(ProvisioningModule provisioningModule, Context context, ProvisionerInfo provisionerInfo, DSSClient dSSClient, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Clock clock, SharedPreferencesProvider sharedPreferencesProvider) {
        return (ZigbeeCredentialsSyncCoordinator) Preconditions.checkNotNull(provisioningModule.providesDeviceCredentialsSyncCoordinator(context, provisionerInfo, dSSClient, jobScheduler, jobInfoProvider, clock, sharedPreferencesProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ZigbeeCredentialsSyncCoordinator mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.provisionerinfoProvider, this.dssClientProvider, this.jobSchedulerProvider, this.jobInfoProvider, this.clockProvider, this.sharedPreferencesProvider);
    }
}
