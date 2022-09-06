package com.amazon.devicesetup.provisioning.ble;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ProvisionableEventType {
    private final int eventType;
    private final UUID eventUuid;

    public ProvisionableEventType(UUID uuid, int i) {
        if (uuid != null) {
            this.eventUuid = uuid;
            this.eventType = i;
            return;
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public int getEventType() {
        return this.eventType;
    }

    public UUID getEventUuid() {
        return this.eventUuid;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisionableEventType [uuid=");
        outline107.append(this.eventUuid);
        outline107.append(", type=");
        return GeneratedOutlineSupport1.outline86(outline107, this.eventType, "]");
    }
}
