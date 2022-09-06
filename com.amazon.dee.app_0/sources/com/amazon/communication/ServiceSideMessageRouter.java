package com.amazon.communication;

import amazon.communication.BufferedMessageHandler;
import amazon.communication.DuplicateHandlerException;
import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.RegistrationFailedException;
import amazon.communication.identity.EndpointIdentity;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class ServiceSideMessageRouter {
    private static final DPLogger log = new DPLogger("TComm.ServiceSideMessageRouter");
    private final BandwidthToolByteAccountant mByteAccountant;
    private final MessageRouter mMessageRouter;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class MessageHandlerProxy implements MessageHandler, IBinder.DeathRecipient {
        private final int mChannel;
        private final IMessageHandler mHandler;
        private final int mUid;

        public MessageHandlerProxy(int i, int i2, IMessageHandler iMessageHandler) {
            this.mUid = i;
            this.mChannel = i2;
            this.mHandler = iMessageHandler;
        }

        public IBinder binder() {
            return this.mHandler.asBinder();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ServiceSideMessageRouter.log.warn("onBinderDied", "binder died", "channel", Integer.valueOf(this.mChannel), "uid", Integer.valueOf(this.mUid));
            ServiceSideMessageRouter.this.onBinderDied(this.mChannel);
        }

        @Override // amazon.communication.MessageHandler
        public void onMessage(EndpointIdentity endpointIdentity, Message message) {
            int payloadSize = message.getPayloadSize();
            if (payloadSize >= 0) {
                ServiceSideMessageRouter.this.mByteAccountant.accountBytesReceived(this.mUid, payloadSize);
            } else {
                ServiceSideMessageRouter.log.warn("onMessage", "unknown payload size", "channel", Integer.valueOf(this.mChannel), "uid", Integer.valueOf(this.mUid));
            }
            try {
                this.mHandler.onMessage(new ParcelableEndpointIdentity(endpointIdentity), MessageEnvelope.createInstance(message));
            } catch (RemoteException e) {
                ServiceSideMessageRouter.log.warn("onMessage", "binder is dead", "channel", Integer.valueOf(this.mChannel), "uid", Integer.valueOf(this.mUid), e);
            }
        }

        @Override // amazon.communication.MessageHandler
        public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
            throw new UnsupportedOperationException("Message fragments NYI");
        }

        public int uid() {
            return this.mUid;
        }
    }

    /* loaded from: classes12.dex */
    private class RemoteMessageHandler implements MessageHandler {
        final BufferedMessageHandler mBufferedHandler;
        final MessageHandlerProxy mProxy;

        public RemoteMessageHandler(int i, int i2, IMessageHandler iMessageHandler) {
            this.mProxy = new MessageHandlerProxy(i, i2, iMessageHandler);
            this.mBufferedHandler = new BufferedMessageHandler(this.mProxy);
        }

        @Override // amazon.communication.MessageHandler
        public void onMessage(EndpointIdentity endpointIdentity, Message message) {
            this.mBufferedHandler.onMessage(endpointIdentity, message);
        }

        @Override // amazon.communication.MessageHandler
        public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
            this.mBufferedHandler.onMessageFragment(endpointIdentity, i, message, z);
        }

        public MessageHandlerProxy proxy() {
            return this.mProxy;
        }
    }

    public ServiceSideMessageRouter(MessageRouter messageRouter, BandwidthToolByteAccountant bandwidthToolByteAccountant) {
        this.mMessageRouter = messageRouter;
        this.mByteAccountant = bandwidthToolByteAccountant;
    }

    private static RemoteException newRemoteException(Throwable th) {
        RemoteException remoteException = new RemoteException();
        remoteException.initCause(th);
        return remoteException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBinderDied(int i) {
        this.mMessageRouter.deregisterMessageHandler(i);
    }

    public void deregisterMessageHandler(int i) throws RemoteException {
        int callingUid = Binder.getCallingUid();
        MessageHandler messageHandler = this.mMessageRouter.getMessageHandler(i);
        if (messageHandler == null) {
            log.warn("deregisterMessageHandler", "futile attempt", "channel", Integer.valueOf(i), "uid", Integer.valueOf(callingUid));
        } else if (messageHandler instanceof RemoteMessageHandler) {
            RemoteMessageHandler remoteMessageHandler = (RemoteMessageHandler) messageHandler;
            if (callingUid == remoteMessageHandler.proxy().uid()) {
                this.mMessageRouter.deregisterMessageHandler(i);
                MessageHandlerProxy proxy = remoteMessageHandler.proxy();
                proxy.binder().unlinkToDeath(proxy, 0);
                return;
            }
            log.warn("deregisterMessageHandler", "remote handler hijack attempt", "channel", Integer.valueOf(i), "uid", Integer.valueOf(callingUid));
            throw newRemoteException(new SecurityException("Handler was not registered by this caller"));
        } else {
            log.warn("deregisterMessageHandler", "non-remote handler hijack attempt", "channel", Integer.valueOf(i), "uid", Integer.valueOf(callingUid));
            throw newRemoteException(new SecurityException("Handler was not registered by this caller"));
        }
    }

    public int registerMessageHandler(int i, IMessageHandler iMessageHandler) throws RemoteException {
        int callingUid = Binder.getCallingUid();
        RemoteMessageHandler remoteMessageHandler = new RemoteMessageHandler(callingUid, i, iMessageHandler);
        try {
            this.mMessageRouter.registerMessageHandler(i, remoteMessageHandler);
            try {
                MessageHandlerProxy proxy = remoteMessageHandler.proxy();
                proxy.binder().linkToDeath(proxy, 0);
                return 0;
            } catch (RemoteException unused) {
                log.warn("registerMessageHandler", "handler died", "channel", Integer.valueOf(i), "uid", Integer.valueOf(callingUid));
                this.mMessageRouter.deregisterMessageHandler(i);
                return CommunicationErrorCodes.ERR_HANDLER_INTERNAL_ERROR_UPON_REGISTRATION;
            }
        } catch (DuplicateHandlerException unused2) {
            log.warn("registerMessageHandler", "handler already exists", "channel", Integer.valueOf(i), "uid", Integer.valueOf(callingUid));
            return 2000;
        } catch (RegistrationFailedException unused3) {
            log.warn("registerMessageHandler", "registration failed", "channel", Integer.valueOf(i), "uid", Integer.valueOf(callingUid));
            return CommunicationErrorCodes.ERR_HANDLER_INTERNAL_ERROR_UPON_REGISTRATION;
        }
    }
}
