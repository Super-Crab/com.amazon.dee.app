package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.GenericHashSetCollection;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.ValidatableUuid;
import java.util.Collection;
import java.util.UUID;
/* loaded from: classes13.dex */
public class UuidCollection extends GenericHashSetCollection<ValidatableUuid> {
    public UuidCollection() {
    }

    public UuidCollection(Collection<UUID> collection) {
        for (UUID uuid : collection) {
            this.values.add(new ValidatableUuid(uuid));
        }
    }
}
