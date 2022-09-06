package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncJobService;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncJobService_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService;
import com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.FFSWhiteListJobService;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.FFSWhiteListJobService_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.WhiteListPolicyUpdateListener;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedDeviceCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.ZigbeeCredentialsSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DaggerProvisionerServicesDependencyInjector implements ProvisionerServicesDependencyInjector {
    private ProvisionerServicesComponent provisionerServicesComponent;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ProvisionerServicesComponent provisionerServicesComponent;

        public ProvisionerServicesDependencyInjector build() {
            Preconditions.checkBuilderRequirement(this.provisionerServicesComponent, ProvisionerServicesComponent.class);
            return new DaggerProvisionerServicesDependencyInjector(this);
        }

        public Builder provisionerServicesComponent(ProvisionerServicesComponent provisionerServicesComponent) {
            this.provisionerServicesComponent = (ProvisionerServicesComponent) Preconditions.checkNotNull(provisionerServicesComponent);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AssociatedCredentialsSyncService injectAssociatedCredentialsSyncService(AssociatedCredentialsSyncService associatedCredentialsSyncService) {
        AssociatedCredentialsSyncService_MembersInjector.injectMZigbeeCredentialsSyncCoordinator(associatedCredentialsSyncService, (ZigbeeCredentialsSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesCredentialSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        AssociatedCredentialsSyncService_MembersInjector.injectMAssociatedDeviceCredentialsSyncCoordinator(associatedCredentialsSyncService, (AssociatedDeviceCredentialsSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesAssociatedDeviceCredentialsSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        AssociatedCredentialsSyncService_MembersInjector.injectMFFSArcusSyncCoordinator(associatedCredentialsSyncService, (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.getFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        AssociatedCredentialsSyncService_MembersInjector.injectMProvisionerClientData(associatedCredentialsSyncService, (ProvisionerClientData) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method"));
        AssociatedCredentialsSyncService_MembersInjector.injectMDSHSSetCredentialsAPI(associatedCredentialsSyncService, (DSHSSetCredentialsAPI) Preconditions.checkNotNull(this.provisionerServicesComponent.providesZigbeeCredentialSyncIntent(), "Cannot return null from a non-@Nullable component method"));
        AssociatedCredentialsSyncService_MembersInjector.injectMCredentialSyncMetricsRecorder(associatedCredentialsSyncService, (CredentialSyncMetricsRecorder) Preconditions.checkNotNull(this.provisionerServicesComponent.providesCredentialSyncMetricsRecorder(), "Cannot return null from a non-@Nullable component method"));
        return associatedCredentialsSyncService;
    }

    private FFSArcusSyncJobService injectFFSArcusSyncJobService(FFSArcusSyncJobService fFSArcusSyncJobService) {
        FFSArcusSyncJobService_MembersInjector.injectMFFSArcusSyncCoordinator(fFSArcusSyncJobService, (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.getFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        FFSArcusSyncJobService_MembersInjector.injectMProvisionerClientData(fFSArcusSyncJobService, (ProvisionerClientData) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method"));
        return fFSArcusSyncJobService;
    }

    private FFSProvisioningService injectFFSProvisioningService(FFSProvisioningService fFSProvisioningService) {
        FFSProvisioningService_MembersInjector.injectMContext(fFSProvisioningService, (Context) Preconditions.checkNotNull(this.provisionerServicesComponent.getContext(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMSharedPreferencesProvider(fFSProvisioningService, (SharedPreferencesProvider) Preconditions.checkNotNull(this.provisionerServicesComponent.getSharedPreferencesProvider(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMWhiteListPolicyCoordinator(fFSProvisioningService, (WhiteListPolicyCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesFFSWhiteListPolicyCoordinator(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMWhiteListPolicyUpdateListener(fFSProvisioningService, (WhiteListPolicyUpdateListener) Preconditions.checkNotNull(this.provisionerServicesComponent.providesWhiteListPolicyUpdateListener(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMProvisionerClientData(fFSProvisioningService, (ProvisionerClientData) Preconditions.checkNotNull(this.provisionerServicesComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMDevicePowerMonitor(fFSProvisioningService, (DevicePowerMonitor) Preconditions.checkNotNull(this.provisionerServicesComponent.getDevicePowerMonitor(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMFFSServiceMetricsRecorder(fFSProvisioningService, (FFSProvisioningServiceMetricsRecorder) Preconditions.checkNotNull(this.provisionerServicesComponent.providesFFSProvisioningServiceMetricsRecorder(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMLocationPermissionsHelper(fFSProvisioningService, (LocationPermissionsHelper) Preconditions.checkNotNull(this.provisionerServicesComponent.getLocationPermissionsHelper(), "Cannot return null from a non-@Nullable component method"));
        FFSProvisioningService_MembersInjector.injectMFFSArcusSyncCoordinator(fFSProvisioningService, (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.getFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        return fFSProvisioningService;
    }

    private FFSWhiteListJobService injectFFSWhiteListJobService(FFSWhiteListJobService fFSWhiteListJobService) {
        FFSWhiteListJobService_MembersInjector.injectMWhiteListPolicyCoordinator(fFSWhiteListJobService, (WhiteListPolicyCoordinator) Preconditions.checkNotNull(this.provisionerServicesComponent.providesFFSWhiteListPolicyCoordinator(), "Cannot return null from a non-@Nullable component method"));
        return fFSWhiteListJobService;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesDependencyInjector
    public void inject(FFSWhiteListJobService fFSWhiteListJobService) {
        injectFFSWhiteListJobService(fFSWhiteListJobService);
    }

    private DaggerProvisionerServicesDependencyInjector(Builder builder) {
        this.provisionerServicesComponent = builder.provisionerServicesComponent;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesDependencyInjector
    public void inject(FFSProvisioningService fFSProvisioningService) {
        injectFFSProvisioningService(fFSProvisioningService);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesDependencyInjector
    public void inject(AssociatedCredentialsSyncService associatedCredentialsSyncService) {
        injectAssociatedCredentialsSyncService(associatedCredentialsSyncService);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesDependencyInjector
    public void inject(FFSArcusSyncJobService fFSArcusSyncJobService) {
        injectFFSArcusSyncJobService(fFSArcusSyncJobService);
    }
}
