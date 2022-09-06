package amazon.communication;

import amazon.communication.connection.ConnectionClosedDetails;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface GatewayConnectivity {

    /* loaded from: classes.dex */
    public interface GatewayConnectivityMonitor {
        @FireOsSdk
        void onGatewayConnectionClosed(ConnectionClosedDetails connectionClosedDetails);

        @FireOsSdk
        void onGatewayConnectionEstablished();
    }

    @FireOsSdk
    void deregisterGatewayConnectivityMonitor(GatewayConnectivityMonitor gatewayConnectivityMonitor);

    @FireOsSdk
    int getGatewayConnectionState() throws TCommServiceDownException;

    @FireOsSdk
    int registerGatewayConnectivityMonitor(GatewayConnectivityMonitor gatewayConnectivityMonitor);
}
