package com.amazon.communication.heartbeat;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.heartbeat.HeartbeatControlMessage;
import com.amazon.dp.logger.DPLogger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
public class HeartbeatControlMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger("TComm.HeartbeatControlMessageHandler");
    private final HeartbeatControlApplicationProtocol mApplicationProtocol;
    private final Map<HeartbeatControlMessage.Type, HeartbeatCommandInvoker> mRegisteredCommandInvokers = new ConcurrentHashMap();

    /* loaded from: classes12.dex */
    public interface HeartbeatCommandInvoker {
        void execute(HeartbeatControlMessage heartbeatControlMessage);
    }

    public HeartbeatControlMessageHandler(HeartbeatControlApplicationProtocol heartbeatControlApplicationProtocol) {
        this.mApplicationProtocol = heartbeatControlApplicationProtocol;
    }

    private HeartbeatCommandInvoker getInvoker(HeartbeatControlMessage.Type type) {
        return this.mRegisteredCommandInvokers.get(type);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        log.info("onMessage", "heartbeat control message received", "source", endpointIdentity, "message", message);
        try {
            HeartbeatControlMessage decode = this.mApplicationProtocol.decode(message);
            HeartbeatCommandInvoker invoker = getInvoker(decode.getType());
            if (invoker != null) {
                invoker.execute(decode);
            } else {
                log.info("onMessage", "no invoker registered for given type; ignoring command", "type", decode.getType(), "message", decode);
            }
        } catch (ProtocolException e) {
            log.error("onMessage", "error occurred while decoding message", e);
        }
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("onMessageFragment is not supported for control messages.");
    }

    public void registerInvoker(HeartbeatControlMessage.Type type, HeartbeatCommandInvoker heartbeatCommandInvoker) {
        this.mRegisteredCommandInvokers.put(type, heartbeatCommandInvoker);
    }

    public void unregisterInvoker(HeartbeatControlMessage.Type type) {
        this.mRegisteredCommandInvokers.remove(type);
    }
}
