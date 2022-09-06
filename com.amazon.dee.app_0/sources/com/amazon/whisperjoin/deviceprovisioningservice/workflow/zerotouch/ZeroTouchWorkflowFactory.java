package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow;
/* loaded from: classes13.dex */
public interface ZeroTouchWorkflowFactory {
    PhilipsZigbeeBleWorkflow createNewPhilipsZigbeeBleWorkflow();

    WifiSimpleSetupZeroTouchWorkflow createNewWifiSimpleSetupWorkflow();
}
