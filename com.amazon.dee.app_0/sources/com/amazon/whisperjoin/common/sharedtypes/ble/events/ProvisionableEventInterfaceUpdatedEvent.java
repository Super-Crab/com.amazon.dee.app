package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisionableEventTypeCollection;
/* loaded from: classes13.dex */
public class ProvisionableEventInterfaceUpdatedEvent extends ProvisionableEvent<ProvisionableEventTypeCollection> {
    public ProvisionableEventInterfaceUpdatedEvent(ProvisionableEventTypeCollection provisionableEventTypeCollection) {
        super(ProvisionableEvents.PROVISIONING_EVENT_INTERFACE_UPDATED_EVENT_UUID, provisionableEventTypeCollection);
    }
}
