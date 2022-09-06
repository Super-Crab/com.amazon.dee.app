package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
/* loaded from: classes13.dex */
public class VerifyProvisioningEventAccumulator extends EventAccumulator<WJResult.VerifyProvisioning> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore error(WJResult.VerifyProvisioning verifyProvisioning, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = verifyProvisioning.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession.Mutator mutator = new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee));
            Throwable error = verifyProvisioning.getError();
            mutator.setState(DeviceSession.DeviceState.VERIFYING_PROVISIONING).setLastSetupError(new DeviceSession.SetupError(error, DeviceSession.DeviceState.VERIFYING_PROVISIONING));
            if (error instanceof ProvisionableWifiNetworkConnectionError) {
                mutator.setLastWifiConnectionError((ProvisionableWifiNetworkConnectionError) error);
            }
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(verifyProvisioning).updateSession(wJProvisionee, mutator.create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore inProgress(WJResult.VerifyProvisioning verifyProvisioning, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = verifyProvisioning.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession.Mutator mutator = new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee));
            mutator.setState(DeviceSession.DeviceState.VERIFYING_PROVISIONING);
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(verifyProvisioning).updateSession(wJProvisionee, mutator.create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore success(WJResult.VerifyProvisioning verifyProvisioning, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = verifyProvisioning.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession.Mutator mutator = new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee));
            mutator.setState(DeviceSession.DeviceState.VERIFIED_PROVISIONING);
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(verifyProvisioning).updateSession(wJProvisionee, mutator.create()).create();
        }
        return null;
    }
}
