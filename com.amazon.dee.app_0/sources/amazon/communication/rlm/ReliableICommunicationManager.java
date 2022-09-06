package amazon.communication.rlm;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.ICommunicationManager;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes.dex */
public interface ReliableICommunicationManager extends ICommunicationManager {
    ReliableConnection acquireConnectedReliableConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    ReliableConnection acquireReliableConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException;

    void removeAckHandler() throws RegistrationFailedException;

    void setAckHandler(AckHandler ackHandler) throws RegistrationFailedException;
}
