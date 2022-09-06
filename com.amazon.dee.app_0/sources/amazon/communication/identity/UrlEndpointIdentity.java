package amazon.communication.identity;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class UrlEndpointIdentity extends EndpointIdentity {
    private final String mUrn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UrlEndpointIdentity(String str) {
        this.mUrn = str;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public EndpointIdentity.Type getType() {
        return EndpointIdentity.Type.URL;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toLogSafeString() {
        return "URNs may contain PII";
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toString() {
        return this.mUrn;
    }
}
