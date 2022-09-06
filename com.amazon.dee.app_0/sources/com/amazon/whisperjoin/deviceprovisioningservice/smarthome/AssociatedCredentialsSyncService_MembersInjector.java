package com.amazon.whisperjoin.deviceprovisioningservice.smarthome;

import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AssociatedCredentialsSyncService_MembersInjector implements MembersInjector<AssociatedCredentialsSyncService> {
    private final Provider<AssociatedDeviceCredentialsSyncCoordinator> mAssociatedDeviceCredentialsSyncCoordinatorProvider;
    private final Provider<CredentialSyncMetricsRecorder> mCredentialSyncMetricsRecorderProvider;
    private final Provider<DSHSSetCredentialsAPI> mDSHSSetCredentialsAPIProvider;
    private final Provider<FFSArcusSyncCoordinator> mFFSArcusSyncCoordinatorProvider;
    private final Provider<ProvisionerClientData> mProvisionerClientDataProvider;
    private final Provider<ZigbeeCredentialsSyncCoordinator> mZigbeeCredentialsSyncCoordinatorProvider;

    public AssociatedCredentialsSyncService_MembersInjector(Provider<ZigbeeCredentialsSyncCoordinator> provider, Provider<AssociatedDeviceCredentialsSyncCoordinator> provider2, Provider<FFSArcusSyncCoordinator> provider3, Provider<ProvisionerClientData> provider4, Provider<DSHSSetCredentialsAPI> provider5, Provider<CredentialSyncMetricsRecorder> provider6) {
        this.mZigbeeCredentialsSyncCoordinatorProvider = provider;
        this.mAssociatedDeviceCredentialsSyncCoordinatorProvider = provider2;
        this.mFFSArcusSyncCoordinatorProvider = provider3;
        this.mProvisionerClientDataProvider = provider4;
        this.mDSHSSetCredentialsAPIProvider = provider5;
        this.mCredentialSyncMetricsRecorderProvider = provider6;
    }

    public static MembersInjector<AssociatedCredentialsSyncService> create(Provider<ZigbeeCredentialsSyncCoordinator> provider, Provider<AssociatedDeviceCredentialsSyncCoordinator> provider2, Provider<FFSArcusSyncCoordinator> provider3, Provider<ProvisionerClientData> provider4, Provider<DSHSSetCredentialsAPI> provider5, Provider<CredentialSyncMetricsRecorder> provider6) {
        return new AssociatedCredentialsSyncService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectMAssociatedDeviceCredentialsSyncCoordinator(AssociatedCredentialsSyncService associatedCredentialsSyncService, AssociatedDeviceCredentialsSyncCoordinator associatedDeviceCredentialsSyncCoordinator) {
        associatedCredentialsSyncService.mAssociatedDeviceCredentialsSyncCoordinator = associatedDeviceCredentialsSyncCoordinator;
    }

    public static void injectMCredentialSyncMetricsRecorder(AssociatedCredentialsSyncService associatedCredentialsSyncService, CredentialSyncMetricsRecorder credentialSyncMetricsRecorder) {
        associatedCredentialsSyncService.mCredentialSyncMetricsRecorder = credentialSyncMetricsRecorder;
    }

    public static void injectMDSHSSetCredentialsAPI(AssociatedCredentialsSyncService associatedCredentialsSyncService, DSHSSetCredentialsAPI dSHSSetCredentialsAPI) {
        associatedCredentialsSyncService.mDSHSSetCredentialsAPI = dSHSSetCredentialsAPI;
    }

    public static void injectMFFSArcusSyncCoordinator(AssociatedCredentialsSyncService associatedCredentialsSyncService, FFSArcusSyncCoordinator fFSArcusSyncCoordinator) {
        associatedCredentialsSyncService.mFFSArcusSyncCoordinator = fFSArcusSyncCoordinator;
    }

    public static void injectMProvisionerClientData(AssociatedCredentialsSyncService associatedCredentialsSyncService, ProvisionerClientData provisionerClientData) {
        associatedCredentialsSyncService.mProvisionerClientData = provisionerClientData;
    }

    public static void injectMZigbeeCredentialsSyncCoordinator(AssociatedCredentialsSyncService associatedCredentialsSyncService, ZigbeeCredentialsSyncCoordinator zigbeeCredentialsSyncCoordinator) {
        associatedCredentialsSyncService.mZigbeeCredentialsSyncCoordinator = zigbeeCredentialsSyncCoordinator;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AssociatedCredentialsSyncService associatedCredentialsSyncService) {
        injectMZigbeeCredentialsSyncCoordinator(associatedCredentialsSyncService, this.mZigbeeCredentialsSyncCoordinatorProvider.mo10268get());
        injectMAssociatedDeviceCredentialsSyncCoordinator(associatedCredentialsSyncService, this.mAssociatedDeviceCredentialsSyncCoordinatorProvider.mo10268get());
        injectMFFSArcusSyncCoordinator(associatedCredentialsSyncService, this.mFFSArcusSyncCoordinatorProvider.mo10268get());
        injectMProvisionerClientData(associatedCredentialsSyncService, this.mProvisionerClientDataProvider.mo10268get());
        injectMDSHSSetCredentialsAPI(associatedCredentialsSyncService, this.mDSHSSetCredentialsAPIProvider.mo10268get());
        injectMCredentialSyncMetricsRecorder(associatedCredentialsSyncService, this.mCredentialSyncMetricsRecorderProvider.mo10268get());
    }
}
