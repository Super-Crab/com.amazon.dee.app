package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.connection.ConnectionClosedDetails;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.TransmissionFailedException;
import amazon.communication.rlm.ReliableConnection;
import android.os.RemoteException;
import com.amazon.communication.IConnectionListener;
import com.amazon.communication.connection.ClosedConnectionReasonFactory;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/* loaded from: classes12.dex */
public class ConnectionProxy extends IConnectionListener.Stub implements amazon.communication.connection.IConnection, ReliableConnection {
    private static final DPLogger log = new DPLogger("TComm.ConnectionProxy");
    private IConnection mConnection;
    private final boolean mIsRequestResponseOnly;
    private final Set<IConnection.ConnectionListener> mListeners = Collections.synchronizedSet(new HashSet(2));
    private int mState = 0;
    private Object mStateLock = new Object();
    private Object mConnectionLock = new Object();

    public ConnectionProxy(boolean z) {
        log.verbose("constructor", "Creating new connection proxy", "requestResponseOnly", Boolean.valueOf(z));
        this.mIsRequestResponseOnly = z;
        this.mConnection = null;
    }

    public static void addHeadersFromParams(HttpRequestBase httpRequestBase) {
        HttpParams params = httpRequestBase.getParams();
        if (params != null) {
            httpRequestBase.addHeader(HttpRequestResponseConverterBase.CONNECTION_TIMEOUT_MS_HEADER, Integer.toString(HttpConnectionParams.getConnectionTimeout(params)));
            httpRequestBase.addHeader(HttpRequestResponseConverterBase.SOCKET_TIMEOUT_MS_HEADER, Integer.toString(HttpConnectionParams.getSoTimeout(params)));
        }
    }

    private boolean isValidState(int i) {
        return i >= 0 && i <= 4;
    }

    private void notifyStateClosed(int i, String str) {
        log.debug("notifyStateClosed", "connection closed", "statusCode", Integer.valueOf(i), "closeReason", ClosedConnectionReasonFactory.getReasonForStatusCode(i), "message", str);
        synchronized (this.mListeners) {
            for (IConnection.ConnectionListener connectionListener : this.mListeners) {
                connectionListener.onClosed(this, new ConnectionClosedDetails(i, str));
            }
        }
    }

    private void notifyStateOpened() {
        synchronized (this.mListeners) {
            for (IConnection.ConnectionListener connectionListener : this.mListeners) {
                connectionListener.onOpened(this);
            }
        }
    }

