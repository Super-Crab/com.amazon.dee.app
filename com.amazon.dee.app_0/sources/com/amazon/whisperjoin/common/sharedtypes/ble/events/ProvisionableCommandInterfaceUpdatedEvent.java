package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.UuidCollection;
/* loaded from: classes13.dex */
public class ProvisionableCommandInterfaceUpdatedEvent extends ProvisionableEvent<UuidCollection> {
    public ProvisionableCommandInterfaceUpdatedEvent(UuidCollection uuidCollection) {
        super(ProvisionableEvents.PROVISIONING_COMMAND_INTERFACE_UPDATED_EVENT_UUID, uuidCollection);
    }
}
