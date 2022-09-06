package com.amazon.devicesetup.provisioning.ble.events;

import com.amazon.devicesetup.provisioning.data.device.ProvisioningFailure;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEvent;
/* loaded from: classes12.dex */
public class ProvisioningDoneFailureEvent extends ProvisionableEvent<ProvisioningFailure> {
    public ProvisioningDoneFailureEvent(ProvisioningFailure provisioningFailure) {
        super(ProvisionableEvents.PROVISIONING_DONE_FAILURE_EVENT_UUID, provisioningFailure);
    }
}
