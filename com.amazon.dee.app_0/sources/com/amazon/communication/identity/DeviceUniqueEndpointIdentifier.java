package com.amazon.communication.identity;

import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
/* loaded from: classes12.dex */
public class DeviceUniqueEndpointIdentifier extends UniqueEndpointIdentifier {
    private final DeviceIdentity mDeviceIdentity;

    public DeviceUniqueEndpointIdentifier(DeviceIdentity deviceIdentity) {
        this.mDeviceIdentity = deviceIdentity;
    }

    @Override // com.amazon.communication.identity.UniqueEndpointIdentifier
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof DeviceUniqueEndpointIdentifier)) {
            return this.mDeviceIdentity.equals(((DeviceUniqueEndpointIdentifier) obj).mDeviceIdentity);
        }
        return false;
    }

    public DeviceIdentity getDeviceIdentity() {
        return this.mDeviceIdentity;
    }

    @Override // com.amazon.communication.identity.UniqueEndpointIdentifier
    public int hashCode() {
        return this.mDeviceIdentity.hashCode();
    }

    @Override // com.amazon.communication.identity.UniqueEndpointIdentifier
    public String toString() {
        return this.mDeviceIdentity.toString();
    }

    public DeviceUniqueEndpointIdentifier(String str) {
        EndpointIdentity createFromUrn = EndpointIdentityFactory.createFromUrn(str);
        if (createFromUrn instanceof DeviceIdentity) {
            this.mDeviceIdentity = (DeviceIdentity) createFromUrn;
            return;
        }
        throw new IllegalArgumentException("URN must represent a DeviceIdentity");
    }
}
