package com.amazon.communication.socket;

import amazon.communication.Message;
import amazon.communication.connection.Purpose;
import amazon.speech.simclient.genericconnection.ConnectionResult;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.ByteBufferBackedMessage;
import com.amazon.communication.CsmQueuedByteBufferChainHandler;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.ProtocolHandlerFactory;
import com.amazon.communication.ProtocolHandlerManager;
import com.amazon.communication.TCommMetrics;
import com.amazon.communication.TuningFailedException;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseReason;
import com.amazon.dp.logger.DPLogger;
import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Set;
/* loaded from: classes12.dex */
public class CsmProtocolSocket extends ProtocolSocketBase {
    private static final String METRICS_SOURCE_NAME = "CsmProtocolSocket";
    private static final DPLogger log = new DPLogger("TComm.CsmProtocolSocket");
    protected CsmHandleDataCallable mHandleDataCallable;
    private MetricEvent mMetricEvent;
    private PeriodicMetricReporter mPeriodicMetricReporter;
    protected ProtocolHandler mProtocolHandler;
    protected ProtocolHandlerManager mProtocolHandlerManager;
    private volatile ProtocolSocket.ProtocolSocketState mState = ProtocolSocket.ProtocolSocketState.UNKNOWN;

    /* renamed from: com.amazon.communication.socket.CsmProtocolSocket$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult = new int[ConnectionResult.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_DISCONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_DOWNCHANNEL_PENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_DOWNCHANNEL_CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_CONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public CsmProtocolSocket(ProtocolHandlerManager protocolHandlerManager, Set<ProtocolSocket.ProtocolSocketAttribute> set, WorkExecutor workExecutor, PeriodicMetricReporter periodicMetricReporter) {
        this.mProtocolHandlerManager = protocolHandlerManager;
        this.mWorkExecutor = workExecutor;
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mMetricEvent = periodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        CsmQueuedByteBufferChainHandler csmQueuedByteBufferChainHandler = new CsmQueuedByteBufferChainHandler(this.mWorkExecutor, this, 50, this.mPeriodicMetricReporter);
        ProtocolHandlerFactory protocolHandlerFactory = this.mProtocolHandlerManager.getProtocolHandlerFactory("A:H");
        if (protocolHandlerFactory != null) {
            try {
                this.mProtocolHandler = protocolHandlerFactory.makeProtocolHandler(csmQueuedByteBufferChainHandler, this.mWorkExecutor, this, false, new HashMap());
            } catch (TuningFailedException e) {
                log.error(METRICS_SOURCE_NAME, "Exception thrown when creating ProtocolHandler", e);
            }
        }
        this.mHandleDataCallable = new CsmHandleDataCallable(this, this.mWorkExecutor, this.mProtocolHandler, null);
        addSupportedAttributes(set);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void close() {
        log.info("close", "CsmProtocolSocket does not support close(). CSM is in charge of maintaining the ProtocolSocket connectivity state. No CloseDetails provided.", new Object[0]);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseDetail closeDetail() {
        return null;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public CloseReason closeReason() {
        return null;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void enableKeepAlive(int i, int i2, int i3) throws SocketException {
        throw new UnsupportedOperationException("CsmProtocolSocket does not support KeepAlive.");
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public ConnectReason getConnectReason() {
        throw new UnsupportedOperationException("CsmProtocolSocket does not support getConnectReason.");
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public Purpose getPurpose() {
        return Purpose.REGULAR;
    }

    public void handleData(byte[] bArr) {
        this.mMetricEvent.addCounter(TCommMetrics.COUNT_TOTAL_RX_BYTES_CSM_PROTOCOL_SOCKET, bArr.length);
        this.mHandleDataCallable.processBytesAndEnqueue(bArr);
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public boolean isConnectReasonSupported() {
        return false;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int release() {
        return 1;
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public int retain() {
        return 1;
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void sendMessage(Message message, String str, int i) throws IOException {
        try {
            if (socketState() == ProtocolSocket.ProtocolSocketState.CONNECTING) {
                if (i == 865) {
                    this.mProtocolHandler.encodeMessage(message, str, i);
                    this.mMetricEvent.addCounter(TCommMetrics.COUNT_TOTAL_TX_SENT_BYTES_CSM_PROTOCOL_SOCKET, message.getPayloadSize());
                    return;
                }
                throw new IOException("Unable to send message on non-GatewayHandshake Channel as the connection is in CONNECTING state, channel:" + i);
            } else if (socketState() == ProtocolSocket.ProtocolSocketState.CONNECTED) {
                this.mProtocolHandler.encodeMessage(message, str, i);
                this.mMetricEvent.addCounter(TCommMetrics.COUNT_TOTAL_TX_SENT_BYTES_CSM_PROTOCOL_SOCKET, message.getPayloadSize());
            } else {
                throw new IOException("Unable to send message as socket was not in CONNECTING or CONNECTED state, state:" + socketState().toString());
            }
        } catch (ProtocolException e) {
            log.error("sendMessage", "exception while encoding or sending data", "socket", this, e);
        }
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void setPurpose(Purpose purpose) {
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public ProtocolSocket.ProtocolSocketState socketState() {
        return this.mState;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateSocketState(amazon.speech.simclient.genericconnection.ConnectionResult r8) {
        /*
            r7 = this;
            com.amazon.dp.logger.DPLogger r0 = com.amazon.communication.socket.CsmProtocolSocket.log
            r1 = 4
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            java.lang.String r4 = "newState"
            r2[r3] = r4
            java.lang.String r3 = r8.toString()
            r4 = 1
            r2[r4] = r3
            r3 = 2
            java.lang.String r4 = "oldState"
            r2[r3] = r4
            com.amazon.communication.socket.ProtocolSocket$ProtocolSocketState r4 = r7.mState
            r5 = 3
            r2[r5] = r4
            java.lang.String r4 = "updateSocketState"
            java.lang.String r6 = "Socket state updated by CSM"
            r0.info(r4, r6, r2)
            int r8 = r8.ordinal()
            if (r8 == r3) goto L39
            if (r8 == r5) goto L36
            if (r8 == r1) goto L33
            r0 = 5
            if (r8 == r0) goto L39
            com.amazon.communication.socket.ProtocolSocket$ProtocolSocketState r8 = r7.mState
            goto L3b
        L33:
            com.amazon.communication.socket.ProtocolSocket$ProtocolSocketState r8 = com.amazon.communication.socket.ProtocolSocket.ProtocolSocketState.CONNECTED
            goto L3b
        L36:
            com.amazon.communication.socket.ProtocolSocket$ProtocolSocketState r8 = com.amazon.communication.socket.ProtocolSocket.ProtocolSocketState.CONNECTING
            goto L3b
        L39:
            com.amazon.communication.socket.ProtocolSocket$ProtocolSocketState r8 = com.amazon.communication.socket.ProtocolSocket.ProtocolSocketState.DISCONNECTED
        L3b:
            com.amazon.communication.socket.ProtocolSocket$ProtocolSocketState r0 = r7.mState
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L48
            r7.mState = r8
            r7.notifyStateChanged()
        L48:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.socket.CsmProtocolSocket.updateSocketState(amazon.speech.simclient.genericconnection.ConnectionResult):void");
    }

    @Override // com.amazon.communication.socket.ProtocolSocketBase, com.amazon.communication.socket.ProtocolSocket
    public void verifyTuningResult(ByteBufferBackedMessage byteBufferBackedMessage) throws TuningFailedException {
        throw new UnsupportedOperationException("CsmProtocolSocket does not support Tuning.");
    }

    @Override // com.amazon.communication.socket.ProtocolSocket
    public void close(CloseDetail closeDetail) {
        log.info("close", "CsmProtocolSocket does not support close(). CSM is in charge of maintaining the ProtocolSocket connectivity state. CloseDetails:", closeDetail);
    }

    @VisibleForTesting
    CsmProtocolSocket() {
    }
}
