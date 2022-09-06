package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface MessageHandler {
    @FireOsSdk
    void onMessage(EndpointIdentity endpointIdentity, Message message);

    @FireOsSdk
    void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z);
}