    @Override // amazon.communication.connection.IConnection
    public void addConnectionListener(IConnection.ConnectionListener connectionListener) {
        log.verbose("addConnectionListener", "Adding connection listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, connectionListener);
        if (connectionListener != null) {
            this.mListeners.add(connectionListener);
            return;
        }
        throw new IllegalArgumentException("Connection listener cannot be null");
    }

    @Override // amazon.communication.connection.IConnection
    public int getConnectionState() {
        return this.mState;
    }

    @Override // com.amazon.communication.IConnectionListener
    public void onConnectionSetInitialState(int i) throws RemoteException {
        log.verbose("onConnectionSetInitialState", "Setting initial connection state", "state", Integer.valueOf(i));
        try {
            FailFast.expectTrue(isValidState(i));
            synchronized (this.mStateLock) {
                this.mState = i;
            }
        } catch (RuntimeException e) {
            log.warn("onConnectionSetInitialState", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.IConnectionListener
    public void onConnectionStateChanged(int i, int i2, String str) throws RemoteException {
        int i3;
        log.verbose("onConnectionStateChanged", "Connection state changed", "state", Integer.valueOf(i), "statusCode", Integer.valueOf(i2), "message", str);
        try {
            FailFast.expectTrue(isValidState(i));
            synchronized (this.mStateLock) {
                i3 = this.mState;
                this.mState = i;
            }
            if (i3 != 4 && i == 4) {
                notifyStateClosed(i2, str);
            } else if (i3 == 2 || i != 2) {
            } else {
                notifyStateOpened();
            }
        } catch (RuntimeException e) {
            log.warn("onConnectionStateChanged", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // amazon.communication.connection.IConnection
    public void release() {
        log.verbose("release", "Releasing connectionProxy", new Object[0]);
        if (this.mConnection != null) {
            try {
                try {
                    try {
                        synchronized (this.mStateLock) {
                            this.mState = 0;
                        }
                        synchronized (this.mConnectionLock) {
                            this.mConnection.release();
                        }
                    } catch (RemoteException e) {
                        log.warn("release", "Error releasing connection, service unavailable", e);
                    }
                } catch (RuntimeException e2) {
                    log.warn("release", "Error releasing connection, service unavailable", e2);
                }
            } finally {
                this.mConnection = null;
            }
        }
    }

    @Override // amazon.communication.connection.IConnection
    public void removeConnectionListener(IConnection.ConnectionListener connectionListener) {
        log.verbose("removeConnectionListener", "Removing connection listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, connectionListener);
        if (connectionListener != null) {
            synchronized (this.mListeners) {
                if (this.mListeners.contains(connectionListener)) {
                    this.mListeners.remove(connectionListener);
                } else {
                    throw new IllegalArgumentException("Connection listener isn't registered to connection");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Connection listener cannot be null");
    }

    @Override // amazon.communication.connection.IConnection
    public void sendMessage(Message message, int i) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException {
        int sendMessage;
        log.verbose("sendMessage", "Sending message", "message", message, "channel", Integer.valueOf(i));
        if (!this.mIsRequestResponseOnly) {
            if (message != null && message.getPayloadSize() != 0) {
                try {
                    synchronized (this.mStateLock) {
                        if (this.mState != 2) {
                            throw new IllegalConnectionStateException("Connection is not open", this.mState);
                        }
                    }
                    synchronized (this.mConnectionLock) {
                        sendMessage = this.mConnection.sendMessage(MessageEnvelope.createInstance(message), i);
                    }
                    if (sendMessage == 3003) {
                        throw new MissingCredentialsException("No Amazon account on the device");
                    }
                    if (sendMessage != 0) {
                        throw new TransmissionFailedException("Sending message failed");
                    }
                    return;
                } catch (RemoteException e) {
                    throw new TransmissionFailedException("Error while communicating with service", e);
                } catch (RuntimeException e2) {
                    throw new TransmissionFailedException("Error while communicating with service", e2);
                }
            }
            throw new IllegalArgumentException("Message must not be null or empty");
        }
        throw new UnsupportedOperationException("Cannot send a TComm message on a Request/Response only connection");
    }

    @Override // amazon.communication.rlm.ReliableConnection
    public void sendReliableMessage(Message message, int i, int i2) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException {
        int sendReliableMessage;
        log.verbose("sendReliableMessage", "Sending Reliable message", "message", message, "channel", Integer.valueOf(i));
        if (!this.mIsRequestResponseOnly) {
            if (message != null && message.getPayloadSize() != 0) {
                try {
                    synchronized (this.mStateLock) {
                        if (this.mState != 2) {
                            throw new IllegalConnectionStateException("Connection is not open", this.mState);
                        }
                    }
                    synchronized (this.mConnectionLock) {
                        sendReliableMessage = this.mConnection.sendReliableMessage(MessageEnvelope.createInstance(message), i2, i);
                    }
                    if (sendReliableMessage == 3003) {
                        throw new MissingCredentialsException("No Amazon account on device");
                    }
                    if (sendReliableMessage != 0) {
                        throw new TransmissionFailedException("Sending reliable message failed");
                    }
                    return;
                } catch (RemoteException e) {
                    throw new TransmissionFailedException("Error while communicating with service", e);
                } catch (RuntimeException e2) {
                    throw new TransmissionFailedException("Error while communicating with service", e2);
                }
            }
            throw new IllegalArgumentException("Message must not be null or empty");
        }
        throw new UnsupportedOperationException("Cannot send a TComm message on a Request/Response only connection");
    }

    @Override // amazon.communication.connection.IConnection
    public void sendRequest(HttpRequestBase httpRequestBase, ResponseHandlerBase responseHandlerBase) throws IllegalConnectionStateException, TransmissionFailedException, MissingCredentialsException {
        int sendRequest;
        log.verbose("sendRequest", "Sending request", "request", httpRequestBase);
        if (httpRequestBase != null) {
            if (responseHandlerBase != null) {
                try {
                    synchronized (this.mStateLock) {
                        if (this.mState != 2) {
                            throw new IllegalConnectionStateException("Not connected", this.mState);
                        }
                    }
                    addHeadersFromParams(httpRequestBase);
                    MessageEnvelope createInstance = MessageEnvelope.createInstance(PlainTextHttpRequestResponseConverter.getInstance().convertRequestToMessage(httpRequestBase));
                    synchronized (this.mConnectionLock) {
                        sendRequest = this.mConnection.sendRequest(createInstance, new ResponseHandlerProxy(responseHandlerBase, PlainTextHttpRequestResponseConverter.getInstance()));
                    }
                    if (sendRequest == 3003) {
                        throw new MissingCredentialsException("No Amazon account on the device.");
                    }
                    if (sendRequest != 0) {
                        throw new TransmissionFailedException("Sending request failed");
                    }
                    return;
                } catch (RemoteException e) {
                    log.verbose("sendRequest", "request sending failed", e);
                    throw new TransmissionFailedException("Error while communicating with service", e);
                } catch (RuntimeException e2) {
                    log.verbose("sendRequest", "request sending failed", e2);
                    throw new TransmissionFailedException("Error while communicating with service", e2);
                }
            }
            throw new IllegalArgumentException("ResponseHandlerBase must not be null");
        }
        throw new IllegalArgumentException("Request must not be null");
    }

    public void setConnectionInterface(IConnection iConnection) {
        if (iConnection != null) {
            if (this.mConnection == null) {
                this.mConnection = iConnection;
                return;
            }
            throw new IllegalStateException("Connection is already set");
        }
        throw new IllegalArgumentException("connection must not be null.");
    }
}
