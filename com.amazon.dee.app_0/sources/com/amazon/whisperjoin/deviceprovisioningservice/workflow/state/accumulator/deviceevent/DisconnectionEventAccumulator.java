package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ConnectionFailureDuringSetup;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
/* loaded from: classes13.dex */
public class DisconnectionEventAccumulator extends EventAccumulator<WJResult.Disconnection> {
    private static final String TAG = "DisconnectionEventAccumulator";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore error(WJResult.Disconnection disconnection, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = disconnection.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession session = wJWorkflowStateStore.getSession(disconnection.getWJProvisionee());
            if (session.isState(DeviceSession.DeviceState.CONNECTING)) {
                WJLog.d(TAG, "Ignoring disconnection error since device is still Connecting, waiting for Connection WJResult");
                return null;
            }
            WJResult.Disconnection error = WJResult.Disconnection.error(disconnection.getWJProvisionee(), new ConnectionFailureDuringSetup(disconnection.getError(), session));
            WJLog.d(TAG, "Unexpected Disconnection, removing session");
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(error).removeSession(wJProvisionee).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore success(WJResult.Disconnection disconnection, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = disconnection.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            WJLog.d(TAG, "Disconnection Occurred, removing session");
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(disconnection).removeSession(wJProvisionee).create();
        }
        return null;
    }
}
