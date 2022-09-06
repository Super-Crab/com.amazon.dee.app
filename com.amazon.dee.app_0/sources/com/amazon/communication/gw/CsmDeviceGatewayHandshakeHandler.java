package com.amazon.communication.gw;

import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.SigningException;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.ExponentialBackoffWaitCalculator;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.TCommMetrics;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public class CsmDeviceGatewayHandshakeHandler extends DeviceGatewayHandshakeHandler {
    static final int BACKOFF_COEFFICIENT = 2000;
    public static final int CSM_HANDSHAKE_RETRIES = 5;
    static final long JITTER_FRACTION = 30;
    static final long MAX_BACKOFF_INTERVAL = 30000;
    private static final String METRICS_SOURCE_NAME = "CsmDeviceGatewayHandshakeHandler";
    static final long MIN_BACKOFF_INTERVAL = 0;
    private static DPLogger log = new DPLogger("TComm.CsmDeviceGatewayHandshakeHandler");
    private final ExponentialBackoffWaitCalculator mBackoffCalculator;
    protected final MetricEvent mMetricEvent;

    public CsmDeviceGatewayHandshakeHandler(MapAccountManagerWrapper mapAccountManagerWrapper, SignatureProvider signatureProvider, GatewayHandshakeProtocol gatewayHandshakeProtocol, WorkExecutor workExecutor, PeriodicMetricReporter periodicMetricReporter) {
        super(mapAccountManagerWrapper, signatureProvider, gatewayHandshakeProtocol, workExecutor, periodicMetricReporter);
        this.mBackoffCalculator = new ExponentialBackoffWaitCalculator(0L, 30000L, 2000, 0.3d);
        this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryHandshake(final ProtocolSocket protocolSocket) {
        if (this.mBackoffCalculator.getRetries() < 5) {
            long waitMs = this.mBackoffCalculator.getWaitMs();
            log.info("retryHandshake", "Retrying gateway handshake", "retry attempt #", Integer.valueOf(this.mBackoffCalculator.getRetries()), "waitTime in ms", Long.valueOf(waitMs));
            this.mExecutor.doBackgroundWorkAfter(new Callable<Void>() { // from class: com.amazon.communication.gw.CsmDeviceGatewayHandshakeHandler.2
                @Override // java.util.concurrent.Callable
                public Void call() {
                    try {
                        CsmDeviceGatewayHandshakeHandler.this.startHandshakeRetry(protocolSocket);
                        return null;
                    } catch (Exception e) {
                        CsmDeviceGatewayHandshakeHandler.log.error("notifyStateChanged", "Exception initiating gateway handshake", "socket", protocolSocket, e);
                        return null;
                    }
                }
            }, waitMs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startHandshakeRetry(ProtocolSocket protocolSocket) throws SigningException, ProtocolException, IOException, MissingCredentialsException {
        super.startHandshake(protocolSocket);
    }

    @Override // com.amazon.communication.gw.DeviceGatewayHandshakeHandler, com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public synchronized void notifyStateChanged(ProtocolSocket protocolSocket) {
        log.debug("notifyStateChanged", "state changed for socket", "socket", protocolSocket);
        if (ProtocolSocket.ProtocolSocketState.CONNECTING.equals(protocolSocket.socketState())) {
            try {
                startHandshake(protocolSocket);
            } catch (Exception e) {
                log.error("notifyStateChanged", "Exception starting gateway handshake", "socket", protocolSocket, e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.communication.gw.DeviceGatewayHandshakeHandler
    public void startHandshake(ProtocolSocket protocolSocket) throws SigningException, ProtocolException, IOException, MissingCredentialsException {
        this.mBackoffCalculator.reset();
        super.startHandshake(protocolSocket);
    }

    @Override // com.amazon.communication.gw.DeviceGatewayHandshakeHandler
    protected void startTimeoutCallable(final String str) {
        this.mExecutor.doBackgroundWorkAfter(new Callable<Void>() { // from class: com.amazon.communication.gw.CsmDeviceGatewayHandshakeHandler.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                ProtocolSocket remove = CsmDeviceGatewayHandshakeHandler.this.mInflightHandshakes.remove(str);
                if (remove == null) {
                    CsmDeviceGatewayHandshakeHandler.log.debug("timeoutCallable.call", "TimeoutCallable successfully completed without detecting a timeout, gateway handshake has completed successfully", "messageId", str);
                    return null;
                }
                CsmDeviceGatewayHandshakeHandler.this.mMetricEvent.addCounter(TCommMetrics.GATEWAY_HANDSHAKE_SEND_TIMEOUT, 1.0d);
                CsmDeviceGatewayHandshakeHandler.log.info("timeoutCallable.call", "handshake timed out for socket", "messageId", str, "socket", remove);
                CsmDeviceGatewayHandshakeHandler.this.retryHandshake(remove);
                return null;
            }
        }, 2000L);
    }
}
