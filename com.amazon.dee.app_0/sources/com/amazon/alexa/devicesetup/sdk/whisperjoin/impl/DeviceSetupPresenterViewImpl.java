package com.amazon.alexa.devicesetup.sdk.whisperjoin.impl;

import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.PresenterViewParser;
import com.amazon.alexa.devicesetup.utils.EventBusUtil;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.IdleViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.InProgressViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ProvisioningDetailsViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupCompleteViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupFailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.WifiConnectionErrorViewModel;
/* loaded from: classes7.dex */
public class DeviceSetupPresenterViewImpl implements ControlledSetupPresenterContract.View {
    private final EventBusUtil eventBus;

    public DeviceSetupPresenterViewImpl(EventBusUtil eventBusUtil) {
        this.eventBus = eventBusUtil;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showDiscoveredDevices(DiscoveredDevicesViewModel discoveredDevicesViewModel) {
        String parseDiscoveredDevices = PresenterViewParser.parseDiscoveredDevices(discoveredDevicesViewModel, "showDiscoveredDevices");
        if (parseDiscoveredDevices != null) {
            this.eventBus.sendMessageToEventBus(parseDiscoveredDevices, "ffs::DiscoveredDevices");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showIdleState(IdleViewModel idleViewModel) {
        String parseIdle = PresenterViewParser.parseIdle(idleViewModel);
        if (parseIdle != null) {
            this.eventBus.sendMessageToEventBus(parseIdle, "ffs::SetupIdle");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showInProgress(InProgressViewModel inProgressViewModel) {
        String parseInProgress = PresenterViewParser.parseInProgress(inProgressViewModel);
        if (parseInProgress != null) {
            this.eventBus.sendMessageToEventBus(parseInProgress, "ffs::SetupInProgress");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showProvisioningDetails(ProvisioningDetailsViewModel provisioningDetailsViewModel) {
        String parseProvisioningDetails = PresenterViewParser.parseProvisioningDetails(provisioningDetailsViewModel);
        if (parseProvisioningDetails != null) {
            this.eventBus.sendMessageToEventBus(parseProvisioningDetails, "ffs::ProvisioningDetails");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showSetupComplete(SetupCompleteViewModel setupCompleteViewModel) {
        String parseSetupComplete = PresenterViewParser.parseSetupComplete(setupCompleteViewModel);
        if (parseSetupComplete != null) {
            this.eventBus.sendMessageToEventBus(parseSetupComplete, "ffs::SetupComplete");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showSetupFailure(SetupFailureViewModel setupFailureViewModel) {
        String parseSetupFailure = PresenterViewParser.parseSetupFailure(setupFailureViewModel);
        if (parseSetupFailure != null) {
            this.eventBus.sendMessageToEventBus(parseSetupFailure, "ffs::SetupFailure");
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.View
    public void showWifiConnectionError(WifiConnectionErrorViewModel wifiConnectionErrorViewModel) {
        String parseWifiConnectionError = PresenterViewParser.parseWifiConnectionError(wifiConnectionErrorViewModel);
        if (parseWifiConnectionError != null) {
            this.eventBus.sendMessageToEventBus(parseWifiConnectionError, "ffs::WifiConnectionError");
        }
    }
}
