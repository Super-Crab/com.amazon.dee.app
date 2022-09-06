package com.amazon.whisperjoin.deviceprovisioningservice.arcus;

import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class FFSArcusSyncJobService_MembersInjector implements MembersInjector<FFSArcusSyncJobService> {
    private final Provider<FFSArcusSyncCoordinator> mFFSArcusSyncCoordinatorProvider;
    private final Provider<ProvisionerClientData> mProvisionerClientDataProvider;

    public FFSArcusSyncJobService_MembersInjector(Provider<FFSArcusSyncCoordinator> provider, Provider<ProvisionerClientData> provider2) {
        this.mFFSArcusSyncCoordinatorProvider = provider;
        this.mProvisionerClientDataProvider = provider2;
    }

    public static MembersInjector<FFSArcusSyncJobService> create(Provider<FFSArcusSyncCoordinator> provider, Provider<ProvisionerClientData> provider2) {
        return new FFSArcusSyncJobService_MembersInjector(provider, provider2);
    }

    public static void injectMFFSArcusSyncCoordinator(FFSArcusSyncJobService fFSArcusSyncJobService, FFSArcusSyncCoordinator fFSArcusSyncCoordinator) {
        fFSArcusSyncJobService.mFFSArcusSyncCoordinator = fFSArcusSyncCoordinator;
    }

    public static void injectMProvisionerClientData(FFSArcusSyncJobService fFSArcusSyncJobService, ProvisionerClientData provisionerClientData) {
        fFSArcusSyncJobService.mProvisionerClientData = provisionerClientData;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FFSArcusSyncJobService fFSArcusSyncJobService) {
        injectMFFSArcusSyncCoordinator(fFSArcusSyncJobService, this.mFFSArcusSyncCoordinatorProvider.mo10268get());
        injectMProvisionerClientData(fFSArcusSyncJobService, this.mProvisionerClientDataProvider.mo10268get());
    }
}
