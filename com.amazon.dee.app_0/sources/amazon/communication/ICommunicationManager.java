package amazon.communication;

import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
/* loaded from: classes.dex */
public interface ICommunicationManager {
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.ICommunicationManager";

    @Deprecated
    IConnection acquireConnectedConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    IConnection acquireConnectedConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    @Deprecated
    IConnection acquireConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    IConnection acquireConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    void deregisterMessageHandler(int i) throws RegistrationFailedException;

    IdentityResolver getIdentityResolver();

    void registerMessageHandler(int i, MessageHandler messageHandler) throws RegistrationFailedException;
}
