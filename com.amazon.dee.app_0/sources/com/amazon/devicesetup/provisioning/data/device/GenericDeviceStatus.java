package com.amazon.devicesetup.provisioning.data.device;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public class GenericDeviceStatus<ConnectionStatus, RegistrationStatus> {
    private final ConnectionStatus connectionStatus;
    private final List<RegistrationStatus> registrationStatuses;

    public GenericDeviceStatus(ConnectionStatus connectionstatus, Collection<RegistrationStatus> collection) {
        this.connectionStatus = connectionstatus;
        this.registrationStatuses = new ArrayList(collection);
    }

    public ConnectionStatus getConnectionStatus() {
        return this.connectionStatus;
    }

    public Collection<RegistrationStatus> getRegistrationStatus() {
        return Collections.unmodifiableCollection(this.registrationStatuses);
    }
}
