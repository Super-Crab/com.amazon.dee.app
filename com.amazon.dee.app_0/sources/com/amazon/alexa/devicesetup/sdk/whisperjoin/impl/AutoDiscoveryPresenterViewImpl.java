package com.amazon.alexa.devicesetup.sdk.whisperjoin.impl;

import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.PresenterViewParser;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.ProvisioningConstants;
import com.amazon.alexa.devicesetup.utils.EventBusUtil;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.FailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.RecentlySetupDevicesViewModel;
/* loaded from: classes7.dex */
public class AutoDiscoveryPresenterViewImpl implements AutoDiscoveryPresenterContract.View {
    EventBusUtil eventBus;

    public AutoDiscoveryPresenterViewImpl(EventBusUtil eventBusUtil) {
        this.eventBus = eventBusUtil;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.View
    public void showDevicesForControlledSetup(DiscoveredDevicesViewModel discoveredDevicesViewModel) {
        String parseDiscoveredDevices = PresenterViewParser.parseDiscoveredDevices(discoveredDevicesViewModel, "showDevicesForControlledSetup");
        if (parseDiscoveredDevices != null) {
            this.eventBus.sendMessageToEventBus(parseDiscoveredDevices, ProvisioningConstants.EVENT_UNAUTHENTICATED_DEVICES);
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.View
    public void showDevicesIneligibleForAutomatedSetup(DiscoveredDevicesViewModel discoveredDevicesViewModel) {
        String parseDiscoveredDevices = PresenterViewParser.parseDiscoveredDevices(discoveredDevicesViewModel, "showDevicesIneligibleForAutomatedSetup");
        if (parseDiscoveredDevices != null) {
            this.eventBus.sendMessageToEventBus(parseDiscoveredDevices, ProvisioningConstants.EVENT_AUTHENTICATED_DEVICES);
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.View
    public void showFailure(FailureViewModel failureViewModel) {
        String parseFailure = PresenterViewParser.parseFailure(failureViewModel);
        if (parseFailure != null) {
            this.eventBus.sendMessageToEventBus(parseFailure, ProvisioningConstants.EVENT_AUTO_DISCOVERY_FAILURE);
        }
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.View
    public void showRecentlySetupDevices(RecentlySetupDevicesViewModel recentlySetupDevicesViewModel) {
        String parseRecentlySetupDevices = PresenterViewParser.parseRecentlySetupDevices(recentlySetupDevicesViewModel);
        if (parseRecentlySetupDevices != null) {
            this.eventBus.sendMessageToEventBus(parseRecentlySetupDevices, ProvisioningConstants.EVENT_RECENTLY_SETUP_DEVICES);
        }
    }
}
