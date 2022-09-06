package com.amazon.communication;

import amazon.speech.simclient.genericconnection.GenericConnectionClient;
import amazon.speech.simclient.genericconnection.UpstreamMessageCallback;
import amazon.speech.simclient.genericconnection.UpstreamMessageResult;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.dp.logger.DPLogger;
import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
/* loaded from: classes12.dex */
public class CsmQueuedByteBufferChainHandler extends AbstractQueuedByteBufferChainHandler {
    private static final String METRICS_SOURCE_NAME = "CsmQueuedByteBufferChainHandler";
    private static final DPLogger log = new DPLogger("TComm.CsmQueuedByteBufferChainHandler");
    private GenericConnectionClient mGenericConnectionClient;
    private MetricEvent mMetricEvent;
    private final Object mSendBytesLock;

    /* renamed from: com.amazon.communication.CsmQueuedByteBufferChainHandler$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult = new int[UpstreamMessageResult.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.CONNECTION_UNAVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.INVALID_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.CLOUD_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.TIMEOUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.REMOTE_EXCEPTION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$UpstreamMessageResult[UpstreamMessageResult.UNKNOWN_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes12.dex */
    class TCommUpstreamMessageCallback implements UpstreamMessageCallback {
        private ByteBufferChain mChain;

        public TCommUpstreamMessageCallback(ByteBufferChain byteBufferChain) {
            this.mChain = byteBufferChain;
        }

        @Override // amazon.speech.simclient.genericconnection.UpstreamMessageCallback
        public void onResult(UpstreamMessageResult upstreamMessageResult) {
            if (upstreamMessageResult.ordinal() != 0) {
                CsmQueuedByteBufferChainHandler.log.error("TCommUpstreamMessageCallback.onResult", "Unable to send the message", "error", upstreamMessageResult.toString(), "messageSize", Integer.valueOf(this.mChain.getDataSize()), "ByteBufferChain", this.mChain.toString());
            }
        }
    }

    public CsmQueuedByteBufferChainHandler(WorkExecutor workExecutor, ProtocolSocket protocolSocket, int i, PeriodicMetricReporter periodicMetricReporter) {
        super(workExecutor, protocolSocket, i);
        this.mSendBytesLock = new Object();
        this.mGenericConnectionClient = CsmTCommService.getGenericConnectionClient();
        this.mMetricEvent = periodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
    }

    @Override // com.amazon.communication.AbstractQueuedByteBufferChainHandler
    protected void retriedTooManyTimes() {
        log.info("retriedTooManyTimes", "a higher level component has retried too many times, will close the socket", new Object[0]);
        this.mProtocolSocket.close(new CloseDetail(CloseStatusCodes.TOO_MANY_WRITE_RETRIES, "Too many retries trying to write to the socket"));
    }

    @Override // com.amazon.communication.AbstractQueuedByteBufferChainHandler
    protected int sendByteBufferChain(ByteBufferChain byteBufferChain) throws ByteBufferChainConsumptionException {
        boolean z = this.mProtocolSocket.socketState() == ProtocolSocket.ProtocolSocketState.CONNECTING;
        try {
            byte[] byteArray = byteBufferChain.toByteArray();
            synchronized (this.mSendBytesLock) {
                log.debug("sendByteBufferChain", "Sending bytes to CSM", "numBytes", Integer.valueOf(byteArray.length), "isHandshake", Boolean.valueOf(z));
                this.mGenericConnectionClient.sendMessage(byteArray, z, new TCommUpstreamMessageCallback(byteBufferChain));
            }
            this.mMetricEvent.addCounter(TCommMetrics.COUNT_TOTAL_TX_BYTES, byteArray.length);
            this.mMetricEvent.addCounter(TCommMetrics.CSM_BYTE_BUFFER_CHAIN_TX_BYTES, byteArray.length);
            return byteArray.length;
        } catch (IOException e) {
            throw new ByteBufferChainConsumptionException("Unable to send bytes to CSM", e);
        }
    }

    @VisibleForTesting
    public void setGenericConnectionClient(GenericConnectionClient genericConnectionClient) {
        this.mGenericConnectionClient = genericConnectionClient;
    }
}
