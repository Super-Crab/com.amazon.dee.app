package amazon.communication.rmr;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
@Deprecated
/* loaded from: classes.dex */
public interface IRmrManager {
    @Deprecated
    RmrRequest createRmrRequest(EndpointIdentity endpointIdentity, Message message, RmrResponseHandler rmrResponseHandler);

    @Deprecated
    void shutdown();
}
