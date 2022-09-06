package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.IdleViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.InProgressViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ProvisioningDetailsViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupCompleteViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupFailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.WifiConnectionErrorViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ClientProvisioningDataModel;
/* loaded from: classes13.dex */
public interface ControlledSetupPresenterContract {

    /* loaded from: classes13.dex */
    public interface Action {
        void chooseDevice(DiscoveredDevice discoveredDevice);

        void discoverDevices();

        void provisionDevice(ClientProvisioningDataModel clientProvisioningDataModel);

        void refreshAvailableNetworks();

        void terminateSetup();
    }

    /* loaded from: classes13.dex */
    public interface View {
        void showDiscoveredDevices(DiscoveredDevicesViewModel discoveredDevicesViewModel);

        void showIdleState(IdleViewModel idleViewModel);

        void showInProgress(InProgressViewModel inProgressViewModel);

        void showProvisioningDetails(ProvisioningDetailsViewModel provisioningDetailsViewModel);

        void showSetupComplete(SetupCompleteViewModel setupCompleteViewModel);

        void showSetupFailure(SetupFailureViewModel setupFailureViewModel);

        void showWifiConnectionError(WifiConnectionErrorViewModel wifiConnectionErrorViewModel);
    }
}
