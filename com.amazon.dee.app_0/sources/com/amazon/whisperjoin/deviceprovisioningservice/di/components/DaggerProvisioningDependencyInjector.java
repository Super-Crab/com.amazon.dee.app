package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenter_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.CustomerProvisioneesSetupStatusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DaggerProvisioningDependencyInjector implements ProvisioningDependencyInjector {
    private ProvisioningComponent provisioningComponent;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ProvisioningComponent provisioningComponent;

        public ProvisioningDependencyInjector build() {
            Preconditions.checkBuilderRequirement(this.provisioningComponent, ProvisioningComponent.class);
            return new DaggerProvisioningDependencyInjector(this);
        }

        public Builder provisioningComponent(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = (ProvisioningComponent) Preconditions.checkNotNull(provisioningComponent);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AutoDiscoveryPresenter injectAutoDiscoveryPresenter(AutoDiscoveryPresenter autoDiscoveryPresenter) {
        AutoDiscoveryPresenter_MembersInjector.injectMDiscoverySettings(autoDiscoveryPresenter, (DiscoverySettings) Preconditions.checkNotNull(this.provisioningComponent.providesDiscoverySettings(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMDeviceDiscoveryStream(autoDiscoveryPresenter, (DeviceDiscoveryStream) Preconditions.checkNotNull(this.provisioningComponent.providesDeviceDiscoveryStream(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMAutoDiscoverySyncCoordinator(autoDiscoveryPresenter, (CustomerProvisioneesSetupStatusSyncCoordinator) Preconditions.checkNotNull(this.provisioningComponent.provideCustomerProvisioneesSetupStatusSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMFFSArcusSyncCoordinator(autoDiscoveryPresenter, (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.provisioningComponent.providesFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMProvisionerClientData(autoDiscoveryPresenter, (ProvisionerClientData) Preconditions.checkNotNull(this.provisioningComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMWJErrorMapper(autoDiscoveryPresenter, (WJErrorMapper) Preconditions.checkNotNull(this.provisioningComponent.providesWJErrorMapper(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMMetricsRecorder(autoDiscoveryPresenter, (AutoDiscoveryMetricsRecorder) Preconditions.checkNotNull(this.provisioningComponent.providesAutoDiscoveryMetricsRecorder(), "Cannot return null from a non-@Nullable component method"));
        AutoDiscoveryPresenter_MembersInjector.injectMDssClient(autoDiscoveryPresenter, (DSSClient) Preconditions.checkNotNull(this.provisioningComponent.providesDSSClient(), "Cannot return null from a non-@Nullable component method"));
        return autoDiscoveryPresenter;
    }

    private ControlledSetupPresenter injectControlledSetupPresenter(ControlledSetupPresenter controlledSetupPresenter) {
        ControlledSetupPresenter_MembersInjector.injectMFFSArcusSyncCoordinator(controlledSetupPresenter, (FFSArcusSyncCoordinator) Preconditions.checkNotNull(this.provisioningComponent.providesFFSArcusSyncCoordinator(), "Cannot return null from a non-@Nullable component method"));
        ControlledSetupPresenter_MembersInjector.injectMProvisionerClientData(controlledSetupPresenter, (ProvisionerClientData) Preconditions.checkNotNull(this.provisioningComponent.providesProvisionerClientData(), "Cannot return null from a non-@Nullable component method"));
        return controlledSetupPresenter;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningDependencyInjector
    public void inject(AutoDiscoveryPresenter autoDiscoveryPresenter) {
        injectAutoDiscoveryPresenter(autoDiscoveryPresenter);
    }

    private DaggerProvisioningDependencyInjector(Builder builder) {
        this.provisioningComponent = builder.provisioningComponent;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningDependencyInjector
    public void inject(ControlledSetupPresenter controlledSetupPresenter) {
        injectControlledSetupPresenter(controlledSetupPresenter);
    }
}
