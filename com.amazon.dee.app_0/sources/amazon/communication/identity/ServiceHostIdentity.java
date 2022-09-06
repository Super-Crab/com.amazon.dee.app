package amazon.communication.identity;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Deprecated
/* loaded from: classes.dex */
public class ServiceHostIdentity extends EndpointIdentity {
    private final EndpointIdentity mHostIdentity;
    private final String mServiceName;

    @Deprecated
    ServiceHostIdentity(String str, String str2, int i, String str3) {
        if (str != null && !str.trim().isEmpty()) {
            if (str2 != null && !str2.trim().isEmpty()) {
                this.mServiceName = str;
                this.mHostIdentity = EndpointIdentityFactory.createUrlEndpointIdentity(str2, i, str3);
                return;
            }
            throw new IllegalArgumentException("hostname must not be null or empty for ServiceHostIdentity");
        }
        throw new IllegalArgumentException("serviceName must not be null or empty for ServiceHostIdentity");
    }

    @FireOsSdk
    public String getServiceName() {
        return this.mServiceName;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public EndpointIdentity.Type getType() {
        return EndpointIdentity.Type.SERVICE;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toLogSafeString() {
        return this.mHostIdentity.toLogSafeString();
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toString() {
        return this.mHostIdentity.toString();
    }
}
