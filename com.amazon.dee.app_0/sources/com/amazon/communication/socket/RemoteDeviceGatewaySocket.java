package com.amazon.communication.socket;

import amazon.communication.Message;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.Channels;
import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.AlwaysOnSocketWatchdog;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.TCommMetrics;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.gw.GatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayControlMessage;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.identity.DeviceUniqueEndpointIdentifier;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.time.GlobalTimeSource;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.dp.logger.DPLogger;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class RemoteDeviceGatewaySocket extends GatewaySocket {
    private static final int AVAILABILITY_TIMEOUT_FAIL_MS = 20000;
    private static final int AVAILABILITY_TIMEOUT_RETRY_MS = 5000;
    private static final int DELAY_CLOSE_TIME_MS = 5000;
    private static final String METRICS_SOURCE_NAME = "RemoteDeviceGatewaySocket";
    private final AtomicReference<AlwaysOnSocketWatchdog> mD2DGatewayWatchdog;
    private final WorkExecutor mExecutor;
    private final GatewayControlProtocol mGatewayControlProtocol;
    private final AtomicBoolean mHasCheckedRemoteDeviceAvailability;
    private final MetricEvent mMetricEvent;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    private final ReentrantLock mRefCountLock;
    private final int mSocketIdentifier;
    private ProtocolSocket.ProtocolSocketState mSocketState;
    private final AtomicLong mTimeAvailabilityRequestSent;
    private final TryReuseOrCallCloseCallable mTryReuseOrCallCloseWorkable;
    private static final DPLogger log = new DPLogger("TComm.RemoteDeviceGatewaySocket");
    private static AtomicInteger sConnectionCounter = new AtomicInteger();
    public static final Set<ProtocolSocket.ProtocolSocketAttribute> D2D_ATTRIBUTES = new HashSet(Arrays.asList(ProtocolSocket.ProtocolSocketAttribute.SECURE));

    public RemoteDeviceGatewaySocket(DeviceIdentity deviceIdentity, WorkExecutor workExecutor, GatewayApplicationProtocol gatewayApplicationProtocol, ProtocolSocket protocolSocket, AlwaysOnSocketWatchdog alwaysOnSocketWatchdog, GatewayControlProtocol gatewayControlProtocol, PeriodicMetricReporter periodicMetricReporter) throws SocketAcquisitionFailedException {
        super(deviceIdentity, gatewayApplicationProtocol, protocolSocket);
        this.mSocketIdentifier = sConnectionCounter.incrementAndGet();
        this.mTimeAvailabilityRequestSent = new AtomicLong();
        this.mRefCountLock = new ReentrantLock();
        this.mSocketState = ProtocolSocket.ProtocolSocketState.CONNECTING;
        this.mD2DGatewayWatchdog = new AtomicReference<>();
        this.mHasCheckedRemoteDeviceAvailability = new AtomicBoolean(false);
        this.mGatewayControlProtocol = gatewayControlProtocol;
        this.mExecutor = workExecutor;
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        this.mD2DGatewayWatchdog.set(alwaysOnSocketWatchdog);
        this.mTryReuseOrCallCloseWorkable = new TryReuseOrCallCloseCallable(this, workExecutor, 5000);
        this.mUniqueEndpointIdentifier = new DeviceUniqueEndpointIdentifier(deviceIdentity);
        addSupportedAttributes(D2D_ATTRIBUTES);
        if (this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
            log.info("RemoteDeviceGatewaySocket constructor", "the protocol socket is in CONNECTED state, check for remote endpoint availability", new Object[0]);
            checkIfRemoteEndpointAvailableOnce();
            return;
        }
        log.info("RemoteDeviceGatewaySocket constructor", "the protocol socket is not in CONNECTED state, we'll delay the check for remote endpoint availability until the socket is in CONNECTED state", "socket", this.mProtocolSocket);
    }

    private void checkIfRemoteEndpointAvailableOnce() throws SocketAcquisitionFailedException {
        if (this.mHasCheckedRemoteDeviceAvailability.getAndSet(true)) {
            log.info("checkIfRemoteEndpointAvailable", "We have checked already. Ignore!", new Object[0]);
            return;
        }
        try {
            this.mTimeAvailabilityRequestSent.set(prepareAndSendControlMessage());
            this.mExecutor.doBackgroundWorkAfter(new Callable<Void>() { // from class: com.amazon.communication.socket.RemoteDeviceGatewaySocket.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    if (RemoteDeviceGatewaySocket.this.mSocketState == ProtocolSocket.ProtocolSocketState.CONNECTING && RemoteDeviceGatewaySocket.this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                        RemoteDeviceGatewaySocket.log.warn("checkIfRemoteEndpointAvailable", "This endpoint is still CONNECTING, assume GW failure", "AVAILABILITY_TIMEOUT_FAIL_MS", Integer.valueOf((int) RemoteDeviceGatewaySocket.AVAILABILITY_TIMEOUT_FAIL_MS), "mIdentity", RemoteDeviceGatewaySocket.this.mIdentity.toLogSafeString());
                        RemoteDeviceGatewaySocket.this.close(new CloseDetail(CloseStatusCodes.SERVER_COMMUNICATION_ERROR, "Did not receive remote device availability from Gateway"));
                        return null;
                    }
                    return null;
                }
            }, SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US);
            this.mExecutor.doBackgroundWorkAfter(new Callable<Void>() { // from class: com.amazon.communication.socket.RemoteDeviceGatewaySocket.2
                @Override // java.util.concurrent.Callable
                public Void call() {
                    if (RemoteDeviceGatewaySocket.this.mSocketState == ProtocolSocket.ProtocolSocketState.CONNECTING && RemoteDeviceGatewaySocket.this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                        RemoteDeviceGatewaySocket.log.info("checkIfRemoteEndpointAvailable", "This endpoint is still CONNECTING, retry getting availability", "AVAILABILITY_TIMEOUT_RETRY_MS", 5000, "mIdentity", RemoteDeviceGatewaySocket.this.mIdentity.toLogSafeString());
                        try {
                            RemoteDeviceGatewaySocket.this.prepareAndSendControlMessage();
                            return null;
                        } catch (MissingCredentialsException e) {
                            RemoteDeviceGatewaySocket.log.warn("checkIfRemoteEndpointAvailable", "retry callable hit error sending message", "mIdentity", RemoteDeviceGatewaySocket.this.mIdentity.toLogSafeString(), e);
                            return null;
                        } catch (IOException e2) {
                            RemoteDeviceGatewaySocket.log.warn("checkIfRemoteEndpointAvailable", "retry callable hit error sending message", "mIdentity", RemoteDeviceGatewaySocket.this.mIdentity.toLogSafeString(), e2);
                            return null;
                        }
                    }
                    return null;
                }
            }, 5000L);
        } catch (MissingCredentialsException e) {
            log.error("checkIfRemoteEndpointAvailable", "error sending message - missing credentials", "mIdentity", this.mIdentity.toLogSafeString(), e);
            throw new SocketAcquisitionFailedException(e);
        } catch (IOException e2) {
            log.error("checkIfRemoteEndpointAvailable", "error sending message", "mIdentity", this.mIdentity.toLogSafeString(), e2);
            throw new SocketAcquisitionFailedException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long prepareAndSendControlMessage() throws IOException, MissingCredentialsException {
        GatewayControlMessage gatewayControlMessage = new GatewayControlMessage(GatewayControlMessage.MessageType.MESSAGE_TYPE_DEVICE_AVAILABLE_REQUEST, this.mIdentity);
        Message encode = this.mGatewayControlProtocol.encode(gatewayControlMessage);
        log.info("prepareAndSendControlMessage", "sending device availability request", "message", gatewayControlMessage.toLogSafeString());
        long currentTimeMillis = GlobalTimeSource.INSTANCE.currentTimeMillis();
        this.mProtocolSocket.sendMessage(encode, ProtocolHandler.MESSAGE_MESSAGE_TYPE, Channels.GW_CTL_CHANNEL);
        this.mMetricEvent.addCounter(TCommMetrics.COUNT_SEND_D2D_CONTROL_MSG, GlobalTimeSource.INSTANCE.currentTimeMillis() - currentTimeMillis);
        return currentTimeMillis;
    }

    @Override // com.amazon.communication.socket.GatewaySocket, com.amazon.communication.socket.ProtocolSocket
    public void close(CloseDetail closeDetail) {
        log.debug("close", "Close called -- setting state to DISCONNECTED and notifying listeners", "details", closeDetail);
        this.mSocketState = ProtocolSocket.ProtocolSocketState.DISCONNECTED;
        notifyStateChanged();
    }

    @Override // com.amazon.communication.socket.GatewaySocket, com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public void notifyStateChanged(ProtocolSocket protocolSocket) {
        log.debug("notifyStateChanged", "got state change notification", "socket", protocolSocket);
        ProtocolSocket protocolSocket2 = this.mProtocolSocket;
        if (protocolSocket != protocolSocket2) {
            log.error("notifyStateChanged", "Receive state change callback from unrecognized socket. This could be a coding bug.", "socket that invoked the callback", protocolSocket, "expected socket", protocolSocket2);
        } else {
            ProtocolSocket.ProtocolSocketState socketState = protocolSocket.socketState();
            if (socketState == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                try {
                    checkIfRemoteEndpointAvailableOnce();
                } catch (SocketAcquisitionFailedException e) {
                    log.error("notifyStateChanged", "Failed to check for availibitly of the remote endpoint", "socket", protocolSocket, "ex", e);
                }
            } else if (socketState == ProtocolSocket.ProtocolSocketState.DISCONNECTED || socketState == ProtocolSocket.ProtocolSocketState.DISCONNECTING) {
                this.mSocketState = socketState;
            }
        }
        super.notifyStateChanged(protocolSocket);
    }

    public void onGatewayControlMessage(GatewayControlMessage gatewayControlMessage) {
        log.info("onGatewayControlMessage", "Received gateway control message", "message", gatewayControlMessage.toLogSafeString());
        long andSet = this.mTimeAvailabilityRequestSent.getAndSet(0L);
        if (andSet != 0) {
            this.mMetricEvent.addTimer(TCommMetrics.TIME_D2D_GET_AVAILABILITY_RESPONSE, GlobalTimeSource.INSTANCE.currentTimeMillis() - andSet);
        }
        if (gatewayControlMessage.isAvailable) {
            ProtocolSocket.ProtocolSocketState protocolSocketState = this.mSocketState;
            ProtocolSocket.ProtocolSocketState protocolSocketState2 = ProtocolSocket.ProtocolSocketState.CONNECTED;
            if (protocolSocketState == protocolSocketState2) {
                return;
            }
            this.mSocketState = protocolSocketState2;
            notifyStateChanged();
            return;
        }
        ProtocolSocket.ProtocolSocketState protocolSocketState3 = this.mSocketState;
        ProtocolSocket.ProtocolSocketState protocolSocketState4 = ProtocolSocket.ProtocolSocketState.DISCONNECTED;
        if (protocolSocketState3 == protocolSocketState4) {
            return;
        }
        this.mSocketState = protocolSocketState4;
        notifyStateChanged();
    }

    @Override // com.amazon.communication.socket.GatewaySocket, com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int release() {
        this.mRefCountLock.lock();
        try {
            int decrementAndGet = this.mRefCount.decrementAndGet();
            log.debug("release", "releasing", "refCount after decrement", Integer.valueOf(decrementAndGet));
            if (decrementAndGet == 0) {
                this.mTryReuseOrCallCloseWorkable.enqueueAfter(5000L);
            }
            return decrementAndGet;
        } finally {
            this.mRefCountLock.unlock();
        }
    }

    protected void releaseWatchdog() {
        log.verbose("releaseWatchdog", "attempting to release watchdog", new Object[0]);
        AlwaysOnSocketWatchdog andSet = this.mD2DGatewayWatchdog.getAndSet(null);
        if (andSet != null) {
            andSet.release();
        } else {
            log.warn("releaseWatchdog", "attempted to release a null watchdog", new Object[0]);
        }
    }

    @Override // com.amazon.communication.socket.GatewaySocket, com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int retain() {
        this.mRefCountLock.lock();
        try {
            this.mTryReuseOrCallCloseWorkable.tryReuseSocket();
            int incrementAndGet = this.mRefCount.incrementAndGet();
            log.debug("retain", "reference count after", Integer.valueOf(incrementAndGet));
            return incrementAndGet;
        } finally {
            this.mRefCountLock.unlock();
        }
    }

    @Override // com.amazon.communication.socket.GatewaySocket, com.amazon.communication.socket.ProtocolSocket
    public ProtocolSocket.ProtocolSocketState socketState() {
        if (this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
            return this.mSocketState;
        }
        return this.mProtocolSocket.socketState();
    }

    public String toString() {
        String str = "";
        if (log.isDebugEnabled()) {
            String valueOf = String.valueOf(this.mSocketIdentifier);
            EndpointIdentity endpointIdentity = this.mIdentity;
            ProtocolSocket.ProtocolSocketState protocolSocketState = this.mSocketState;
            if (protocolSocketState != null) {
                str = protocolSocketState.toString();
            }
            return formatProtocolSocketString(valueOf, endpointIdentity, str);
        }
        String valueOf2 = String.valueOf(this.mSocketIdentifier);
        String logSafeString = this.mIdentity.toLogSafeString();
        ProtocolSocket.ProtocolSocketState protocolSocketState2 = this.mSocketState;
        if (protocolSocketState2 != null) {
            str = protocolSocketState2.toString();
        }
        return formatProtocolSocketString(valueOf2, logSafeString, str);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase
    public void notifyStateChanged() {
        log.verbose("notifyStateChange", "sending state change notification", "state", this.mSocketState);
        ProtocolSocket.ProtocolSocketState protocolSocketState = this.mSocketState;
        if (protocolSocketState == ProtocolSocket.ProtocolSocketState.DISCONNECTING || protocolSocketState == ProtocolSocket.ProtocolSocketState.DISCONNECTED) {
            releaseWatchdog();
        }
        super.notifyStateChanged();
    }
}
