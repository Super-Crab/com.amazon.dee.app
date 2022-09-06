package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.WorkflowScope;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow;
import dagger.Component;
@Component(dependencies = {ProvisioningComponent.class}, modules = {WorkflowModule.class})
@WorkflowScope
/* loaded from: classes13.dex */
public interface WorkflowComponent {
    ControlledSetupWorkflow getControlledSetupWorkflow();

    PhilipsZigbeeBleWorkflow getPhilipsZigbeeBleWorkflow();

    WifiSimpleSetupZeroTouchWorkflow getWifiSimpleSetupZeroTouchWorkflow();
}
