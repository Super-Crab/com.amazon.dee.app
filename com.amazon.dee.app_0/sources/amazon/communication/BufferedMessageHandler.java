package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.BufferedMessageManagerBase;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class BufferedMessageHandler implements MessageHandler {
    private final BufferedMessageManagerBase mBufferedMessageManagerBase;
    private final MessageHandler mDecoratedMessageHandler;

    @FireOsSdk
    public BufferedMessageHandler(MessageHandler messageHandler) {
        this(messageHandler, 1800000L);
    }

    @Override // amazon.communication.MessageHandler
    @FireOsSdk
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        this.mDecoratedMessageHandler.onMessage(endpointIdentity, message);
    }

    @Override // amazon.communication.MessageHandler
    @FireOsSdk
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        this.mBufferedMessageManagerBase.handleMessageFragment(endpointIdentity, i, message, z);
    }

    @FireOsSdk
    public BufferedMessageHandler(MessageHandler messageHandler, long j) {
        this.mBufferedMessageManagerBase = new BufferedMessageManagerBase(j) { // from class: amazon.communication.BufferedMessageHandler.1
            @Override // com.amazon.communication.BufferedMessageManagerBase
            protected void handleCompletedMessage(EndpointIdentity endpointIdentity, BufferedMessageManagerBase.MessageEntry messageEntry) {
                BufferedMessageHandler.this.onMessage(endpointIdentity, messageEntry.getMessage());
            }
        };
        this.mDecoratedMessageHandler = messageHandler;
    }
}
