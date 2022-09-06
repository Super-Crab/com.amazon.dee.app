package com.amazon.communication.socket;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.Channels;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayMessage;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
/* loaded from: classes12.dex */
public class GatewaySocket extends ProtocolSocketBase implements ProtocolSocket.ProtocolSocketStateListener {
    private static final DPLogger log = new DPLogger("TComm.GatewaySocket");
    private final GatewayApplicationProtocol mGatewayProtocol;
    protected final ProtocolSocket mProtocolSocket;
    private final Object mRefCountLock = new Object();

    public GatewaySocket(EndpointIdentity endpointIdentity, GatewayApplicationProtocol gatewayApplicationProtocol, ProtocolSocket protocolSocket) {
        if (endpointIdentity != null) {
            if (gatewayApplicationProtocol == null) {
                throw new IllegalArgumentException("GatewayApplicationProtocol is null");
            }
            if (protocolSocket != null) {
                this.mIdentity = endpointIdentity;
                this.mGatewayProtocol = gatewayApplicationProtocol;
                this.mProtocolSocket = protocolSocket;
                this.mProtocolSocket.retain();
                this.mProtocolSocket.addStateListener(this);
                return;
            }
            throw new IllegalArgumentException("ProtocolSocket is null");
        }
        throw new IllegalArgumentException("EndpointIdentity is null");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void close(CloseDetail closeDetail) {
        throw new UnsupportedOperationException("Gateway sockets should never be closed. Call release to decrement the refcount of the underlying gateway connection");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseDetail closeDetail() {
        return this.mProtocolSocket.closeDetail();
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseReason closeReason() {
        return this.mProtocolSocket.closeReason();
    }

    public ProtocolSocket getInternalProtocolSocket() {
        return this.mProtocolSocket;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        ProtocolSocket protocolSocket2 = this.mProtocolSocket;
        if (protocolSocket != protocolSocket2) {
            log.error("notifyStateChanged", "unexpected callback - received notification for a socket different than what I have.", "mProtocolSocket", protocolSocket2, "socket", protocolSocket);
        } else {
            notifyStateChanged();
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int release() {
        int release;
        synchronized (this.mRefCountLock) {
            release = super.release();
            if (release == 0) {
                this.mProtocolSocket.removeStateListener(this);
                this.mProtocolSocket.release();
            }
        }
        return release;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int retain() {
        int retain;
        synchronized (this.mRefCountLock) {
            retain = super.retain();
        }
        return retain;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void sendMessage(Message message, String str, int i) throws IOException, MissingCredentialsException {
        GatewayMessage gatewayMessage = new GatewayMessage();
        gatewayMessage.message = message;
        gatewayMessage.messageType = str;
        gatewayMessage.channel = i;
        gatewayMessage.destination = this.mIdentity;
        gatewayMessage.origin = null;
        this.mProtocolSocket.sendMessage(this.mGatewayProtocol.encode(gatewayMessage), ProtocolHandler.MESSAGE_MESSAGE_TYPE, Channels.GW_CHANNEL);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public ProtocolSocket.ProtocolSocketState socketState() {
        return this.mProtocolSocket.socketState();
    }
}
