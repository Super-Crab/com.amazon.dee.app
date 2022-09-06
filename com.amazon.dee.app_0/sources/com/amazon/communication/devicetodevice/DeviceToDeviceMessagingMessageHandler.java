package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class DeviceToDeviceMessagingMessageHandler implements MessageHandler {
    private static final DPLogger log = new DPLogger("TComm:DeviceToDeviceMessagingMessageHandler");
    private final DeviceD2DApplicationProtocol mD2DProtocol;

    public DeviceToDeviceMessagingMessageHandler(DeviceD2DApplicationProtocol deviceD2DApplicationProtocol) {
        this.mD2DProtocol = deviceD2DApplicationProtocol;
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        log.debug("onMessage", "message received!", "origin", EndpointIdentity.logSafe(endpointIdentity), "message", message);
        try {
            this.mD2DProtocol.decode(message, endpointIdentity);
        } catch (ProtocolException e) {
            log.error("onMessage", "error occurred while decoding message", e);
        }
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        log.warn("onMessageFragment", "we do not expect to receive fragmented messages on this channel!", "origin", EndpointIdentity.logSafe(endpointIdentity), "messageId", Integer.valueOf(i), "message", message, "moreToCome", Boolean.valueOf(z));
    }
}
