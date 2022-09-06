package com.amazon.communication.socket.decisionengine;

import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.communication.ConnectivityManagerWrapper;
import com.amazon.communication.SocketDecisionEngineBase;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManager;
import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public abstract class EndpointIdentitySocketDecisionEngine extends SocketDecisionEngineBase {
    protected final ConnectivityManagerWrapper mConnectivityManager;

    public EndpointIdentitySocketDecisionEngine(ConnectivityManagerWrapper connectivityManagerWrapper) {
        this.mConnectivityManager = connectivityManagerWrapper;
    }

    private boolean isDirectConnectionAllowed(IRServiceEndpoint iRServiceEndpoint) {
        return iRServiceEndpoint == null || iRServiceEndpoint.getDirectConnection() != IRServiceEndpoint.DirectConnection.NOT_ALLOWED;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean doesExistingProtocolSocketMeetRequirements(IRServiceEndpoint iRServiceEndpoint, Policy policy) {
        FailFast.expectNotNull(policy);
        return isDirectConnectionAllowed(iRServiceEndpoint);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProtocolSocket reUseExistingProtocolSocket(SocketManager socketManager, EndpointIdentity endpointIdentity, Policy policy, IRServiceEndpoint iRServiceEndpoint) throws SocketAcquisitionFailedException {
        return getValidExistingProtocolSocket(socketManager, endpointIdentity, policy, iRServiceEndpoint);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void toggleNetworkPreference(Policy policy) {
        if (policy.isWiFiNecessary()) {
            this.mConnectivityManager.setNetworkPreference(1);
        }
    }
}
