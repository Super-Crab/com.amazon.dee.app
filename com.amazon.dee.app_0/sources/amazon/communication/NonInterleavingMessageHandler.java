package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.BufferedMessageManagerBase;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.util.Map;
/* loaded from: classes.dex */
public class NonInterleavingMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger("TComm.NonInterleavingMessageHandler");
    protected BufferedMessageManagerBase mBufferedMessageManagerBase;
    private final MessageHandler mDecoratedMessageHandler;

    /* loaded from: classes.dex */
    protected static class EndpointOnlyMessageIdentity implements BufferedMessageManagerBase.MessageIdentityKey {
        private final EndpointIdentity mEndpointIdentity;

        EndpointOnlyMessageIdentity(EndpointIdentity endpointIdentity) {
            this.mEndpointIdentity = endpointIdentity;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || EndpointOnlyMessageIdentity.class != obj.getClass()) {
                return false;
            }
            return this.mEndpointIdentity.equals(((EndpointOnlyMessageIdentity) obj).mEndpointIdentity);
        }

        public int hashCode() {
            return this.mEndpointIdentity.hashCode();
        }
    }

    public NonInterleavingMessageHandler(MessageHandler messageHandler) {
        this(messageHandler, 1800000L);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        this.mDecoratedMessageHandler.onMessage(endpointIdentity, message);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        this.mBufferedMessageManagerBase.verifyOnMessageFragmentParams(endpointIdentity, message);
        BufferedMessageManagerBase.MessageIdentityKey createMessageIdentityKey = this.mBufferedMessageManagerBase.createMessageIdentityKey(endpointIdentity, i);
        Map<BufferedMessageManagerBase.MessageIdentityKey, BufferedMessageManagerBase.ByteBufferChainMessageEntry> messageFragmentMap = this.mBufferedMessageManagerBase.getMessageFragmentMap();
        BufferedMessageManagerBase.ByteBufferChainMessageEntry byteBufferChainMessageEntry = messageFragmentMap.get(createMessageIdentityKey);
        if (byteBufferChainMessageEntry != null && byteBufferChainMessageEntry.getMessageId() != i) {
            log.error("onMessageFragment", "receive a fragment breaking noninterleaving rule.", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "bufferedMessage", byteBufferChainMessageEntry, "messageId", Integer.valueOf(i));
            messageFragmentMap.remove(createMessageIdentityKey);
        }
        this.mBufferedMessageManagerBase.handleMessageFragment(endpointIdentity, i, message, z);
    }

    public NonInterleavingMessageHandler(MessageHandler messageHandler, long j) {
        this.mBufferedMessageManagerBase = new BufferedMessageManagerBase(j) { // from class: amazon.communication.NonInterleavingMessageHandler.1
            @Override // com.amazon.communication.BufferedMessageManagerBase
            public BufferedMessageManagerBase.MessageIdentityKey createMessageIdentityKey(EndpointIdentity endpointIdentity, int i) {
                return new EndpointOnlyMessageIdentity(endpointIdentity);
            }

            @Override // com.amazon.communication.BufferedMessageManagerBase
            protected void handleCompletedMessage(EndpointIdentity endpointIdentity, BufferedMessageManagerBase.MessageEntry messageEntry) {
                NonInterleavingMessageHandler.this.onMessage(endpointIdentity, messageEntry.getMessage());
            }
        };
        this.mDecoratedMessageHandler = messageHandler;
    }
}
