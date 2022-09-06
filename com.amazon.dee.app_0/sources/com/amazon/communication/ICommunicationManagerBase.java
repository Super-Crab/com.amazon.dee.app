package com.amazon.communication;

import amazon.communication.BlockingConnectionListenerBase;
import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.ICommunicationManager;
import amazon.communication.MissingCredentialsException;
import amazon.communication.TimeoutException;
import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public abstract class ICommunicationManagerBase implements ICommunicationManager {
    @Override // amazon.communication.ICommunicationManager
    public amazon.communication.connection.IConnection acquireConnectedConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return acquireConnectedConnection(endpointIdentity, new Policy.Builder().fromConnectionPolicy(connectionPolicy), connectionListener, i);
    }

    @Override // amazon.communication.ICommunicationManager
    public amazon.communication.connection.IConnection acquireConnectedConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        BlockingConnectionListenerBase blockingConnectionListenerBase = new BlockingConnectionListenerBase(connectionListener, i);
        amazon.communication.connection.IConnection acquireConnection = acquireConnection(endpointIdentity, policy, blockingConnectionListenerBase);
        try {
            blockingConnectionListenerBase.waitForConnectionOpen(acquireConnection);
            return acquireConnection;
        } catch (TimeoutException e) {
            acquireConnection.release();
            throw new ConnectionAcquisitionFailedException(e);
        } catch (InterruptedException e2) {
            acquireConnection.release();
            throw new ConnectionAcquisitionFailedException(e2);
        }
    }
}
