package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.ConnectionEventAccumulator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.DisconnectionEventAccumulator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.DiscoveryEventAccumulator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.GetProvisioningDetailsEventAccumulator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.ProvisionDeviceEventAccumulator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.VerifyProvisioningEventAccumulator;
/* loaded from: classes13.dex */
public class DeviceEventWorkflowStateAccumulator extends AbstractDeviceEventAccumulator<WJWorkflowStateStore> {
    private final DiscoveryEventAccumulator mDiscoveryEventAccumulator = new DiscoveryEventAccumulator();
    private final ConnectionEventAccumulator mConnectionEventAccumulator = new ConnectionEventAccumulator();
    private final DisconnectionEventAccumulator mDisconnectionEventAccumulator = new DisconnectionEventAccumulator();
    private final GetProvisioningDetailsEventAccumulator mGetAvailableNetworksEventAccumulator = new GetProvisioningDetailsEventAccumulator();
    private final ProvisionDeviceEventAccumulator mProvisionDeviceEventAccumulator = new ProvisionDeviceEventAccumulator();
    private final VerifyProvisioningEventAccumulator mVerifyProvisioningEventAccumulator = new VerifyProvisioningEventAccumulator();

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleConnectionEvent */
    public WJWorkflowStateStore mo5709handleConnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Connection connection) {
        return this.mConnectionEventAccumulator.mo5716accumulate((ConnectionEventAccumulator) connection, wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDisconnectionEvent */
    public WJWorkflowStateStore mo5710handleDisconnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Disconnection disconnection) {
        return this.mDisconnectionEventAccumulator.mo5716accumulate((DisconnectionEventAccumulator) disconnection, wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDiscoveryEvent */
    public WJWorkflowStateStore mo5711handleDiscoveryEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Discovery discovery) {
        return this.mDiscoveryEventAccumulator.mo5716accumulate((DiscoveryEventAccumulator) discovery, wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleGetProvisioningDetails */
    public WJWorkflowStateStore mo5712handleGetProvisioningDetails(WJWorkflowStateStore wJWorkflowStateStore, WJResult.GetProvisioningDetails getProvisioningDetails) {
        return this.mGetAvailableNetworksEventAccumulator.mo5716accumulate((GetProvisioningDetailsEventAccumulator) getProvisioningDetails, wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleProvisionDeviceEvent */
    public WJWorkflowStateStore mo5713handleProvisionDeviceEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.ProvisionDevice provisionDevice) {
        return this.mProvisionDeviceEventAccumulator.mo5716accumulate((ProvisionDeviceEventAccumulator) provisionDevice, wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleVerifyProvisioningEvent */
    public WJWorkflowStateStore mo5714handleVerifyProvisioningEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.VerifyProvisioning verifyProvisioning) {
        return this.mVerifyProvisioningEventAccumulator.mo5716accumulate((VerifyProvisioningEventAccumulator) verifyProvisioning, wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleWorkflowIdle */
    public WJWorkflowStateStore mo5715handleWorkflowIdle(WJWorkflowStateStore wJWorkflowStateStore, WJResult.WorkflowIdle workflowIdle) {
        return new WJWorkflowStateStore.Mutator(wJWorkflowStateStore).setLastResult(workflowIdle).reset().create();
    }
}
