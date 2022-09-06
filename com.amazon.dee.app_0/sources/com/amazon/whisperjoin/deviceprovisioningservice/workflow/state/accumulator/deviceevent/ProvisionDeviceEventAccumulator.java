package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
/* loaded from: classes13.dex */
public class ProvisionDeviceEventAccumulator extends EventAccumulator<WJResult.ProvisionDevice> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore error(WJResult.ProvisionDevice provisionDevice, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = provisionDevice.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession.Mutator mutator = new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee));
            mutator.setState(DeviceSession.DeviceState.PROVISIONING).setLastSetupError(new DeviceSession.SetupError(provisionDevice.getError(), DeviceSession.DeviceState.PROVISIONING));
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(provisionDevice).updateSession(wJProvisionee, mutator.create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore inProgress(WJResult.ProvisionDevice provisionDevice, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = provisionDevice.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(provisionDevice).updateSession(wJProvisionee, new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee)).setState(DeviceSession.DeviceState.PROVISIONING).create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore success(WJResult.ProvisionDevice provisionDevice, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = provisionDevice.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession.Mutator mutator = new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee));
            mutator.setState(DeviceSession.DeviceState.PROVISIONED).setChosenWifiConfiguration(provisionDevice.getData().getChosenWifiConfiguration());
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(provisionDevice).updateSession(wJProvisionee, mutator.create()).create();
        }
        return null;
    }
}
