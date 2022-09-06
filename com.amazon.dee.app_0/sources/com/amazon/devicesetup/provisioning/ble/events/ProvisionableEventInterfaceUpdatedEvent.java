package com.amazon.devicesetup.provisioning.ble.events;

import com.amazon.devicesetup.provisioning.data.device.ProvisionableEventTypeCollection;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEvent;
/* loaded from: classes12.dex */
public class ProvisionableEventInterfaceUpdatedEvent extends ProvisionableEvent<ProvisionableEventTypeCollection> {
    public ProvisionableEventInterfaceUpdatedEvent(ProvisionableEventTypeCollection provisionableEventTypeCollection) {
        super(ProvisionableEvents.PROVISIONING_EVENT_INTERFACE_UPDATED_EVENT_UUID, provisionableEventTypeCollection);
    }
}
