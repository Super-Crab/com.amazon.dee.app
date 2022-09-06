package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.MessageHandler;
import amazon.communication.MissingCredentialsException;
import amazon.communication.authentication.SigningException;
import amazon.communication.connection.Channels;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.ProtocolException;
import com.amazon.communication.ProtocolHandler;
import com.amazon.communication.TCommMetrics;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.gw.GatewayHandshakeMessage;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
public class DeviceGatewayHandshakeHandler implements ProtocolSocket.ProtocolSocketStateListener, MessageHandler {
    public static final int HANDSHAKE_TIMEOUT_MS = 2000;
    private static final String METRICS_SOURCE_NAME = "DeviceGatewayHandshakeHandler";
    private static DPLogger log = new DPLogger("TComm.DeviceGatewayHandshakeHandler");
    protected WorkExecutor mExecutor;
    protected final Map<String, ProtocolSocket> mInflightHandshakes = new ConcurrentHashMap();
    protected final MapAccountManagerWrapper mMapAccountManager;
    protected final MetricEvent mMetricEvent;
    protected final PeriodicMetricReporter mPeriodicMetricReporter;
    protected final GatewayHandshakeProtocol mProtocol;
    protected final SignatureProvider mSignatureProvider;

    public DeviceGatewayHandshakeHandler(MapAccountManagerWrapper mapAccountManagerWrapper, SignatureProvider signatureProvider, GatewayHandshakeProtocol gatewayHandshakeProtocol, WorkExecutor workExecutor, PeriodicMetricReporter periodicMetricReporter) {
        if (mapAccountManagerWrapper != null) {
            if (signatureProvider == null) {
                throw new IllegalArgumentException("SignatureProvider must not be null");
            }
            if (gatewayHandshakeProtocol == null) {
                throw new IllegalArgumentException("Protocol must not be null");
            }
            if (workExecutor != null) {
                this.mMapAccountManager = mapAccountManagerWrapper;
                this.mSignatureProvider = signatureProvider;
                this.mProtocol = gatewayHandshakeProtocol;
                this.mExecutor = workExecutor;
                this.mPeriodicMetricReporter = periodicMetricReporter;
                this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
                return;
            }
            throw new IllegalArgumentException("executor must not be null");
        }
        throw new IllegalArgumentException("MapAccountManagerWrapper must not be null");
    }

    protected void doCloseSocket(ProtocolSocket protocolSocket) {
        log.info("doCloseSocket", "closing protocol socket due to incomplete handshake (but not actualy closing until TAG-1679 is done)", "socket", protocolSocket);
    }

    @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
    public synchronized void notifyStateChanged(ProtocolSocket protocolSocket) {
        log.debug("notifyStateChanged", "state changed for socket", "socket", protocolSocket);
        if (ProtocolSocket.ProtocolSocketState.CONNECTED.equals(protocolSocket.socketState())) {
            try {
                startHandshake(protocolSocket);
            } catch (Exception e) {
                log.error("notifyStateChanged", "Exception starting gateway handshake", "socket", protocolSocket, e);
                doCloseSocket(protocolSocket);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    @Override // amazon.communication.MessageHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMessage(amazon.communication.identity.EndpointIdentity r18, amazon.communication.Message r19) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.gw.DeviceGatewayHandshakeHandler.onMessage(amazon.communication.identity.EndpointIdentity, amazon.communication.Message):void");
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("onMessageFragment not implemented");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startHandshake(ProtocolSocket protocolSocket) throws SigningException, ProtocolException, IOException, MissingCredentialsException {
        log.debug("startHandshake", "starting handshake", "socket", protocolSocket);
        this.mMetricEvent.addCounter(TCommMetrics.GATEWAY_HANDSHAKE_SEND_ATTEMPT, 1.0d);
        GatewayHandshakeMessage.InitiateMessageBuilder initiateMessageBuilder = new GatewayHandshakeMessage.InitiateMessageBuilder(this.mSignatureProvider);
        Set<String> accounts = this.mMapAccountManager.getAccounts();
        String account = this.mMapAccountManager.getAccount();
        log.debug("startHandshake", "got accounts", "primaryAccount", account, "accounts", accounts);
        if (accounts != null && account != null) {
            for (String str : accounts) {
                if (!str.equals(account)) {
                    log.debug("startHandshake", "adding account to handshake message", "directedCustomerId", str);
                    initiateMessageBuilder.addAccount(str);
                }
            }
        }
        GatewayHandshakeInitiate build = initiateMessageBuilder.build();
        log.debug("startHandshake", "encoding and sending handshake message", "message", build);
        protocolSocket.sendMessage(this.mProtocol.encode(build), ProtocolHandler.MESSAGE_MESSAGE_TYPE, Channels.GW_HANDSHAKE_CHANNEL);
        this.mMetricEvent.addCounter(TCommMetrics.GATEWAY_HANDSHAKE_SEND_SUCCESS, 1.0d);
        this.mInflightHandshakes.put(build.getMessageId(), protocolSocket);
        protocolSocket.retain();
        startTimeoutCallable(build.getMessageId());
    }

    protected void startTimeoutCallable(final String str) {
        this.mExecutor.doBackgroundWorkAfter(new Callable<Void>() { // from class: com.amazon.communication.gw.DeviceGatewayHandshakeHandler.1
            @Override // java.util.concurrent.Callable
            public Void call() {
                ProtocolSocket remove = DeviceGatewayHandshakeHandler.this.mInflightHandshakes.remove(str);
                DeviceGatewayHandshakeHandler.this.mMetricEvent.addCounter(TCommMetrics.GATEWAY_HANDSHAKE_SEND_TIMEOUT, 1.0d);
                if (remove == null) {
                    DeviceGatewayHandshakeHandler.log.debug("timeoutCallable.call", "TimoutCallable succesfully completed without detecting a timeout, gateway handshake has completed succesfully", "messageId", str);
                    return null;
                }
                remove.release();
                DeviceGatewayHandshakeHandler.log.info("timeoutCallable.call", "handshake timed out for socket", "messageId", str, "socket", remove);
                DeviceGatewayHandshakeHandler.this.doCloseSocket(remove);
                return null;
            }
        }, 2000L);
    }
}
