package com.amazon.whisperjoin.common.sharedtypes.provisioning.events;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class ProvisioningEvent<EventType> {
    private static final String TAG = "com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent";
    private final EventType mEvent;
    private final Object mEventData;

    public ProvisioningEvent(EventType eventtype, Object obj) {
        if (eventtype != null) {
            this.mEvent = eventtype;
            this.mEventData = obj;
            return;
        }
        throw new IllegalArgumentException("event can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisioningEvent.class != obj.getClass()) {
            return false;
        }
        ProvisioningEvent provisioningEvent = (ProvisioningEvent) obj;
        return new EqualsBuilder().append(this.mEvent, provisioningEvent.mEvent).append(this.mEventData, provisioningEvent.mEventData).isEquals();
    }

    public EventType getEvent() {
        return this.mEvent;
    }

    public <EventObject> EventObject getEventObject() {
        EventObject eventobject = (EventObject) this.mEventData;
        if (eventobject == null) {
            return null;
        }
        return eventobject;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mEvent).append(this.mEventData).toHashCode();
    }
}
