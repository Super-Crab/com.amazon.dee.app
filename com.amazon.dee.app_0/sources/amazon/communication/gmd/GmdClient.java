package amazon.communication.gmd;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes.dex */
public interface GmdClient {
    void sendMessage(EndpointIdentity endpointIdentity, Message message, int i) throws GmdUnableToAcceptMessageException, IllegalArgumentException;
}
