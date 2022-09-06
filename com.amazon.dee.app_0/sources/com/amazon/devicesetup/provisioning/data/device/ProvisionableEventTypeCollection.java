package com.amazon.devicesetup.provisioning.data.device;

import com.amazon.devicesetup.provisioning.ble.ProvisionableEventType;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.GenericHashSetCollection;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ProvisionableEventTypeCollection extends GenericHashSetCollection<ProvisionableEventType> {
    public ProvisionableEventTypeCollection() {
    }

    public ProvisionableEventTypeCollection(Map<UUID, Integer> map) {
        for (Map.Entry<UUID, Integer> entry : map.entrySet()) {
            this.values.add(new ProvisionableEventType(entry.getKey(), entry.getValue().intValue()));
        }
    }
}
