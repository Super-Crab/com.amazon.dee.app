package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.app.job.JobScheduler;
import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningServicesModule_ProvidesFFSWhiteListPolicyCoordinatorFactory implements Factory<WhiteListPolicyCoordinator> {
    private final Provider<Clock> clockProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final Provider<JobInfoProvider> jobInfoProvider;
    private final Provider<JobScheduler> jobSchedulerProvider;
    private final ProvisioningServicesModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;
    private final Provider<SharedPreferencesProvider> sharedPreferencesProvider;

    public ProvisioningServicesModule_ProvidesFFSWhiteListPolicyCoordinatorFactory(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<JobScheduler> provider2, Provider<JobInfoProvider> provider3, Provider<SharedPreferencesProvider> provider4, Provider<Clock> provider5, Provider<DSSClient> provider6, Provider<ProvisionerClientData> provider7) {
        this.module = provisioningServicesModule;
        this.contextProvider = provider;
        this.jobSchedulerProvider = provider2;
        this.jobInfoProvider = provider3;
        this.sharedPreferencesProvider = provider4;
        this.clockProvider = provider5;
        this.dssClientProvider = provider6;
        this.provisionerClientDataProvider = provider7;
    }

    public static ProvisioningServicesModule_ProvidesFFSWhiteListPolicyCoordinatorFactory create(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<JobScheduler> provider2, Provider<JobInfoProvider> provider3, Provider<SharedPreferencesProvider> provider4, Provider<Clock> provider5, Provider<DSSClient> provider6, Provider<ProvisionerClientData> provider7) {
        return new ProvisioningServicesModule_ProvidesFFSWhiteListPolicyCoordinatorFactory(provisioningServicesModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static WhiteListPolicyCoordinator provideInstance(ProvisioningServicesModule provisioningServicesModule, Provider<Context> provider, Provider<JobScheduler> provider2, Provider<JobInfoProvider> provider3, Provider<SharedPreferencesProvider> provider4, Provider<Clock> provider5, Provider<DSSClient> provider6, Provider<ProvisionerClientData> provider7) {
        return proxyProvidesFFSWhiteListPolicyCoordinator(provisioningServicesModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static WhiteListPolicyCoordinator proxyProvidesFFSWhiteListPolicyCoordinator(ProvisioningServicesModule provisioningServicesModule, Context context, JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, SharedPreferencesProvider sharedPreferencesProvider, Clock clock, DSSClient dSSClient, ProvisionerClientData provisionerClientData) {
        return (WhiteListPolicyCoordinator) Preconditions.checkNotNull(provisioningServicesModule.providesFFSWhiteListPolicyCoordinator(context, jobScheduler, jobInfoProvider, sharedPreferencesProvider, clock, dSSClient, provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WhiteListPolicyCoordinator mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.jobSchedulerProvider, this.jobInfoProvider, this.sharedPreferencesProvider, this.clockProvider, this.dssClientProvider, this.provisionerClientDataProvider);
    }
}
