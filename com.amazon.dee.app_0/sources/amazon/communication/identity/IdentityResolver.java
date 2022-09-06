package amazon.communication.identity;

import amazon.communication.connection.Purpose;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface IdentityResolver {
    @FireOsSdk
    IRServiceEndpoint getEndpointForServiceName(String str);

    @FireOsSdk
    IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity);

    @FireOsSdk
    IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity, Purpose purpose);
}
