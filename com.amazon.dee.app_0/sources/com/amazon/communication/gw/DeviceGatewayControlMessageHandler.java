package com.amazon.communication.gw;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.identity.DeviceUniqueEndpointIdentifier;
import com.amazon.communication.socket.EndpointNotAuthenticatedException;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.RemoteDeviceGatewaySocket;
import com.amazon.communication.socket.SocketManager;
import com.amazon.dp.logger.DPLogger;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public class DeviceGatewayControlMessageHandler extends GatewayControlMessageHandler {
    private static final DPLogger log = new DPLogger();
    private final SocketManager mSocketManager;

    public DeviceGatewayControlMessageHandler(GatewayControlProtocol gatewayControlProtocol, SocketManager socketManager) {
        super(gatewayControlProtocol);
        this.mSocketManager = socketManager;
    }

    @Override // com.amazon.communication.gw.GatewayControlMessageHandler
    protected void onGatewayControlMessage(EndpointIdentity endpointIdentity, GatewayControlMessage gatewayControlMessage) {
        Collections.emptyList();
        try {
            List<ProtocolSocket> existingProtocolSockets = this.mSocketManager.getExistingProtocolSockets(new DeviceUniqueEndpointIdentifier(gatewayControlMessage.endpointIdentity), RemoteDeviceGatewaySocket.D2D_ATTRIBUTES);
            if (existingProtocolSockets.size() == 0) {
                log.debug("onGatewayControlMessage", "Received a message for a socket that isn't being tracked", "origin", endpointIdentity, "gatewayCtlMessage", gatewayControlMessage);
            }
            for (ProtocolSocket protocolSocket : existingProtocolSockets) {
                if (protocolSocket instanceof RemoteDeviceGatewaySocket) {
                    ((RemoteDeviceGatewaySocket) protocolSocket).onGatewayControlMessage(gatewayControlMessage);
                } else {
                    log.warn("onGatewayControlMessage", "Got a socket from SocketManager that is not an instance of RemoteDeviceGatewaySocket", "socket", protocolSocket, "origin", endpointIdentity, "gatewayCtlMessage", gatewayControlMessage);
                }
            }
        } catch (EndpointNotAuthenticatedException e) {
            log.warn("onGatewayControlMessage", "got EndpointNotAuthenticatedException when trying to get socket to device", "origin", endpointIdentity, "gatewayCtlMessage", gatewayControlMessage, e);
        }
    }
}
