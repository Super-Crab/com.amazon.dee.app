package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
/* loaded from: classes13.dex */
public class GetProvisioningDetailsEventAccumulator extends EventAccumulator<WJResult.GetProvisioningDetails> {
    private static final String TAG = "GetProvisioningDetailsEventAccumulator";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore error(WJResult.GetProvisioningDetails getProvisioningDetails, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = getProvisioningDetails.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(getProvisioningDetails).updateSession(wJProvisionee, new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee)).setState(DeviceSession.DeviceState.GETTING_PROVISIONING_DETAILS).setProvisioningDetails(getProvisioningDetails.getData()).setLastSetupError(new DeviceSession.SetupError(getProvisioningDetails.getError(), DeviceSession.DeviceState.GETTING_PROVISIONING_DETAILS)).create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore inProgress(WJResult.GetProvisioningDetails getProvisioningDetails, WJWorkflowStateStore wJWorkflowStateStore) {
        WJProvisionee wJProvisionee = getProvisioningDetails.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(getProvisioningDetails).updateSession(wJProvisionee, new DeviceSession.Mutator(wJWorkflowStateStore.getSession(wJProvisionee)).setState(DeviceSession.DeviceState.GETTING_PROVISIONING_DETAILS).create()).create();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore success(WJResult.GetProvisioningDetails getProvisioningDetails, WJWorkflowStateStore wJWorkflowStateStore) {
        ProvisioningDetails data;
        WJProvisionee wJProvisionee = getProvisioningDetails.getWJProvisionee();
        if (EventAccumulator.isWorkflowActive(wJWorkflowStateStore) && sessionExists(wJProvisionee, wJWorkflowStateStore)) {
            DeviceSession session = wJWorkflowStateStore.getSession(wJProvisionee);
            if (session.getProvisioningDetails() != null) {
                ProvisioningDetails data2 = getProvisioningDetails.getData();
                ProvisioningDetails provisioningDetails = session.getProvisioningDetails();
                data = new ProvisioningDetails().setAvailableWifiNetworks(data2.getAvailableWifiNetworks() != null ? data2.getAvailableWifiNetworks() : provisioningDetails.getAvailableWifiNetworks()).setDeviceDetails(data2.getDeviceDetails() != null ? data2.getDeviceDetails() : provisioningDetails.getDeviceDetails());
            } else {
                data = getProvisioningDetails.getData();
            }
            return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(getProvisioningDetails).updateSession(wJProvisionee, new DeviceSession.Mutator(session).setState(DeviceSession.DeviceState.PROVISIONING_DETAILS_RECEIVED).setProvisioningDetails(data).create()).create();
        }
        return null;
    }
}
