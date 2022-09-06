package com.amazon.communication;

import amazon.communication.ConnectionAcquisitionFailedException;
import amazon.communication.ICommunicationManager;
import amazon.communication.Message;
import amazon.communication.MessageFactory;
import amazon.communication.MissingCredentialsException;
import amazon.communication.connection.IConnection;
import amazon.communication.connection.IllegalConnectionStateException;
import amazon.communication.connection.Policy;
import amazon.communication.connection.TransmissionFailedException;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class EchoMessageHandler extends EchoMessageHandlerBase {
    private static final int DEFAULT_TIMEOUT_MILLIS = 10000;
    private final int mChannel;
    private final ICommunicationManager mCommunicationManager;
    private static final DPLogger log = new DPLogger("TComm.EchoMessageHandlerBase");
    private static final Policy GATEWAY_FRIENDLY_POLICY = new Policy.Builder().setIsRequestResponseOnly(false).setIsLowLatencyNecessary(false).setIsAnonymousCredentialsAllowed(true).build();

    public EchoMessageHandler(ICommunicationManager iCommunicationManager, int i) {
        this.mCommunicationManager = iCommunicationManager;
        this.mChannel = i;
    }

    @Override // amazon.communication.MessageHandler
    public void onMessage(EndpointIdentity endpointIdentity, Message message) {
        InputStream payload = message.getPayload();
        if (EchoMessageHandlerBase.isPingMessage(payload)) {
            log.verbose("onMessage", "receive PING message", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity));
            amazon.communication.connection.IConnection iConnection = null;
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    byte[] bArr = new byte[payload.available() + 3];
                                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                                    EchoMessageHandlerBase.preparePongHeader(wrap);
                                    int available = payload.available();
                                    int read = payload.read(bArr, 3, available);
                                    if (available != read) {
                                        log.error("onMessage", "did not read expected number of bytes", "expected", Integer.valueOf(available), "read", Integer.valueOf(read));
                                    }
                                    log.verbose("onMessage", "sending response", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, EndpointIdentity.logSafe(endpointIdentity), "messageId", new String(bArr, 3, available));
                                    wrap.rewind();
                                    iConnection = this.mCommunicationManager.acquireConnectedConnection(endpointIdentity, GATEWAY_FRIENDLY_POLICY, (IConnection.ConnectionListener) null, 10000);
                                    iConnection.sendMessage(MessageFactory.createMessage(wrap), this.mChannel);
                                } catch (TransmissionFailedException e) {
                                    log.error("onMessage", "TransmissionFailedException while reading message", e);
                                    if (iConnection == null) {
                                        return;
                                    }
                                }
                            } catch (IOException e2) {
                                log.error("onMessage", "IOException while reading message", e2);
                                if (iConnection == null) {
                                    return;
                                }
                            }
                        } catch (ConnectionAcquisitionFailedException e3) {
                            log.error("onMessage", "ConnectionAcquisitionFailedException while reading message", e3);
                            if (iConnection == null) {
                                return;
                            }
                        }
                    } catch (IllegalConnectionStateException e4) {
                        log.error("onMessage", "IllegalConnectionStateException while reading message", e4);
                        if (iConnection == null) {
                            return;
                        }
                    }
                } catch (MissingCredentialsException e5) {
                    log.error("onMessage", "MissingCredentialsException while reading message", e5);
                    if (iConnection == null) {
                        return;
                    }
                }
                iConnection.release();
                return;
            } catch (Throwable th) {
                if (iConnection != null) {
                    iConnection.release();
                }
                throw th;
            }
        }
        log.verbose("onMessage", "received a non-PING message. ignoring", new Object[0]);
    }

    @Override // amazon.communication.MessageHandler
    public void onMessageFragment(EndpointIdentity endpointIdentity, int i, Message message, boolean z) {
        throw new UnsupportedOperationException("GatewayEchoMessageHandler doesn't support fragmented messages");
    }
}
