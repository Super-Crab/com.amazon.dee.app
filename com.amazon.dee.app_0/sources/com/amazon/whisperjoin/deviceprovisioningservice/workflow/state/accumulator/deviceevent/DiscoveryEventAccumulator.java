package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DiscoveryEventAccumulator extends EventAccumulator<WJResult.Discovery> {
    private static final String TAG = "DiscoveryEventAccumulator";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore error(WJResult.Discovery discovery, WJWorkflowStateStore wJWorkflowStateStore) {
        if (!EventAccumulator.isWorkflowActive(wJWorkflowStateStore)) {
            return null;
        }
        return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(discovery).create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore idle(WJResult.Discovery discovery, WJWorkflowStateStore wJWorkflowStateStore) {
        return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(discovery).setDiscoveryActive(false).create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore inProgress(WJResult.Discovery discovery, WJWorkflowStateStore wJWorkflowStateStore) {
        return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(discovery).clearDiscoveredDevices().setDiscoveryActive(true).create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator
    public WJWorkflowStateStore success(WJResult.Discovery discovery, WJWorkflowStateStore wJWorkflowStateStore) {
        if (!EventAccumulator.isWorkflowActive(wJWorkflowStateStore)) {
            return null;
        }
        if (!wJWorkflowStateStore.isDiscoveryActive()) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Discovery update occurred while discovery is not active, ignoring provisionable: ");
            outline107.append(discovery.getWJProvisionee());
            WJLog.d(str, outline107.toString());
            return wJWorkflowStateStore;
        }
        return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(discovery).addDiscoveredDevices(discovery.getData()).create();
    }
}
