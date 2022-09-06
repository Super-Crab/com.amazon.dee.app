package com.amazon.communication;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.DuplicateHandlerException;
import amazon.communication.GatewayConnectivity;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RegistrationFailedException;
import amazon.communication.RemoteCommunicationManagerBase;
import amazon.communication.ServiceConnectedHandler;
import amazon.communication.ServiceConnectivityListener;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.rlm.AckHandler;
import amazon.communication.rlm.ReliableConnection;
import amazon.communication.rlm.ReliableICommunicationManager;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.communication.AndroidTCommServiceConnection;
import com.amazon.communication.ICommunicationService;
import com.amazon.communication.IConnectionListener;
import com.amazon.communication.rlm.AckHandlerProxy;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class AndroidTCommManager extends TCommManager implements RemoteCommunicationManagerBase, ReliableICommunicationManager, IBinder.DeathRecipient {
    private static final DPLogger log = new DPLogger("TComm.AndroidTCommManager");
    private GatewayConnectivityImpl mGatewayConnectivity;
    private Object mGatewayConnectivityLock;
    protected final AndroidTCommServiceConnection mServiceConnection;

    public AndroidTCommManager(Context context) {
        super(new AndroidIdentityResolver(context), null);
        this.mGatewayConnectivityLock = new Object();
        this.mServiceConnection = new AndroidTCommServiceConnection(context);
        this.mServiceConnection.bindTCommService();
    }

    private void resetGatewayConnectivity() {
        synchronized (this.mGatewayConnectivityLock) {
            this.mGatewayConnectivity = null;
        }
    }

    @Override // amazon.communication.rlm.ReliableICommunicationManager
    public ReliableConnection acquireConnectedReliableConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener, int i) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return (ReliableConnection) acquireConnectedConnection(endpointIdentity, policy, connectionListener, i);
    }

    @Override // amazon.communication.rlm.ReliableICommunicationManager
    public ReliableConnection acquireReliableConnection(EndpointIdentity endpointIdentity, Policy policy, IConnection.ConnectionListener connectionListener) throws ConnectionAcquisitionFailedException, MissingCredentialsException {
        return (ReliableConnection) acquireConnection(endpointIdentity, policy, connectionListener);
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        resetGatewayConnectivity();
        this.mServiceConnection.mState = AndroidTCommServiceConnection.State.UNBOUND;
    }

    public void close() {
        this.mServiceConnection.unbindTCommService();
    }

    @Override // amazon.communication.RemoteCommunicationManagerBase
    public void deregisterServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener) {
        this.mServiceConnection.deregisterServiceConnectivityListener(serviceConnectivityListener);
    }

    public GatewayConnectivity getGatewayConnectivity() throws amazon.communication.TCommServiceDownException {
        try {
            synchronized (this.mGatewayConnectivityLock) {
                if (this.mGatewayConnectivity == null) {
                    GatewayConnectivityImpl gatewayConnectivityImpl = new GatewayConnectivityImpl();
                    ParcelableStatus parcelableStatus = new ParcelableStatus();
                    IGatewayConnectivity gatewayConnectivity = getService().getGatewayConnectivity(IConnectionListener.Stub.asInterface(gatewayConnectivityImpl), parcelableStatus);
                    if (gatewayConnectivity != null) {
                        gatewayConnectivityImpl.setGatewayConnectivityInterface(gatewayConnectivity);
                        this.mGatewayConnectivity = gatewayConnectivityImpl;
                        log.debug("getGatewayConnectivity", "gateway connectivity object created", new Object[0]);
                    } else if (parcelableStatus.getStatusCode() == 8) {
                        resetGatewayConnectivity();
                        throw new amazon.communication.TCommServiceDownException(parcelableStatus.getStatusMessage());
                    } else {
                        resetGatewayConnectivity();
                        throw new amazon.communication.TCommServiceDownException("Unknown error occurred getting the GatewayConnectivity object from the service: " + parcelableStatus.getStatusMessage());
                    }
                }
            }
            return this.mGatewayConnectivity;
        } catch (RemoteException e) {
            resetGatewayConnectivity();
            throw new amazon.communication.TCommServiceDownException(e);
        } catch (NullPointerException e2) {
            resetGatewayConnectivity();
            throw new amazon.communication.TCommServiceDownException(e2);
        }
    }

    @Override // com.amazon.communication.TCommManager
    protected ICommunicationService getService() throws amazon.communication.TCommServiceDownException {
        ICommunicationService asInterface = ICommunicationService.Stub.asInterface(this.mServiceConnection.getBinder());
        if (asInterface != null) {
            return asInterface;
        }
        throw new amazon.communication.TCommServiceDownException("acquired null instance of ICommunicationService");
    }

    @Override // amazon.communication.RemoteCommunicationManagerBase
    public void registerServiceConnectedHandler(ServiceConnectedHandler serviceConnectedHandler) {
        this.mServiceConnection.registerServiceConnectedHandler(serviceConnectedHandler);
    }

    @Override // amazon.communication.RemoteCommunicationManagerBase
    public void registerServiceConnectivityListener(ServiceConnectivityListener serviceConnectivityListener) {
        this.mServiceConnection.registerServiceConnectivityListener(serviceConnectivityListener);
    }

    @Override // amazon.communication.rlm.ReliableICommunicationManager
    public void removeAckHandler() throws RegistrationFailedException {
        try {
            getService().removeAckHandler();
        } catch (amazon.communication.TCommServiceDownException e) {
            log.warn("removeAckHander", "TComm service is down", new Object[0]);
            throw new RegistrationFailedException(e);
        } catch (RemoteException unused) {
            log.warn("removeAckHandler", "error deregistering handler", new Object[0]);
            throw new RegistrationFailedException("Unable to contact service");
        } catch (NullPointerException e2) {
            log.warn("removeAckHander", "TComm service is down, NPE was thrown", new Object[0]);
            throw new RegistrationFailedException(e2);
        }
    }

    @Override // amazon.communication.rlm.ReliableICommunicationManager
    public void setAckHandler(AckHandler ackHandler) throws RegistrationFailedException {
        try {
            int ackHandler2 = getService().setAckHandler(new AckHandlerProxy(ackHandler));
            if (ackHandler2 == 0) {
                return;
            }
            if (ackHandler2 == 2000) {
                throw new DuplicateHandlerException("Cannot register duplicate handler");
            }
            throw new RegistrationFailedException("Internal error during registration");
        } catch (amazon.communication.TCommServiceDownException e) {
            log.warn("setAckHandler", "TComm service is down", new Object[0]);
            throw new RegistrationFailedException(e);
        } catch (RemoteException e2) {
            log.warn("setAckHandler", "error registering handler", new Object[0]);
            throw new RegistrationFailedException(e2);
        } catch (NullPointerException e3) {
            log.warn("setAckHandler", "TComm service is down, NPE was thrown", new Object[0]);
            throw new RegistrationFailedException(e3);
        }
    }
}
