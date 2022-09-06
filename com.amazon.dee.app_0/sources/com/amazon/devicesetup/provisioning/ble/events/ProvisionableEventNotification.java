package com.amazon.devicesetup.provisioning.ble.events;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ProvisionableEventNotification {
    private final int eventKey;
    private final int eventType;

    public ProvisionableEventNotification(int i, int i2) {
        this.eventKey = i;
        this.eventType = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionableEventNotification.class != obj.getClass()) {
            return false;
        }
        ProvisionableEventNotification provisionableEventNotification = (ProvisionableEventNotification) obj;
        return this.eventKey == provisionableEventNotification.eventKey && this.eventType == provisionableEventNotification.eventType;
    }

    public int getEventKey() {
        return this.eventKey;
    }

    public int getEventType() {
        return this.eventType;
    }

    public int hashCode() {
        return (this.eventKey * 31) + this.eventType;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningEventNotification [key=");
        outline107.append(this.eventKey);
        outline107.append(", type=");
        return GeneratedOutlineSupport1.outline86(outline107, this.eventType, "]");
    }
}
