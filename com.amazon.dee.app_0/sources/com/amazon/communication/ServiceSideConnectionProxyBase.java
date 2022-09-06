package com.amazon.communication;

import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.ConnectionClosedDetails;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.IConnection;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
abstract class ServiceSideConnectionProxyBase extends IConnection.Stub implements ProtocolSocket.ProtocolSocketStateListener, IBinder.DeathRecipient {
    private static final String METRICS_SOURCE_NAME = "ServiceSideConnectionProxyBase";
    private static final DPLogger log = new DPLogger("TComm.ServiceSideConnectionProxyBase");
    private final BandwidthToolByteAccountant mByteAccountant;
    private final AtomicReference<IConnectionListener> mListenerCallback = new AtomicReference<>();
    private MetricEvent mMetricEvent;
    private PeriodicMetricReporter mPeriodicMetricReporter;
    private final ResponseRouter mResponseRouter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceSideConnectionProxyBase(ResponseRouter responseRouter, IConnectionListener iConnectionListener, BandwidthToolByteAccountant bandwidthToolByteAccountant, PeriodicMetricReporter periodicMetricReporter) throws RemoteException {
        this.mResponseRouter = responseRouter;
        this.mListenerCallback.set(iConnectionListener);
        this.mByteAccountant = bandwidthToolByteAccountant;
        try {
            iConnectionListener.asBinder().linkToDeath(this, 0);
            this.mPeriodicMetricReporter = periodicMetricReporter;
            this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        } catch (RemoteException e) {
            binderDied();
            throw e;
        }
    }

