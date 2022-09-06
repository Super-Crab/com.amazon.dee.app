package com.amazon.whisperjoin.common.sharedtypes.ble.events;
/* loaded from: classes13.dex */
public class ProvisioningDoneSuccessEvent extends ProvisionableEvent<EmptyEventData> {
    public ProvisioningDoneSuccessEvent() {
        super(ProvisionableEvents.PROVISIONING_DONE_SUCESS_EVENT_UUID, new EmptyEventData());
    }
}
