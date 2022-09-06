package com.amazon.whisperjoin.common.sharedtypes.provisioning.events;
/* loaded from: classes13.dex */
public interface EventHandler<EventType> {
    boolean handleEvent(ProvisioningEvent<EventType> provisioningEvent);
}
