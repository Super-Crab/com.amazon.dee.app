package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ControlledSetupPresenter_MembersInjector implements MembersInjector<ControlledSetupPresenter> {
    private final Provider<FFSArcusSyncCoordinator> mFFSArcusSyncCoordinatorProvider;
    private final Provider<ProvisionerClientData> mProvisionerClientDataProvider;

    public ControlledSetupPresenter_MembersInjector(Provider<FFSArcusSyncCoordinator> provider, Provider<ProvisionerClientData> provider2) {
        this.mFFSArcusSyncCoordinatorProvider = provider;
        this.mProvisionerClientDataProvider = provider2;
    }

    public static MembersInjector<ControlledSetupPresenter> create(Provider<FFSArcusSyncCoordinator> provider, Provider<ProvisionerClientData> provider2) {
        return new ControlledSetupPresenter_MembersInjector(provider, provider2);
    }

    public static void injectMFFSArcusSyncCoordinator(ControlledSetupPresenter controlledSetupPresenter, FFSArcusSyncCoordinator fFSArcusSyncCoordinator) {
        controlledSetupPresenter.mFFSArcusSyncCoordinator = fFSArcusSyncCoordinator;
    }

    public static void injectMProvisionerClientData(ControlledSetupPresenter controlledSetupPresenter, ProvisionerClientData provisionerClientData) {
        controlledSetupPresenter.mProvisionerClientData = provisionerClientData;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ControlledSetupPresenter controlledSetupPresenter) {
        injectMFFSArcusSyncCoordinator(controlledSetupPresenter, this.mFFSArcusSyncCoordinatorProvider.mo10268get());
        injectMProvisionerClientData(controlledSetupPresenter, this.mProvisionerClientDataProvider.mo10268get());
    }
}
