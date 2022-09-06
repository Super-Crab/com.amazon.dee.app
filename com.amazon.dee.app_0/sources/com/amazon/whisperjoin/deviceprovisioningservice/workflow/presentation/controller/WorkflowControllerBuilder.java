package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.controller;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ClientProvisioningDataModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetProvisioningDetailsOptions;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.RegistrationRequest;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.google.common.base.Function;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes13.dex */
public class WorkflowControllerBuilder {
    private final DeviceActionCreator mActionController;
    private final WJWorkflowStateStore mWorkflowState;
    private Function<Void, Void> mDiscoverDevices = null;
    private Function<DiscoveredDevice, Void> mConnectToDevice = null;
    private Function<ClientProvisioningDataModel, Void> mSendProvisioningData = null;
    private Function<Void, Void> mRefreshAvailableNetworks = null;
    private final Function<Void, Void> mAbortSetup = new AbortSetup();

    /* loaded from: classes13.dex */
    private class AbortSetup implements Function<Void, Void> {
        private AbortSetup() {
        }

        @Override // com.google.common.base.Function
        @Nullable
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Void mo8172apply(@Nullable Void r2) {
            WorkflowControllerBuilder.this.mActionController.terminateSetup(WorkflowControllerBuilder.this.mWorkflowState);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class ConnectToDiscoveredDevice implements Function<DiscoveredDevice, Void> {
        private final Map<DiscoveredDevice, WJProvisionee> mDiscoveredDeviceMap;
        private final TrustProvider.TrustState mTrustState;

        public ConnectToDiscoveredDevice(Map<DiscoveredDevice, WJProvisionee> map, TrustProvider.TrustState trustState) {
            this.mDiscoveredDeviceMap = map;
            this.mTrustState = trustState;
        }

        @Override // com.google.common.base.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Void mo8172apply(DiscoveredDevice discoveredDevice) {
            WJProvisionee wJProvisionee = this.mDiscoveredDeviceMap.get(discoveredDevice);
            if (wJProvisionee != null) {
                WorkflowControllerBuilder.this.mActionController.connectToDevice(wJProvisionee, DeviceConnectionConfiguration.builder().withTrustState(this.mTrustState).build());
                return null;
            }
            throw new IllegalStateException("Unrecognized device: " + discoveredDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class DiscoverDevices implements Function<Void, Void> {
        private DiscoverDevices() {
        }

        @Override // com.google.common.base.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Void mo8172apply(@Nullable Void r1) {
            WorkflowControllerBuilder.this.mActionController.startDiscovery();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class RefreshAvailableNetworks implements Function<Void, Void> {
        private final WJProvisionee mWJProvisionee;
        private final WJWorkflowStateStore mWorkflowState;

        public RefreshAvailableNetworks(WJProvisionee wJProvisionee, WJWorkflowStateStore wJWorkflowStateStore) {
            this.mWJProvisionee = wJProvisionee;
            this.mWorkflowState = wJWorkflowStateStore;
        }

        @Override // com.google.common.base.Function
        @Nullable
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Void mo8172apply(@Nullable Void r4) {
            DeviceSession session = this.mWorkflowState.getSession(this.mWJProvisionee);
            WorkflowControllerBuilder.this.mActionController.getProvisioningDetails(this.mWJProvisionee, GetProvisioningDetailsOptions.refreshAvailableNetworks(session.getDiscoverySessionToken(), session.getDeviceDetails()));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class SendProvisioningData implements Function<ClientProvisioningDataModel, Void> {
        private final DeviceSession mDeviceSession;
        private final WJProvisionee mWJProvisionee;

        public SendProvisioningData(WJProvisionee wJProvisionee, DeviceSession deviceSession) {
            this.mWJProvisionee = wJProvisionee;
            this.mDeviceSession = deviceSession;
        }

        @Override // com.google.common.base.Function
        @Nullable
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Void mo8172apply(ClientProvisioningDataModel clientProvisioningDataModel) {
            if (clientProvisioningDataModel != null) {
                WorkflowControllerBuilder.this.mActionController.provisionDevice(this.mWJProvisionee, new ProvisionableConfiguration.Builder().setChosenWifiConfiguration(clientProvisioningDataModel.getWifiConfiguration()).setDeviceDetails(this.mDeviceSession.getProvisioningDetails().getDeviceDetails()).setSaveWifiNetworkToLocker(clientProvisioningDataModel.saveNetworkToLocker()).setCustomClientConfiguration(clientProvisioningDataModel.getCustomConfiguration()).setAvailableNetworks(this.mDeviceSession.getProvisioningDetails().getAvailableWifiNetworks()).setRegistrationRequest(new RegistrationRequest(RegistrationRequest.Scheme.CODE_BASED_LINKING)).setProvisionableReportUrl(this.mDeviceSession.getProvisionableReportUrl()).setSessionToken(this.mDeviceSession.getDiscoverySessionToken()).create());
                return null;
            }
            throw new IllegalArgumentException("Wifi config can't be null");
        }
    }

    public WorkflowControllerBuilder(DeviceActionCreator deviceActionCreator, WJWorkflowStateStore wJWorkflowStateStore) {
        this.mActionController = deviceActionCreator;
        this.mWorkflowState = wJWorkflowStateStore;
    }

    public UGSClientWorkflowController build() {
        return new UGSClientWorkflowController(this.mDiscoverDevices, this.mConnectToDevice, this.mSendProvisioningData, this.mRefreshAvailableNetworks, this.mAbortSetup);
    }

    public WorkflowControllerBuilder connectToDiscoveredDevice(Map<DiscoveredDevice, WJProvisionee> map, TrustProvider.TrustState trustState) {
        this.mConnectToDevice = new ConnectToDiscoveredDevice(map, trustState);
        return this;
    }

    public WorkflowControllerBuilder discoverDevices() {
        this.mDiscoverDevices = new DiscoverDevices();
        return this;
    }

    public WorkflowControllerBuilder refreshAvailableNetworks(WJProvisionee wJProvisionee) {
        this.mRefreshAvailableNetworks = new RefreshAvailableNetworks(wJProvisionee, this.mWorkflowState);
        return this;
    }

    public WorkflowControllerBuilder sendProvisioningData(WJProvisionee wJProvisionee) {
        this.mSendProvisioningData = new SendProvisioningData(wJProvisionee, this.mWorkflowState.getSession(wJProvisionee));
        return this;
    }
}
