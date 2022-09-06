package com.amazon.devicesetup.provisioning.ble.events;

import com.amazon.devicesetup.provisioning.data.device.UuidCollection;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEvent;
/* loaded from: classes12.dex */
public class ProvisionableCommandInterfaceUpdatedEvent extends ProvisionableEvent<UuidCollection> {
    public ProvisionableCommandInterfaceUpdatedEvent(UuidCollection uuidCollection) {
        super(ProvisionableEvents.PROVISIONING_COMMAND_INTERFACE_UPDATED_EVENT_UUID, uuidCollection);
    }
}
