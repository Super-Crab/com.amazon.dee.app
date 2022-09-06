package com.amazon.devicesetup.provisioning.data.device;

import com.amazon.devicesetup.utility.ValidatableUuid;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.GenericHashSetCollection;
import java.util.Collection;
import java.util.UUID;
/* loaded from: classes12.dex */
public class UuidCollection extends GenericHashSetCollection<ValidatableUuid> {
    public UuidCollection() {
    }

    public UuidCollection(Collection<UUID> collection) {
        for (UUID uuid : collection) {
            this.values.add(new ValidatableUuid(uuid));
        }
    }
}
