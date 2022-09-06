package amazon.communication;

import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionDelegate;
import amazon.communication.connection.ConnectionListenerDelegate;
import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import com.amazon.communication.AndroidTCommManager;
import com.amazon.communication.ConnectionProxy;
/* loaded from: classes.dex */
class CommunicationManagerDelegate implements CommunicationManager, RemoteCommunicationManager {
    private AndroidTCommManager delegate;

    public CommunicationManagerDelegate(AndroidTCommManager androidTCommManager) {
        this.delegate = androidTCommManager;
    }

    @Deprecated
    public IConnection acquireConnectedConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return this.delegate.acquireConnectedConnection(endpointIdentity, connectionPolicy, connectionListener, i);
    }

    @Deprecated
    public IConnection acquireConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return this.delegate.acquireConnection(endpointIdentity, connectionPolicy, connectionListener);
    }

    @Override // amazon.communication.CommunicationManager
    public void deregisterMessageHandler(int i) throws RegistrationFailedException {
        this.delegate.deregisterMessageHandler(i);
    }

    @Override // amazon.communication.RemoteCommunicationManager
    public void deregisterServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener) {
        this.delegate.deregisterServiceConnectivityListener(serviceConnectivityListener);
    }

    @Override // amazon.communication.CommunicationManager
    public IdentityResolver getIdentityResolver() {
        return this.delegate.getIdentityResolver();
    }

    @Override // amazon.communication.CommunicationManager
    public void registerMessageHandler(int i, MessageHandler messageHandler) throws RegistrationFailedException {
        this.delegate.registerMessageHandler(i, messageHandler);
    }

    @Override // amazon.communication.RemoteCommunicationManager
    @Deprecated
    public void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler) {
        this.delegate.registerServiceConnectedHandler(serviceConnectedHandler);
    }

    @Override // amazon.communication.RemoteCommunicationManager
    public void registerServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener) {
        this.delegate.registerServiceConnectivityListener(serviceConnectivityListener);
    }

    public IConnection acquireConnectedConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return this.delegate.acquireConnectedConnection(endpointIdentity, policy, connectionListener, i);
    }

    public IConnection acquireConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return this.delegate.acquireConnection(endpointIdentity, policy, connectionListener);
    }

    @Override // amazon.communication.CommunicationManager
    public Connection acquireConnectedConnection(EndpointIdentity endpointIdentity, Policy policy, Connection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return new ConnectionDelegate((ConnectionProxy) this.delegate.acquireConnectedConnection(endpointIdentity, policy, new ConnectionListenerDelegate(connectionListener), i));
    }

    @Override // amazon.communication.CommunicationManager
    public Connection acquireConnection(EndpointIdentity endpointIdentity, Policy policy, Connection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return new ConnectionDelegate((ConnectionProxy) this.delegate.acquireConnection(endpointIdentity, policy, new ConnectionListenerDelegate(connectionListener)));
    }

    @Override // amazon.communication.CommunicationManager
    public Connection acquireConnectedConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, Connection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return new ConnectionDelegate((ConnectionProxy) this.delegate.acquireConnectedConnection(endpointIdentity, connectionPolicy, new ConnectionListenerDelegate(connectionListener), i));
    }

    @Override // amazon.communication.CommunicationManager
    public Connection acquireConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, Connection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return new ConnectionDelegate((ConnectionProxy) this.delegate.acquireConnection(endpointIdentity, connectionPolicy, new ConnectionListenerDelegate(connectionListener)));
    }
}
