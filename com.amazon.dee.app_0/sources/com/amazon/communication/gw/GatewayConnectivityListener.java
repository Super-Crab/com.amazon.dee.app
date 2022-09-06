package com.amazon.communication.gw;

import amazon.communication.connection.ConnectionClosedDetails;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.dp.logger.DPLogger;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes12.dex */
public class GatewayConnectivityListener implements ProtocolSocket.ProtocolSocketStateListener {
    private static final DPLogger log = new DPLogger("TComm.GatewayConnectivityListener");
    private final Set<GatewayStateListener> mListeners = new CopyOnWriteArraySet();
    private int mGatewayConnectionState = 0;

    public void addListener(GatewayStateListener gatewayStateListener) {
        if (gatewayStateListener != null) {
            this.mListeners.add(gatewayStateListener);
            return;
        }
        throw new IllegalArgumentException("listener must not be null");
    }

    public int getGatewayConnectivityState() {
        return this.mGatewayConnectionState;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        String str;
        int i;
        CloseDetail closeDetail;
        ConnectionClosedDetails convertToConnectionClosedDetails;
        if (protocolSocket.socketState().toConnectionState() != 4 || (closeDetail = protocolSocket.closeDetail()) == null || (convertToConnectionClosedDetails = closeDetail.convertToConnectionClosedDetails()) == null) {
            str = "";
            i = 0;
        } else {
            i = convertToConnectionClosedDetails.getDetailsCode();
            str = convertToConnectionClosedDetails.getMessage();
        }
        log.debug("notifyStateChanged", "state changed", "state", protocolSocket.socketState(), "connection state", Integer.valueOf(protocolSocket.socketState().toConnectionState()));
        this.mGatewayConnectionState = protocolSocket.socketState().toConnectionState();
        for (GatewayStateListener gatewayStateListener : this.mListeners) {
            gatewayStateListener.onConnectionStateChanged(protocolSocket.socketState().toConnectionState(), i, str);
        }
    }

    public void removeListener(GatewayStateListener gatewayStateListener) {
        if (gatewayStateListener != null) {
            this.mListeners.remove(gatewayStateListener);
            return;
        }
        throw new IllegalArgumentException("listener must not be null");
    }
}
