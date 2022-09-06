package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import androidx.annotation.NonNull;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.FailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.RecentlySetupDevicesViewModel;
/* loaded from: classes13.dex */
public interface AutoDiscoveryPresenterContract {

    /* loaded from: classes13.dex */
    public interface Action extends BasePresenterAction<View> {
        void attachView(@NonNull View view);

        @Override // 
        void detachView();

        void getCustomerProvisioneesSetupStatus();

        void startDiscovery();

        void terminate();
    }

    /* loaded from: classes13.dex */
    public interface View {
        void showDevicesForControlledSetup(DiscoveredDevicesViewModel discoveredDevicesViewModel);

        void showDevicesIneligibleForAutomatedSetup(DiscoveredDevicesViewModel discoveredDevicesViewModel);

        void showFailure(FailureViewModel failureViewModel);

        void showRecentlySetupDevices(RecentlySetupDevicesViewModel recentlySetupDevicesViewModel);
    }
}
