package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import com.google.common.base.Objects;
import java.util.UUID;
/* loaded from: classes13.dex */
public class ProvisionableEvent<T> {
    private final T eventData;
    private final UUID uuid;

    public ProvisionableEvent(UUID uuid, T t) {
        if (uuid != null) {
            this.uuid = uuid;
            this.eventData = t;
            return;
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProvisionableEvent provisionableEvent = (ProvisionableEvent) obj;
        return Objects.equal(this.uuid, provisionableEvent.uuid) && Objects.equal(this.eventData, provisionableEvent.eventData);
    }

    public T getEventData() {
        return this.eventData;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        return Objects.hashCode(this.uuid, this.eventData);
    }
}
