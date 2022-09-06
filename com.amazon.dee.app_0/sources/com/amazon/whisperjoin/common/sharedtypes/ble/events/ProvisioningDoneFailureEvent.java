package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisioningFailure;
/* loaded from: classes13.dex */
public class ProvisioningDoneFailureEvent extends ProvisionableEvent<ProvisioningFailure> {
    public ProvisioningDoneFailureEvent(ProvisioningFailure provisioningFailure) {
        super(ProvisionableEvents.PROVISIONING_DONE_FAILURE_EVENT_UUID, provisioningFailure);
    }
}
