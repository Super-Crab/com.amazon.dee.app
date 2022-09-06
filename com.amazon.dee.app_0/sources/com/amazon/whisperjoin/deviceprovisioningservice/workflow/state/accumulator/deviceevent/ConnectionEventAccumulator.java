package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ConnectionOperationStatusUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
/* loaded from: classes13.dex */
public class ConnectionEventAccumulator extends EventAccumulator<WJResult.Connection> {
    private static final String TAG = "ConnectionEventAccumulator";

    private boolean validConnectionState(WJResult.Connection connection) {
        return connection.getData().getState() == ConnectionOperationStatusUpdate.State.CONNECTED_TO_DEVICE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore error(WJResult.Connection connection, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = connection.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            WJLog.d(TAG, "An error occur while connecting, removing device session");
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(connection).removeSession(wJProvisionee).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore inProgress(WJResult.Connection connection, WJWorkflowStateStore wJWorkflowStateStore) {
        if (!EventAccumulator.isWorkflowActive(wJWorkflowStateStore)) {
            return null;
        }
        WJProvisionee wJProvisionee = connection.getWJProvisionee();
        return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(connection).updateSession(wJProvisionee, new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee)).setState(DeviceSession.DeviceState.CONNECTING).setDeviceConnectionConfiguration(connection.getData().getDeviceConnectionConfiguration()).create()).create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore success(WJResult.Connection connection, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = connection.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(connection).updateSession(wJProvisionee, new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee)).setConnected(true).setState(DeviceSession.DeviceState.SECURE_CHANNEL_ESTABLISHED).create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore update(WJResult.Connection connection, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = connection.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore) && validConnectionState(connection)) {
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(connection).updateSession(wJProvisionee, new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee)).setConnected(true).setState(DeviceSession.DeviceState.CONNECTED).create()).create();
        }
        return null;
    }
}
