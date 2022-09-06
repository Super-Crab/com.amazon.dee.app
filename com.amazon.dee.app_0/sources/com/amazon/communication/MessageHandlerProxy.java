package com.amazon.communication;

import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentityFactory;
import android.os.RemoteException;
import com.amazon.communication.IMessageHandler;
import com.amazon.dp.logger.DPLogger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class MessageHandlerProxy extends IMessageHandler.Stub {
    private static final DPLogger log = new DPLogger("TComm.MessageHandlerProxy");
    private final MessageHandler mMessageHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageHandlerProxy(MessageHandler messageHandler) {
        this.mMessageHandler = messageHandler;
    }

    public MessageHandler getClientHandler() {
        return this.mMessageHandler;
    }

    @Override // com.amazon.communication.IMessageHandler
    public void onMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope) throws RemoteException {
        try {
            this.mMessageHandler.onMessage(EndpointIdentityFactory.createFromUrn(parcelableEndpointIdentity.toString()), messageEnvelope.toMessage());
        } catch (RuntimeException e) {
            log.warn("onMessage", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.IMessageHandler
    public void onMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i, boolean z) throws RemoteException {
        try {
            throw new UnsupportedOperationException("Message fragments not yet implemented");
        } catch (RuntimeException e) {
            log.warn("onMessageFragment", "Exception occurred!", e);
            throw e;
        }
    }
}