    private int sendMessageOnSocket(MessageEnvelope messageEnvelope, int i) {
        try {
            log.verbose("sendMessageOnSocket", "sending message", "channel", Integer.valueOf(i));
            getProtocolSocket().sendMessage(messageEnvelope.toMessage(), ProtocolHandler.MESSAGE_MESSAGE_TYPE, i);
            return 0;
        } catch (MissingCredentialsException e) {
            log.warn("sendMessage", "unable to send message over protocol socket", e);
            return CommunicationErrorCodes.ERR_CONN_MISSING_CREDENTIALS;
        } catch (IOException e2) {
            log.warn("sendMessage", "unable to send message over protocol socket", e2);
            return 3000;
        } catch (Exception e3) {
            log.warn("sendMessage", "unable to send message over protocol socket. Runtime exception thrown.", e3);
            return 3000;
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        this.mListenerCallback.set(null);
        try {
            release();
        } catch (RemoteException unused) {
        }
    }

    protected ConnectionClosedDetails getConnectionCloseDetails(ProtocolSocket protocolSocket) {
        if (protocolSocket.socketState().toConnectionState() == 4) {
            if (protocolSocket.closeDetail() != null) {
                return protocolSocket.closeDetail().convertToConnectionClosedDetails();
            }
            log.warn("getConnectionCloseDetails", "null close detail when closing socket", "socket", protocolSocket);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IConnectionListener getListenerCallback() {
        return this.mListenerCallback.get();
    }

    protected abstract String getMetricsSource();

    protected abstract ProtocolSocket getProtocolSocket();

    @Override // com.amazon.communication.IConnection
    public boolean isValidConnection() throws RemoteException {
        try {
            if (getProtocolSocket() == null) {
                return false;
            }
            return getProtocolSocket().socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED;
        } catch (RuntimeException e) {
            log.warn("isValidConnection", "Exception occurred!", e);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyInitialState(ProtocolSocket protocolSocket) {
        IConnectionListener listenerCallback = getListenerCallback();
        if (listenerCallback != null) {
            try {
                listenerCallback.onConnectionSetInitialState(protocolSocket.socketState().toConnectionState());
            } catch (RemoteException e) {
                log.warn("setProtocolSocket", "client-side callback object is now stale", e);
            }
        }
    }

    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        String str;
        int i;
        log.verbose("notifyStateChanged", "state changed", "socket", protocolSocket);
        IConnectionListener listenerCallback = getListenerCallback();
        if (listenerCallback == null) {
            log.warn("notifyStateChanged", "client-side callback object is now stale", new Object[0]);
            return;
        }
        ConnectionClosedDetails connectionCloseDetails = getConnectionCloseDetails(protocolSocket);
        if (connectionCloseDetails != null) {
            i = connectionCloseDetails.getDetailsCode();
            str = connectionCloseDetails.getMessage();
        } else {
            str = "";
            i = 0;
        }
        try {
            listenerCallback.onConnectionStateChanged(protocolSocket.socketState().toConnectionState(), i, str);
        } catch (RemoteException unused) {
            log.warn("notifyStateChanged", "client-side callback object is now stale", new Object[0]);
        }
    }

    public void release() throws RemoteException {
        IConnectionListener andSet = this.mListenerCallback.getAndSet(null);
        if (andSet != null) {
            andSet.asBinder().unlinkToDeath(this, 0);
        }
    }

    @Override // com.amazon.communication.IConnection
    public int sendMessage(MessageEnvelope messageEnvelope, int i) {
        try {
            this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_MESSAGE_SERVICE_SIDE_TOTAL, 1.0d);
            int sendMessageOnSocket = sendMessageOnSocket(messageEnvelope, i);
            if (sendMessageOnSocket == 0) {
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_MESSAGE_SERVICE_SIDE_SUCCESS, 1.0d);
            } else {
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_MESSAGE_SERVICE_SIDE_FAILURE, 1.0d);
            }
            return sendMessageOnSocket;
        } catch (RuntimeException e) {
            log.warn("sendMessage", "Exception occurred", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.IConnection
    public int sendReliableMessage(MessageEnvelope messageEnvelope, int i, int i2) {
        throw new UnsupportedOperationException("RLM (ReliableMessageProtocol) use cases are not supported.");
    }

    @Override // com.amazon.communication.IConnection
    public int sendRequest(MessageEnvelope messageEnvelope, IResponseHandler iResponseHandler) throws RemoteException {
        MetricEvent metricEvent;
        try {
            ServiceSideResponseHandlerProxy serviceSideResponseHandlerProxy = new ServiceSideResponseHandlerProxy(iResponseHandler, this.mResponseRouter, this.mPeriodicMetricReporter, this.mByteAccountant);
            int registerResponseHandler = this.mResponseRouter.registerResponseHandler(serviceSideResponseHandlerProxy);
            if (registerResponseHandler >= 1048576) {
                serviceSideResponseHandlerProxy.setRegisteredChannel(registerResponseHandler);
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_REQUEST_SERVICE_SIDE_TOTAL, 1.0d);
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_SRR_SERVICE_SIDE_TOTAL, 1.0d);
                try {
                    log.verbose("sendRequest", "sending request", "channel", Integer.valueOf(registerResponseHandler));
                    getProtocolSocket().sendMessage(messageEnvelope.toMessage(), ProtocolHandler.REQUEST_MESSAGE_TYPE, registerResponseHandler);
                    this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_REQUEST_SERVICE_SIDE_SUCCESS, 1.0d);
                    return 0;
                } catch (MissingCredentialsException e) {
                    log.warn("sendRequest", "unable to send message over protocol socket", e);
                    this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_REQUEST_SERVICE_SIDE_FAILURE, 1.0d);
                    this.mMetricEvent.addCounter(TCommMetrics.COUNT_SRR_SERVICE_SIDE_FAILURE, 1.0d);
                    return CommunicationErrorCodes.ERR_CONN_MISSING_CREDENTIALS;
                } catch (IOException e2) {
                    log.warn("sendRequest", "unable to send request over protocol socket", e2);
                    this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_REQUEST_SERVICE_SIDE_FAILURE, 1.0d);
                    metricEvent = this.mMetricEvent;
                    metricEvent.addCounter(TCommMetrics.COUNT_SRR_SERVICE_SIDE_FAILURE, 1.0d);
                    return CommunicationErrorCodes.ERR_CONN_SEND_REQUEST_SOCKET_ERROR;
                } catch (Exception e3) {
                    log.warn("sendRequest", "unable to send request over protocol socket. Runtime exception thrown", e3);
                    this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_REQUEST_SERVICE_SIDE_FAILURE, 1.0d);
                    metricEvent = this.mMetricEvent;
                    metricEvent.addCounter(TCommMetrics.COUNT_SRR_SERVICE_SIDE_FAILURE, 1.0d);
                    return CommunicationErrorCodes.ERR_CONN_SEND_REQUEST_SOCKET_ERROR;
                }
            }
            throw new IllegalArgumentException("Bad channel id obtained");
        } catch (RuntimeException e4) {
            log.warn("sendRequest", "Exception occurred!", e4);
            throw e4;
        }
    }
}
