package com.amazon.communication;

import amazon.communication.CommunicationBaseException;
import amazon.communication.DuplicateHandlerException;
import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.RegistrationFailedException;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public class MessageRouterImpl implements MessageRouter {
    private static final DPLogger log = new DPLogger("TComm.MessageRouterImpl");
    private final ConcurrentMap<Integer, MessageHandler> mChannelToHandlerMap = new ConcurrentHashMap();
    private UndeliverableMessageHandler mUndeliverableMessageHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class NoRegisteredListenerException extends CommunicationBaseException {
        private static final long serialVersionUID = 813518;

        public NoRegisteredListenerException(Exception exc) {
            super(exc);
        }

        public NoRegisteredListenerException(String str, Exception exc) {
            super(str, exc);
        }

        public NoRegisteredListenerException(String str) {
            super(str);
        }
    }

    private MessageHandler getMessageHandlerForChannel(int i) throws NoRegisteredListenerException {
        MessageHandler messageHandler = this.mChannelToHandlerMap.get(Integer.valueOf(i));
        if (messageHandler != null) {
            return messageHandler;
        }
        throw new NoRegisteredListenerException(GeneratedOutlineSupport1.outline49("[getMessageHandlerForChannel] No registered listener for channel ", i));
    }

    private void reportNoRegisteredListener(String str, EndpointIdentity endpointIdentity, Message message, int i) {
        log.warn(str, "no registered listener", "channel", Integer.valueOf(i), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
        if (log.isDebugEnabled()) {
            InputStream payload = message.getPayload();
            byte[] bArr = new byte[message.getPayloadSize()];
            int i2 = 0;
            while (true) {
                try {
                    int read = payload.read();
                    if (read <= -1) {
                        break;
                    }
                    bArr[i2] = (byte) read;
                    i2++;
                } catch (IOException e) {
                    log.error(str, "IOException while reading message", e);
                }
            }
            log.debug(str, "message dropped because no listener to route it to", "receivedMessage", new String(bArr));
        }
        UndeliverableMessageHandler undeliverableMessageHandler = this.mUndeliverableMessageHandler;
        if (undeliverableMessageHandler != null) {
            log.info(str, "undeliverable message handler registered", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
            try {
                undeliverableMessageHandler.onMessage(endpointIdentity, message);
                return;
            } catch (Exception e2) {
                log.error(str, "unexpected exception while executing undeliverable message handler", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i), e2);
                return;
            }
        }
        log.info(str, "undeliverable message handler not registered", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
    }

    @Override // com.amazon.communication.MessageRouter
    public MessageHandler deregisterMessageHandler(int i) {
        MessageHandler remove = this.mChannelToHandlerMap.remove(Integer.valueOf(i));
        if (remove == null) {
            log.warn("deregisterMessageHandler", "nothing to do", "channel", Integer.valueOf(i));
        }
        log.info("deregisterMessageHandler", "removed route", "channel", Integer.valueOf(i), "handler", remove);
        return remove;
    }

    public synchronized UndeliverableMessageHandler deregisterUndeliverableMessageHandler() {
        UndeliverableMessageHandler undeliverableMessageHandler;
        undeliverableMessageHandler = this.mUndeliverableMessageHandler;
        if (undeliverableMessageHandler == null) {
            log.warn("deregisterUndeliverableMessageHandler", "nothing to do", new Object[0]);
        } else {
            log.info("deregisterUndeliverableMessageHandler", "removed handler", "handler", undeliverableMessageHandler);
        }
        this.mUndeliverableMessageHandler = null;
        return undeliverableMessageHandler;
    }

    @Override // com.amazon.communication.MessageRouter
    public MessageHandler getMessageHandler(int i) {
        return this.mChannelToHandlerMap.get(Integer.valueOf(i));
    }

    public Map<Integer, MessageHandler> getRegisteredMessageHandlers() {
        return Collections.unmodifiableMap(this.mChannelToHandlerMap);
    }

    public UndeliverableMessageHandler getUndeliverableMessageHandler() {
        return this.mUndeliverableMessageHandler;
    }

    @Override // com.amazon.communication.MessageRouter
    public void registerMessageHandler(int i, MessageHandler messageHandler) throws RegistrationFailedException, DuplicateHandlerException {
        if (this.mChannelToHandlerMap.get(Integer.valueOf(i)) != null) {
            log.error("registerMessageHandler", "duplicate registration", "channel", Integer.valueOf(i));
            throw new DuplicateHandlerException(GeneratedOutlineSupport1.outline49("Duplicate registration for channel ", i));
        } else if (messageHandler != null) {
            log.info("registerMessageHandler", "adding route", "channel", Integer.valueOf(i), "handler", messageHandler);
            this.mChannelToHandlerMap.put(Integer.valueOf(i), messageHandler);
        } else {
            log.error("registerMessageHandler", "null handler cannot be registered", new Object[0]);
            throw new RegistrationFailedException("Null handler cannot be registered");
        }
    }

    public synchronized void registerUndeliverableMessageHandler(UndeliverableMessageHandler undeliverableMessageHandler) throws RegistrationFailedException {
        if (this.mUndeliverableMessageHandler != null) {
            log.error("registerMessageHandler", "duplicate undeliverable message handler registration", new Object[0]);
            throw new DuplicateHandlerException("Duplicate undeliverable message handler registration");
        } else if (undeliverableMessageHandler != null) {
            log.info("registerUndeliverableMessageHandler", "adding undeliverable message handler", "handler", undeliverableMessageHandler);
            this.mUndeliverableMessageHandler = undeliverableMessageHandler;
        } else {
            log.error("registerUndeliverableMessageHandler", "null handler cannot be registered", new Object[0]);
            throw new RegistrationFailedException("Null handler cannot be registered");
        }
    }

    @Override // com.amazon.communication.MessageRouter
    public void routeMessage(EndpointIdentity endpointIdentity, Message message, int i) {
        try {
            MessageHandler messageHandlerForChannel = getMessageHandlerForChannel(i);
            log.debug("routeMessage", "routing message", "channel", Integer.valueOf(i), InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
            messageHandlerForChannel.onMessage(endpointIdentity, message);
        } catch (NoRegisteredListenerException unused) {
            reportNoRegisteredListener("routeMessage", endpointIdentity, message, i);
        } catch (Exception e) {
            log.error("routeMessage", "unexpected exception while routing message", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i), e);
        }
    }

    @Override // com.amazon.communication.MessageRouter
    public void routeMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z, int i2) {
        try {
            MessageHandler messageHandlerForChannel = getMessageHandlerForChannel(i2);
            log.verbose("routeMessageFragment", "routing message fragment", "channel", Integer.valueOf(i2), "handler", messageHandlerForChannel);
            messageHandlerForChannel.onMessageFragment(endpointIdentity, i, message, z);
        } catch (NoRegisteredListenerException unused) {
            reportNoRegisteredListener("routeMessageFragment", endpointIdentity, message, i2);
        } catch (Exception e) {
            log.error("routeMessageFragment", "unexpected exception while routing message fragment", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i2), e);
        }
    }
}
