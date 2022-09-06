package com.amazon.communication.rlm;

import amazon.communication.DuplicateHandlerException;
import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.rlm.AckHandler;
import com.amazon.communication.ChannelRestrictor;
import com.amazon.communication.CommunicationEngine;
import com.amazon.communication.MessageRouter;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.framework.StreamCodec;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public class DeviceReliableMessageProtocol extends ReliableMessageProtocol {
    private static final DPLogger log = new DPLogger("TComm.DeviceReliableMessageProtocol");
    private final ConcurrentMap<String, AckHandler> mAckHandlersMap;
    private final ChannelRestrictor mChannelRestrictor;
    private final CommunicationEngine mCommunicationEngine;
    private final MessageRouter mMessageRouter;
    private final StreamCodec mStreamCodec;

    public DeviceReliableMessageProtocol(StreamCodec streamCodec, MessageRouter messageRouter, CommunicationEngine communicationEngine, ChannelRestrictor channelRestrictor) {
        super(streamCodec);
        this.mStreamCodec = streamCodec;
        this.mMessageRouter = messageRouter;
        this.mCommunicationEngine = communicationEngine;
        this.mChannelRestrictor = channelRestrictor;
        this.mAckHandlersMap = new ConcurrentHashMap();
    }

    private void recordReceivedAck(long j) {
        log.verbose("recordReceivedAck", "recording received ack data", "timeElapsed", Long.valueOf(j));
    }

    private void recordReceivedNack(long j) {
        log.verbose("recordReceivedNack", "recording received nack data", "timeElapsed", Long.valueOf(j));
    }

    private void recordReceivedPack(long j) {
        log.verbose("recordReceivedPack", "recording received pack data", "timeElapsed", Long.valueOf(j));
    }

    private void sendResponse(Message message, EndpointIdentity endpointIdentity) {
        try {
            ProtocolSocket acquireProtocolSocket = this.mCommunicationEngine.acquireProtocolSocket(endpointIdentity, new Policy.Builder().build(), null, null);
            log.verbose("sendResponse", "acquired the socket", new Object[0]);
            acquireProtocolSocket.sendMessage(message, ProtocolHandler.MESSAGE_MESSAGE_TYPE, 100);
        } catch (MissingCredentialsException e) {
            log.warn("sendResponse", "unable to send message over protocol socket", e);
        } catch (SocketAcquisitionFailedException e2) {
            log.warn("sendResponse", "unable to acquire a connection back to device to send Ack", e2);
        } catch (IOException e3) {
            log.warn("sendResponse", "unable to send message over protocol socket", e3);
        } catch (Exception e4) {
            log.warn("sendResponse", "unable to send message over protocol socket", e4);
        }
    }

    @Override // com.amazon.communication.rlm.ReliableMessageProtocol
    public void decode(Message message, EndpointIdentity endpointIdentity, int i) throws ProtocolException {
        ReliableMessage decipher = decipher(message);
        if (!this.mChannelRestrictor.isAuthorized(decipher.channel, endpointIdentity)) {
            log.error("decode", "received a message to a restricted channel from prohibited sender", "sender", EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(decipher.channel));
            return;
        }
        String str = decipher.messageType;
        if (ReliableMessageProtocol.RELIABLE_MESSAGE_TYPE.equals(str)) {
            handleReliableMessage(decipher, endpointIdentity, i);
        } else if (ReliableMessageProtocol.RELIABLE_MESSAGE_RESPONSE_TYPES.contains(str)) {
            handleResponseMessage(decipher, endpointIdentity, i);
        } else {
            throw new ProtocolException(GeneratedOutlineSupport1.outline72("Unknown reliable message type: ", str));
        }
    }

    protected void handleReliableMessage(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity, int i) throws ProtocolException {
        if (i != 99) {
            log.warn("handleReliableMessage", "received a RELIABLE_MESSAGE_TYPE not on the RLM_CHANNEL", "sender", EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i), "message", reliableMessage);
        } else if (this.mMessageRouter.getMessageHandler(reliableMessage.channel) != null) {
            this.mMessageRouter.routeMessage(endpointIdentity, reliableMessage.message, reliableMessage.channel);
            sendAckResponse(reliableMessage, endpointIdentity);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No Message Handler was listening on channel ");
            outline107.append(reliableMessage.channel);
            sendNackResponse(reliableMessage, endpointIdentity, 2000, outline107.toString());
        }
    }

    protected void handleResponseMessage(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity, int i) throws ProtocolException {
        if (i != 100) {
            log.warn("handleResponseMessage", "received a RESPONSE_MESSAGE_TYPES not on the RLM_RESPONSE_CHANNEL", "sender", EndpointIdentity.logSafe(endpointIdentity), "receivedOnChannel", Integer.valueOf(i), "message", reliableMessage);
            return;
        }
        String str = reliableMessage.messageType;
        String decodeStringFromMessage = decodeStringFromMessage(reliableMessage.message);
        AckHandler ackHandler = this.mAckHandlersMap.get(reliableMessage.clientIdentifier);
        if (ackHandler == null) {
            log.warn("handleNackMessage", "no registered ack handler", AuthorizationResponseParser.CLIENT_ID_STATE, reliableMessage.clientIdentifier);
        } else if (ReliableMessageProtocol.ACK_MESSAGE_TYPE.equals(str)) {
            ackHandler.onAck(reliableMessage.messageId);
            recordReceivedAck(GlobalTimeSource.INSTANCE.currentTimeMillis() - reliableMessage.timeStart);
        } else if (ReliableMessageProtocol.NACK_MESSAGE_TYPE.equals(str)) {
            ackHandler.onNack(reliableMessage.messageId, reliableMessage.reliableMessageCode, decodeStringFromMessage);
            recordReceivedNack(GlobalTimeSource.INSTANCE.currentTimeMillis() - reliableMessage.timeStart);
        } else if (!ReliableMessageProtocol.PACK_MESSAGE_TYPE.equals(str)) {
        } else {
            ackHandler.onPack(reliableMessage.messageId, reliableMessage.reliableMessageCode, decodeStringFromMessage);
            recordReceivedPack(GlobalTimeSource.INSTANCE.currentTimeMillis() - reliableMessage.timeStart);
        }
    }

    public void removeAckHandler(String str) {
        if (str == null) {
            log.warn("removeAckHandler", "cannot deregister from a null clientId", AuthorizationResponseParser.CLIENT_ID_STATE, str);
        } else {
            log.info("deregisterAckHandler", "removed AckHandler", AuthorizationResponseParser.CLIENT_ID_STATE, str, "AckHandler", this.mAckHandlersMap.remove(str));
        }
    }

    @Override // com.amazon.communication.rlm.ReliableMessageProtocol
    public void sendAckResponse(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity) throws ProtocolException {
        sendResponse(encode(createResponseMessage(reliableMessage, ReliableMessageProtocol.ACK_MESSAGE_TYPE, createMessageFromString(ReliableMessageProtocol.ACK_MESSAGE_TYPE), 1000)), endpointIdentity);
    }

    @Override // com.amazon.communication.rlm.ReliableMessageProtocol
    public void sendNackResponse(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity, int i, String str) throws ProtocolException {
        sendResponse(encode(createResponseMessage(reliableMessage, ReliableMessageProtocol.NACK_MESSAGE_TYPE, createMessageFromString(str), i)), endpointIdentity);
    }

    @Override // com.amazon.communication.rlm.ReliableMessageProtocol
    public void sendPackResponse(ReliableMessage reliableMessage, EndpointIdentity endpointIdentity, int i, String str) throws ProtocolException {
        sendResponse(encode(createResponseMessage(reliableMessage, ReliableMessageProtocol.PACK_MESSAGE_TYPE, createMessageFromString(str), i)), endpointIdentity);
    }

    public void setAckHandler(String str, AckHandler ackHandler) throws RegistrationFailedException, DuplicateHandlerException {
        if (ackHandler == null) {
            log.warn("registerAckHandler", "null AckHandler cannot be registered", new Object[0]);
            throw new RegistrationFailedException("Null AckHandlers cannot be registered");
        } else if (str != null) {
            if (this.mAckHandlersMap.putIfAbsent(str, ackHandler) == null) {
                log.verbose("setAckHandler", "set AckHandler", AuthorizationResponseParser.CLIENT_ID_STATE, str, "AckHandler", ackHandler);
            } else {
                log.warn("setAckHandler", "duplicate registration of AckHandler", AuthorizationResponseParser.CLIENT_ID_STATE, str);
                throw new DuplicateHandlerException("Duplicate registration for this clientId");
            }
        } else {
            log.warn("registerAckHandler", "null clientId cannot be mapped to an handler", new Object[0]);
            throw new RegistrationFailedException("Null clientIds cannot be mapped to an AckHandler");
        }
    }

    public void shutDown() {
        this.mAckHandlersMap.clear();
    }
}
