package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.connection.Channels;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.ServiceIdentity;
import amazon.communication.serialize.ObjectMapper;
import amazon.communication.serialize.ObjectMapperFactory;
import amazon.communication.sync.SyncPayloadWrapper;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.dp.logger.DPLogger;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class DeviceUndeliverableMessageHandler implements UndeliverableMessageHandler {
    private static final String METRICS_SOURCE = "DeviceUndeliverableMessageHandler";
    private static final String SDCS_SERVICE_NAME = "SynchronousDeviceCallingService";
    private final CommunicationEngine mCommunicationEngine;
    private final ObjectMapper mObjectMapper = ObjectMapperFactory.newObjectMapper(ObjectMapperFactory.ContentType.JSON);
    private static final DPLogger log = new DPLogger("TComm.DeviceUndeliverableMessageHandler");
    private static final Policy GATEWAY_FRIENDLY_POLICY = new Policy.Builder().setIsRequestResponseOnly(false).setIsLowLatencyNecessary(false).setIsAnonymousCredentialsAllowed(true).build();

    public DeviceUndeliverableMessageHandler(CommunicationEngine communicationEngine) {
        this.mCommunicationEngine = communicationEngine;
    }

    private boolean handleUndeliverableSdcsMessage(EndpointIdentity endpointIdentity, Message message) {
        if (!(endpointIdentity instanceof ServiceIdentity)) {
            log.debug("handleUndeliverableSdcsMessage", "source is not a service idenity", "source", EndpointIdentity.logSafe(endpointIdentity));
            return false;
        } else if (!SDCS_SERVICE_NAME.equals(((ServiceIdentity) endpointIdentity).getServiceName())) {
            log.debug("handleUndeliverableSdcsMessage", "source is not a SynchronousDeviceCallingService", "source", EndpointIdentity.logSafe(endpointIdentity));
            return false;
        } else {
            try {
                SyncPayloadWrapper syncPayloadWrapper = (SyncPayloadWrapper) this.mObjectMapper.deserialize(message.getPayload(), SyncPayloadWrapper.class);
                return sendNoListenerSdcsResponse(endpointIdentity, syncPayloadWrapper.getMessageId(), syncPayloadWrapper.getPayload());
            } catch (Exception unused) {
                log.warn("handleUndeliverableSdcsMessage", "error de-serializing: probably not a SDCS message, ignoring", "source", EndpointIdentity.logSafe(endpointIdentity), "message", message);
                return false;
            }
        }
    }

    private boolean sendNoListenerSdcsResponse(EndpointIdentity endpointIdentity, String str, ByteBuffer byteBuffer) {
        SyncPayloadWrapper syncPayloadWrapper = new SyncPayloadWrapper();
        syncPayloadWrapper.setMessageId(str);
        syncPayloadWrapper.setErrorCode(SyncPayloadWrapper.NO_LISTENER_ERROR);
        try {
            ProtocolSocket acquireProtocolSocket = this.mCommunicationEngine.acquireProtocolSocket(endpointIdentity, GATEWAY_FRIENDLY_POLICY, null, null);
            log.debug("handleUndeliverableSdcsMessage", "acquired the socket", new Object[0]);
            acquireProtocolSocket.sendMessage(MessageFactory.createMessage(this.mObjectMapper.serialize(syncPayloadWrapper)), ProtocolHandler.MESSAGE_MESSAGE_TYPE, Channels.SDCS_SYNC_RESPONSE_CHANNEL);
            log.debug("handleUndeliverableSdcsMessage", "sent the response", new Object[0]);
            return true;
        } catch (SocketAcquisitionFailedException e) {
            log.warn("handleUndeliverableSdcsMessage", "unable to acquire a connection back to device to send Ack", e);
            return false;
        } catch (Exception e2) {
            log.warn("handleUndeliverableSdcsMessage", "unable to send message over protocol socket", e2);
            return false;
        }
    }

    @Override // com.amazon.communication.UndeliverableMessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        if (handleUndeliverableSdcsMessage(endpointIdentity, message)) {
            log.info("onMessage", "succesfully handled undeliverable SDCS message", "source", EndpointIdentity.logSafe(endpointIdentity));
        } else {
            log.info("onMessage", "ignored message", "source", EndpointIdentity.logSafe(endpointIdentity));
        }
    }
}
