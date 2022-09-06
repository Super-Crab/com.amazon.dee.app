package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.controller;

import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ClientProvisioningDataModel;
import com.google.common.base.Function;
/* loaded from: classes13.dex */
public class UGSClientWorkflowController {
    private final Function<DiscoveredDevice, Void> mConnectToDevice;
    private final Function<Void, Void> mDiscoverDevices;
    private final Function<Void, Void> mRefreshAvailableNetworks;
    private final Function<ClientProvisioningDataModel, Void> mSendProvisioningData;
    private final Function<Void, Void> mTerminateSetup;

    public UGSClientWorkflowController(Function<Void, Void> function, Function<DiscoveredDevice, Void> function2, Function<ClientProvisioningDataModel, Void> function3, Function<Void, Void> function4, Function<Void, Void> function5) {
        this.mDiscoverDevices = function;
        this.mConnectToDevice = function2;
        this.mSendProvisioningData = function3;
        this.mRefreshAvailableNetworks = function4;
        if (function5 != null) {
            this.mTerminateSetup = function5;
            return;
        }
        throw new IllegalArgumentException("Terminate Setup can not be null");
    }

    public void chooseDevice(DiscoveredDevice discoveredDevice) {
        Function<DiscoveredDevice, Void> function = this.mConnectToDevice;
        if (function != null) {
            function.mo8172apply(discoveredDevice);
            return;
        }
        throw new IllegalStateException("ConnectToDevice to Device is not supported in this state");
    }

    public void discoverDevices() {
        Function<Void, Void> function = this.mDiscoverDevices;
        if (function != null) {
            function.mo8172apply(null);
            return;
        }
        throw new IllegalStateException("Discover Devices is not supported in this state");
    }

    public void refreshAvailableNetworks() {
        Function<Void, Void> function = this.mRefreshAvailableNetworks;
        if (function != null) {
            function.mo8172apply(null);
            return;
        }
        throw new IllegalStateException("Refresh Available Networks is not supported in this state");
    }

    public void sendProvisioningData(ClientProvisioningDataModel clientProvisioningDataModel) {
        Function<ClientProvisioningDataModel, Void> function = this.mSendProvisioningData;
        if (function != null) {
            function.mo8172apply(clientProvisioningDataModel);
            return;
        }
        throw new IllegalStateException("Send Provisioning Data is not supported in this state");
    }

    public void terminateSetup() {
        this.mTerminateSetup.mo8172apply(null);
    }
}
