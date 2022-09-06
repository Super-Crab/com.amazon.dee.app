package com.amazon.communication;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.DuplicateHandlerException;
import amazon.communication.MessageHandler;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.SecurePortNotDefinedException;
import amazon.communication.WiFiUnavailableException;
import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import android.os.RemoteException;
import com.amazon.communication.IConnectionListener;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public abstract class TCommManager extends ICommunicationManagerBase {
    private static final DPLogger log = new DPLogger("TComm.TCommManager");
    private final MapAccountManagerWrapper mAccountManager;
    private final IdentityResolver mIdentityResolver;

    public TCommManager(IdentityResolver identityResolver, MapAccountManagerWrapper mapAccountManagerWrapper) {
        this.mIdentityResolver = identityResolver;
        this.mAccountManager = mapAccountManagerWrapper;
    }

    @Override // amazon.communication.ICommunicationManager
    public amazon.communication.connection.IConnection acquireConnection(EndpointIdentity endpointIdentity, ConnectionPolicy connectionPolicy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return acquireConnection(endpointIdentity, new Policy.Builder().fromConnectionPolicy(connectionPolicy), connectionListener);
    }

    @Override // amazon.communication.ICommunicationManager
    public void deregisterMessageHandler(int i) throws RegistrationFailedException {
        try {
            getService().deregisterMessageHandler(i);
        } catch (amazon.communication.TCommServiceDownException e) {
            log.warn("deregisterMessageHandler", "TComm service is down", new Object[0]);
            throw new RegistrationFailedException(e);
        } catch (RemoteException unused) {
            log.warn("deregisterMessageHandler", "error deregistering handler", "channel", Integer.valueOf(i));
            throw new RegistrationFailedException("Unable to contact service");
        } catch (NullPointerException e2) {
            log.warn("deregisterMessageHandler", "TComm service is down, NPE was thrown", new Object[0]);
            throw new RegistrationFailedException(e2);
        }
    }

    @Override // amazon.communication.ICommunicationManager
    public IdentityResolver getIdentityResolver() {
        try {
            if (!getService().isInitialized()) {
                log.warn("getIdentityResolver", "CommunicationService has not finished initialization", new Object[0]);
            }
            return this.mIdentityResolver;
        } catch (amazon.communication.TCommServiceDownException e) {
            log.warn("getIdentityResolver", "TComm service is down", e);
            return null;
        } catch (RemoteException e2) {
            log.warn("getIdentityResolver", "RemoteException while checking CommunicationService status", e2);
            return null;
        } catch (NullPointerException e3) {
            log.warn("getIdentityResolver", "NPE while checking CommunicationService status. TComm service is probably down", e3);
            return null;
        }
    }

    protected abstract ICommunicationService getService() throws amazon.communication.TCommServiceDownException;

    @Override // amazon.communication.ICommunicationManager
    public void registerMessageHandler(int i, MessageHandler messageHandler) throws RegistrationFailedException {
        try {
            int registerMessageHandler = getService().registerMessageHandler(i, new MessageHandlerProxy(messageHandler));
            if (registerMessageHandler == 0) {
                return;
            }
            if (registerMessageHandler == 2000) {
                throw new DuplicateHandlerException("Cannot register duplicate handler");
            }
            throw new RegistrationFailedException("Internal error during registration");
        } catch (amazon.communication.TCommServiceDownException e) {
            log.warn("registerMessageHandler", "TComm service is down", new Object[0]);
            throw new RegistrationFailedException(e);
        } catch (RemoteException e2) {
            log.warn("registerMessageHandler", "error registering handler", "channel", Integer.valueOf(i));
            throw new RegistrationFailedException(e2);
        } catch (NullPointerException e3) {
            log.warn("registerMessageHandler", "TComm service is down, NPE was thrown", new Object[0]);
            throw new RegistrationFailedException(e3);
        }
    }

    @Override // amazon.communication.ICommunicationManager
    public amazon.communication.connection.IConnection acquireConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        log.verbose("acquireConnection", "acquiring connection", "destination", EndpointIdentity.logSafe(endpointIdentity), "policy", policy);
        if (policy != null) {
            if (endpointIdentity != null) {
                try {
                    ConnectionProxy connectionProxy = new ConnectionProxy(policy.isRequestResponseOnly());
                    if (connectionListener != null) {
                        connectionProxy.addConnectionListener(connectionListener);
                    }
                    ParcelableStatus parcelableStatus = new ParcelableStatus();
                    IConnection acquireConnectionEx = getService().acquireConnectionEx(new ParcelableEndpointIdentity(endpointIdentity), new ParcelablePolicy(policy), IConnectionListener.Stub.asInterface(connectionProxy), parcelableStatus);
                    switch (parcelableStatus.getStatusCode()) {
                        case 0:
                            if (acquireConnectionEx != null) {
                                connectionProxy.setConnectionInterface(acquireConnectionEx);
                                return connectionProxy;
                            }
                            throw new ConnectionAcquisitionFailedException("Null IConnection. This is probably caused by an IPC failure. TComm client and service may not be compatible.");
                        case 1:
                        case 3:
                        case 4:
                            throw new ConnectionAcquisitionFailedException(DPFormattedMessage.toDPFormat("acquireConnection", parcelableStatus.getStatusMessage(), "destination", EndpointIdentity.logSafe(endpointIdentity)));
                        case 2:
                            throw new MissingCredentialsException("No Amazon account on the device");
                        case 5:
                            throw new WiFiUnavailableException(parcelableStatus.getStatusMessage());
                        case 6:
                            throw new SecurePortNotDefinedException(DPFormattedMessage.toDPFormat("acquireConnection", parcelableStatus.getStatusMessage(), "destination", EndpointIdentity.logSafe(endpointIdentity)));
                        case 7:
                        default:
                            throw new RuntimeException("Invalid acquireConnectionStatus '" + parcelableStatus.getStatusCode() + "'.");
                        case 8:
                            throw new amazon.communication.TCommServiceDownException(parcelableStatus.getStatusMessage());
                    }
                } catch (amazon.communication.TCommServiceDownException e) {
                    log.warn("acquireConnection", "TComm service is down", "destination", EndpointIdentity.logSafe(endpointIdentity));
                    throw new ConnectionAcquisitionFailedException(e);
                } catch (RemoteException e2) {
                    throw new ConnectionAcquisitionFailedException(e2);
                } catch (NullPointerException e3) {
                    log.warn("acquireConnection", "TComm service is down, NPE was thrown", "destination", EndpointIdentity.logSafe(endpointIdentity));
                    throw new ConnectionAcquisitionFailedException(e3);
                }
            }
            throw new IllegalArgumentException("destination must not be null");
        }
        throw new IllegalArgumentException("Missing policy");
    }
}
