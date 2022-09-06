package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventType;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.GenericHashSetCollection;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes13.dex */
public class ProvisionableEventTypeCollection extends GenericHashSetCollection<ProvisionableEventType> {
    public ProvisionableEventTypeCollection() {
    }

    public ProvisionableEventTypeCollection(Map<UUID, Integer> map) {
        for (Map.Entry<UUID, Integer> entry : map.entrySet()) {
            this.values.add(new ProvisionableEventType(entry.getKey(), entry.getValue().intValue()));
        }
    }
}
