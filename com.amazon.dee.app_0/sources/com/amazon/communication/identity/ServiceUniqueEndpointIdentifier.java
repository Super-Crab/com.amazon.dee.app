package com.amazon.communication.identity;

import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.net.URI;
/* loaded from: classes12.dex */
public class ServiceUniqueEndpointIdentifier extends UniqueEndpointIdentifier {
    private static final DPLogger log = new DPLogger("TComm.ServiceUniqueEndpointIdentifier");
    private final String mUniqueIdentifier;

    public ServiceUniqueEndpointIdentifier(IRServiceEndpoint iRServiceEndpoint) {
        log.debug("ServiceUniqueEndpointIdentifier", "constructor called", "irEndpoint", iRServiceEndpoint);
        this.mUniqueIdentifier = iRServiceEndpoint.getHostname();
    }

    @Override // com.amazon.communication.identity.UniqueEndpointIdentifier
    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ServiceUniqueEndpointIdentifier)) {
            return this.mUniqueIdentifier.equals(((ServiceUniqueEndpointIdentifier) obj).mUniqueIdentifier);
        }
        return false;
    }

    @Override // com.amazon.communication.identity.UniqueEndpointIdentifier
    public int hashCode() {
        return this.mUniqueIdentifier.hashCode();
    }

    @Override // com.amazon.communication.identity.UniqueEndpointIdentifier
    public String toString() {
        return this.mUniqueIdentifier.toString();
    }

    public ServiceUniqueEndpointIdentifier(EndpointIdentity endpointIdentity) {
        log.debug("ServiceUniqueEndpointIdentifier", "constructor called", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
        if (!(endpointIdentity instanceof DeviceIdentity)) {
            String host = URI.create(endpointIdentity.toString()).getHost();
            this.mUniqueIdentifier = host == null ? endpointIdentity.toString() : host;
            log.debug("ServiceUniqueEndpointIdentifier", "made identifier", "mUniqueIdentifier", this.mUniqueIdentifier);
            return;
        }
        throw new IllegalArgumentException("Cannot make a ServiceUniqueEndpointIdentifier using a DeviceIdentity");
    }
}
