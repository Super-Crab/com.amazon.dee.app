package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public class GenericDeviceStatus<ConnectionStatus, RegistrationStatus> {
    private final ConnectionStatus mConnectionStatus;
    private final List<RegistrationStatus> mRegistrationStatus;

    public GenericDeviceStatus(ConnectionStatus connectionstatus, Collection<RegistrationStatus> collection) {
        this.mConnectionStatus = connectionstatus;
        this.mRegistrationStatus = new ArrayList(collection);
    }

    public ConnectionStatus getConnectionStatus() {
        return this.mConnectionStatus;
    }

    public Collection<RegistrationStatus> getRegistrationStatus() {
        return Collections.unmodifiableCollection(this.mRegistrationStatus);
    }
}
