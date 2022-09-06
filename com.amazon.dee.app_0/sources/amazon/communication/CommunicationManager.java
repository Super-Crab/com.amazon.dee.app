package amazon.communication;

import amazon.communication.connection.Connection;
import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface CommunicationManager {
    @FireOsSdk
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.CommunicationManager";

    @FireOsSdk
    @Deprecated
    Connection acquireConnectedConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, Connection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    @FireOsSdk
    Connection acquireConnectedConnection(EndpointIdentity endpointIdentity, Policy policy, Connection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    @FireOsSdk
    @Deprecated
    Connection acquireConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, Connection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    @FireOsSdk
    Connection acquireConnection(EndpointIdentity endpointIdentity, Policy policy, Connection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    @FireOsSdk
    void deregisterMessageHandler(int i) throws RegistrationFailedException;

    @FireOsSdk
    IdentityResolver getIdentityResolver();

    @FireOsSdk
    void registerMessageHandler(int i, MessageHandler messageHandler) throws RegistrationFailedException;
}
