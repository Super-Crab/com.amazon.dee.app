package com.amazon.communication.rlm;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class ReliableMessagingMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger("TComm:ReliableMessagingMessageHandler");
    private final int mChannel;
    private final ReliableMessageProtocol mRlmProtocol;

    public ReliableMessagingMessageHandler(ReliableMessageProtocol reliableMessageProtocol, int i) {
        this.mChannel = i;
        this.mRlmProtocol = reliableMessageProtocol;
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        log.debug("onMessage", "message received", "origin", EndpointIdentity.logSafe(endpointIdentity), "message", message);
        try {
            this.mRlmProtocol.decode(message, endpointIdentity, this.mChannel);
        } catch (ProtocolException e) {
            log.error("onMessage", "error occured while decoding message", e);
        }
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        log.warn("onMessageFragment", "unexpected message fragment", "origin", EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "message", message, "moreToCome", Boolean.valueOf(z));
    }
}
