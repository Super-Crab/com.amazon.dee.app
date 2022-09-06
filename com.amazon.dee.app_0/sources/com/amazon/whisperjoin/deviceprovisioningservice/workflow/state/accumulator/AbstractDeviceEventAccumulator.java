package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public abstract class AbstractDeviceEventAccumulator<R> implements Accumulator<WJResult, R> {
    /* renamed from: handleConnectionEvent */
    protected abstract R mo5709handleConnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Connection connection);

    /* renamed from: handleDisconnectionEvent */
    protected abstract R mo5710handleDisconnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Disconnection disconnection);

    /* renamed from: handleDiscoveryEvent */
    protected abstract R mo5711handleDiscoveryEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Discovery discovery);

    /* renamed from: handleGetProvisioningDetails */
    protected abstract R mo5712handleGetProvisioningDetails(WJWorkflowStateStore wJWorkflowStateStore, WJResult.GetProvisioningDetails getProvisioningDetails);

    /* renamed from: handleProvisionDeviceEvent */
    protected abstract R mo5713handleProvisionDeviceEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.ProvisionDevice provisionDevice);

    /* renamed from: handleVerifyProvisioningEvent */
    protected abstract R mo5714handleVerifyProvisioningEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.VerifyProvisioning verifyProvisioning);

    /* renamed from: handleWorkflowIdle */
    protected abstract R mo5715handleWorkflowIdle(WJWorkflowStateStore wJWorkflowStateStore, WJResult.WorkflowIdle workflowIdle);

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.Accumulator
    /* renamed from: accumulate  reason: avoid collision after fix types in other method */
    public R mo5716accumulate(WJResult wJResult, WJWorkflowStateStore wJWorkflowStateStore) {
        if (wJResult.isA(WJResult.Discovery.class)) {
            return mo5711handleDiscoveryEvent(wJWorkflowStateStore, (WJResult.Discovery) wJResult);
        }
        if (wJResult.isA(WJResult.Connection.class)) {
            return mo5709handleConnectionEvent(wJWorkflowStateStore, (WJResult.Connection) wJResult);
        }
        if (wJResult.isA(WJResult.Disconnection.class)) {
            return mo5710handleDisconnectionEvent(wJWorkflowStateStore, (WJResult.Disconnection) wJResult);
        }
        if (wJResult.isA(WJResult.GetProvisioningDetails.class)) {
            return mo5712handleGetProvisioningDetails(wJWorkflowStateStore, (WJResult.GetProvisioningDetails) wJResult);
        }
        if (wJResult.isA(WJResult.ProvisionDevice.class)) {
            return mo5713handleProvisionDeviceEvent(wJWorkflowStateStore, (WJResult.ProvisionDevice) wJResult);
        }
        if (wJResult.isA(WJResult.VerifyProvisioning.class)) {
            return mo5714handleVerifyProvisioningEvent(wJWorkflowStateStore, (WJResult.VerifyProvisioning) wJResult);
        }
        if (wJResult.isA(WJResult.WorkflowIdle.class)) {
            return mo5715handleWorkflowIdle(wJWorkflowStateStore, (WJResult.WorkflowIdle) wJResult);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unhandled Device WJResult ");
        outline107.append(wJResult.toString());
        throw new RuntimeException(outline107.toString());
    }
}
